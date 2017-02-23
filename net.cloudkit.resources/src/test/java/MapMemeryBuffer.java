import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MapMemeryBuffer {

    public static void main(String[] args) throws Exception {

        // 1、使用MappedByteBuffer: 0.7s
        String srcFile = "D://Noname1.txt";
        String destFile = "D://copy.txt";
        RandomAccessFile rafi = new RandomAccessFile(srcFile, "r");
        RandomAccessFile rafo = new RandomAccessFile(destFile, "rw");
        FileChannel fci = rafi.getChannel();
        FileChannel fco = rafo.getChannel();
        long size = fci.size();
        MappedByteBuffer mbbi = fci.map(FileChannel.MapMode.READ_ONLY, 0, size);
        MappedByteBuffer mbbo = fco.map(FileChannel.MapMode.READ_WRITE, 0, size);
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            byte b = mbbi.get(i);
            mbbo.put(i, b);
        }
        fci.close();
        fco.close();
        rafi.close();
        rafo.close();
        System.out.println("Spend: " + (double) (System.currentTimeMillis() - start) / 1000 + "s");

        /*
        // 2、自己处理Buffer(RandomAccessFile): 0.13s
        String srcFile = "D://Noname1.txt";
        String destFile = "D://copy.txt";
        RandomAccessFile rafi = new RandomAccessFile(srcFile, "r");
        RandomAccessFile rafo = new RandomAccessFile(destFile, "rw");

        byte[] buf = new byte[1024 * 8];

        long start = System.currentTimeMillis();

        int c = rafi.read(buf);

        while (c > 0) {
            if (c == buf.length) {
                rafo.write(buf);
            } else {
                rafo.write(buf, 0, c);
            }

            c = rafi.read(buf);
        }
        rafi.close();
        rafo.close();
        System.out.println("Spend: " + (double) (System.currentTimeMillis() - start) / 1000 + "s");
        */


        ByteBuffer byteBuf = ByteBuffer.allocate(1024 * 14 * 1024);
        byte[] bbb = new byte[14 * 1024 * 1024];
        FileInputStream fis = new FileInputStream("E://data/other/UltraEdit_17.00.0.1035_SC.exe");
        FileOutputStream fos = new FileOutputStream("E://data/other/outFile.txt");
        FileChannel fc = fis.getChannel();
        long timeStar = System.currentTimeMillis();// 得到当前的时间
        fc.read(byteBuf);// 1 读取
        //MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
        System.out.println(fc.size()/1024);
        long timeEnd = System.currentTimeMillis();// 得到当前的时间
        System.out.println("Read time :" + (timeEnd - timeStar) + "ms");

        timeStar = System.currentTimeMillis();
        fos.write(bbb);//2.写入
        //mbb.flip();
        timeEnd = System.currentTimeMillis();
        System.out.println("Write time :" + (timeEnd - timeStar) + "ms");
        fos.flush();
        fc.close();
        fis.close();
    }


}
