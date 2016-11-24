//package ocr;
//
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.methods.GetMethod;
//import org.apache.commons.httpclient.params.HttpClientParams;
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import java.io.*;
//import java.lang.reflect.Method;
//import java.nio.MappedByteBuffer;
//import java.nio.channels.FileChannel;
//import java.security.AccessController;
//import java.security.PrivilegedAction;
//import java.util.Observable;
//import java.util.Timer;
//import java.util.TimerTask;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.concurrent.atomic.AtomicLong;
//import java.util.zip.ZipFile;
//
//public class Downloader extends Observable {
//    protected String url, savePath;			 //下载地址与保存路径
//    protected FileChannel channel;			  //保存文件的通道
//    protected long size, perSize;			  //文件大小与每个小文件的大小
//    protected volatile long downloaded;	   // 已下载的
//    protected int connectCount;				 //连接数
//    protected Connection[] connections;		 //连接对象
//    protected boolean isSupportRange;		 //是否支持断点下载
//    protected long timeout;					 //超时
//    protected boolean exists;				   //是否存在
//    private RandomAccessFile randomAccessFile;
//    protected volatile boolean stop;			//停止
//    private static volatile  boolean exception; //是否异常
//    private AtomicLong prevDownloaded = new AtomicLong(0); //上一次的下载结果
//    private static Log log = LogFactory.getLog(Downloader.class);
//    private AtomicInteger loseNum = new AtomicInteger(0);
//    private int maxThread;
//
//    public Downloader(String url, String savePath) throws IOException {
//        //超时一小时
//        this(url, savePath, 1000 * 60*5,50);
//    }
//
//    public Downloader(String url, String savePath, long timeout,int maxThread) throws FileNotFoundException {
//        this.timeout = timeout;
//        this.url = url;
//        this.maxThread = maxThread;
//        File file = new File(savePath);
//        if (!file.exists()) file.mkdirs();
//        this.savePath= file.getAbsolutePath() + "/" + url.substring(url.lastIndexOf("/"));
//        exists = new File(this.savePath).exists();
//        if(!exists){
//            randomAccessFile=   new RandomAccessFile(this.savePath+".temp", "rw");
//            channel =randomAccessFile.getChannel();
//        }
//    }
//
//
//    public GetMethod method(long start, long end) throws IOException {
//        GetMethod method = new GetMethod(Downloader.this.url);
//        method.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36");
//        if (end > 0) {
//            method.setRequestHeader("Range", "bytes=" + start + "-" + (end - 1));
//        } else {
//            method.setRequestHeader("Range", "bytes=" + start + "-");
//        }
//        HttpClientParams clientParams = new HttpClientParams();
//        //5秒超时
//        clientParams.setConnectionManagerTimeout(5000);
//        HttpClient client = new HttpClient(clientParams);
//        client.executeMethod(method);
//        int statusCode = method.getStatusCode();
//        if (statusCode >= 200 && statusCode < 300) {
//            isSupportRange = (statusCode == 206) ? true : false;
//        }
//        return method;
//    }
//
//    public void init() throws IOException {
//        size = method(0, -1).getResponseContentLength();
//        if (isSupportRange) {
//            if (size < 4 * 1024 * 1024) {  //如果小于4M
//                connectCount = 1;
//            } else if (size < 10 * 1024 * 1024) { //如果文件小于10M 则两个连接
//                connectCount = 2;
//            } else if (size < 30 * 1024 * 1024) { //如果文件小于80M 则使用6个连接
//                connectCount = 3;
//            } else if (size < 60 * 1024 * 1024) {		  //如果小于60M 则使用10个连接
//                connectCount = 4;
//            } else {
//                //否则为10个连接
//                connectCount = 5;
//            }
//        } else {
//            connectCount = 1;
//        }
//        log.debug(String.format("%s size:%s connectCount:%s", this.url, this.size, this.connectCount));
//        perSize = size / connectCount;
//        connections = new Connection[connectCount];
//        long offset = 0;
//        for (int i = 0; i < connectCount - 1; i++) {
//            connections[i] = new Connection(offset, offset + perSize);
//            offset += perSize;
//        }
//        connections[connectCount - 1] = new Connection(offset, size);
//    }
//
//
//    /**
//     * 强制释放内存映射
//     *
//     * @param mappedByteBuffer
//     */
//    static void unmapFileChannel(final MappedByteBuffer mappedByteBuffer) {
//        try {
//            if (mappedByteBuffer == null) {
//                return;
//            }
//            mappedByteBuffer.force();
//            AccessController.doPrivileged(new PrivilegedAction<Object>() {
//                @Override
//                public Object run() {
//                    try {
//                        Method getCleanerMethod = mappedByteBuffer.getClass().getMethod("cleaner", new Class[0]);
//                        getCleanerMethod.setAccessible(true);
//                        sun.misc.Cleaner cleaner = (sun.misc.Cleaner) getCleanerMethod.invoke(mappedByteBuffer, new Object[0]);
//                        cleaner.clean();
//                    } catch (Exception e) {
//                        //LOG.error("unmapFileChannel." + e.getMessage());
//                    }
//                    return null;
//                }
//            });
//        } catch (Exception e) {
//            log.debug("异常->exception=true");
//            exception = true;
//            log.error(e);
//        }
//    }
//
//
//    private void timer() {
//        Timer timer = new Timer();
//        //延迟3秒，3秒运行一次
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                log.debug(String.format("已下载-->%s -> %s",(((double) downloaded) / size * 100) + "%", url));
//                //如果上一次的下载大小与当前的一样就退出
//                if(prevDownloaded.get() ==downloaded && downloaded<size){
//                    if(loseNum.getAndIncrement()>=10){
//                        log.debug(String.format("上次下载%s与当前下载%s一致,exception->true  url:%s ",prevDownloaded.get(),downloaded,url));
//                        exception = true;
//                    }
//                }
//                //如果下载完成或者异常就退出
//                if(downloaded>=size || exception){
//                    stop = true;
//                    cancel();
//                }
//                //设置上次下载的大小等于现在的大小
//                prevDownloaded.set(downloaded);
//            }
//        },3000,3000);
//    }
//
//    public void start() throws IOException {
//        if (exists) {
//            log.info("文件已存在." + this.url);
//            Thread.currentThread().interrupt();
//            return;
//        }
//        while (Thread.activeCount()>maxThread){
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {}
//        }
//        init();
//        timer();
//        CountDownLatch countDownLatch = new CountDownLatch(connections.length);
//        log.debug("开始下载:" + url);
//        for (int i = 0; i < connections.length; i++) {
//            new DownloadPart(countDownLatch, i).start();
//        }
//        end(countDownLatch);
//    }
//
//    private boolean rename(File tempFile){
//        File file = new File(this.savePath);
//        boolean isRename=tempFile.renameTo(file);
//        if(!isRename){
//            try {
//                IOUtils.copy(new FileInputStream(tempFile),new FileOutputStream(file));
//            } catch (IOException e) {
//                log.error(e);
//            }
//        }
//        return true;
//    }
//
//    public void end(CountDownLatch countDownLatch){
//        try {
//            //超过指定时间就直接结束
//            countDownLatch.await(timeout, TimeUnit.MILLISECONDS);
//        } catch (InterruptedException e) {
//            exception = true;
//            log.error(e);
//            log.info("下载失败:"+this.url);
//        } finally {
//            try {
//                channel.force(true);
//                channel.close();
//                randomAccessFile.close();
//            } catch (IOException e) {
//                log.error(e);
//            }
//            File temp = new File(this.savePath+".temp");
//            log.debug(String.format("%s  %s", exception, this.url));
//            //如果有异常则删除已下载的临时文件
//            if(exception){
//                if(!temp.delete()){
//                    if(temp!=null)temp.delete();
//                }
//            }else{
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {}
//                rename(temp);
//                setChanged();
//                notifyObservers(this.url);
//                log.info("下载成功:"+this.url);
//            }
//        }
//    }
//
//
//
//    private class Connection {
//        long start, end;
//
//        public Connection(long start, long end) {
//            this.start = start;
//            this.end = end;
//        }
//
//        public InputStream getInputStream() throws IOException {
//            return method(start, end).getResponseBodyAsStream();
//        }
//    }
//
//
//    private class DownloadPart implements Runnable {
//        CountDownLatch countDownLatch;
//        int i;
//
//        public DownloadPart(CountDownLatch countDownLatch, int i) {
//            this.countDownLatch = countDownLatch;
//            this.i = i;
//        }
//        public void start() {
//            new Thread(this).start();
//        }
//        @Override
//        public void run() {
//            MappedByteBuffer buffer = null;
//            InputStream is = null;
//            try {
//                is = connections[i].getInputStream();
//                buffer = channel.map(FileChannel.MapMode.READ_WRITE, connections[i].start, connections[i].end - connections[i].start);
//                byte[] bytes = new byte[4 * 1024];
//                int len;
//                while ((len = is.read(bytes)) != -1 && !exception && !stop) {
//                    buffer.put(bytes, 0, len);
//                    downloaded+= len;
//                }
//                log.debug(String.format("file block had downloaded.%s %s",i,url));
//            } catch (IOException e) {
//                log.error(e);
//            } finally {
//                unmapFileChannel(buffer);
//                if(buffer != null)buffer.clear();
//                if (is != null) try {
//                    is.close();
//                } catch (IOException e) {
//                }
//                countDownLatch.countDown();
//            }
//        }
//    }
//
//
//}
