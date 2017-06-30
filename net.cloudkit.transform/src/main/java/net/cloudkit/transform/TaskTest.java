package net.cloudkit.transform;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        // 处理队列
        BlockingQueue<File> fileQueue = new ArrayBlockingQueue<>(20, true);

        // 处理队列
        BlockingQueue<Map<String, Object>> queue = new ArrayBlockingQueue<>(20, true);

        // Executors类，提供了一系列工厂方法用于创先线程池，返回的线程池都实现了ExecutorService接口。
        // public static ExecutorService newFixedThreadPool(int nThreads)
        // 创建固定数目线程的线程池。
        // public static ExecutorService newCachedThreadPool()
        // 创建一个可缓存的线程池，调用execute 将重用以前构造的线程（如果线程可用）。如果现有线程没有可用的，则创建一个新线程并添加到池中。终止并从缓存中移除那些已有 60 秒钟未被使用的线程。
        // public static ExecutorService newSingleThreadExecutor()
        // 创建一个单线程化的Executor。
        // public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize)
        // 创建一个支持定时及周期性的任务执行的线程池，多数情况下可用来替代Timer类。
        // 创建一个线程池 Executors.newFixedThreadPool(2); Executors.newCachedThreadPool();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Path sourcePath = Paths.get("E:/transform/sources");
        Path historyPath = Paths.get(sourcePath.getParent().toString() + File.separator + "histories");
        Path failedPath = Paths.get(sourcePath.getParent().toString() + File.separator + "faileds");

        try {
            // 创建目录
            if (Files.notExists(sourcePath)) {
                Files.createDirectories(sourcePath);
            }
            if (Files.notExists(historyPath)) {
                Files.createDirectories(historyPath);
            }
            if (Files.notExists(failedPath)) {
                Files.createDirectories(failedPath);
            }
        } catch (IOException ex) {
            // TODO Auto-generated catch block
        }

        /*
        for(int i = 0; i < 10; i++) {

        }
        */

        // 执行各个线程
        executor.execute(new ProducerThread(fileQueue, historyPath, failedPath, queue));
        executor.execute(new ConsumerThread(queue));

        while (true) {
            // 遍历回执文件夹
            File[] files = sourcePath.toFile().listFiles();
            for (File file : files) {
                // 文件目录则退出
                if (file.isDirectory()) {
                    continue;
                }

                fileQueue.add(file);
            }
        }

        // 关闭线程池
        // executor.shutdown();
    }

    /*
    public static void main(String[] args) {

        ArrayBlockingQueue<String> queueName = new ArrayBlockingQueue<String>(10);

        Producer producer1 = new Producer("producer1", queueName);
        Producer producer2 = new Producer("producer2", queueName);
        Producer producer3 = new Producer("producer2", queueName);

        Consumer consumer1 = new Consumer(queueName);
        Consumer consumer2 = new Consumer(queueName);
        Consumer consumer3 = new Consumer(queueName);

        new Thread(producer1).start();
        new Thread(producer2).start();
        new Thread(producer3).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
        new Thread(consumer3).start();
    }
    */
}

