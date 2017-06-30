package net.cloudkit.transform;

import net.cloudkit.transform.utilities.Common;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

/**
 * 消费者
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2011-10-8 下午12:31:44
 */
public class ConsumerThread extends Thread {

    private BlockingQueue<Map<String, Object>> queue;

    public ConsumerThread(BlockingQueue<Map<String, Object>> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {

            Path historyPath = null;
            Path failedPath = null;
            File file = null;

            try {
                // 执行队列
                Map<String, Object> dataMap = queue.take();

                file = (File) dataMap.get("file");
                historyPath = (Path) dataMap.get("history_path");
                failedPath = (Path) dataMap.get("failed_path");

                System.out.println("Number of products in the queueName after consumption: " + queue.size() + " " + System.currentTimeMillis());
            } catch (InterruptedException ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();

                // 移到错误目录
                if(file.exists()) {
                    Common.movePath(file, failedPath.toString());
                }

            } finally {
                // 移到历史目录
                if(file.exists()) {
                    Common.movePath(file, historyPath.toString());
                }
            }
        }
    }

}
