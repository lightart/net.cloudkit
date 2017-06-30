package net.cloudkit.transform;

import net.cloudkit.transform.utilities.Common;
import noNamespace.*;
import org.apache.xmlbeans.XmlException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

/**
 * 生产者 lightart
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2011-10-8 下午12:31:44
 */
public class ProducerThread extends Thread {

    private BlockingQueue<File> fileQueue;
    private BlockingQueue<Map<String, Object>> queue;
    private Path failedPath;
    private Path historyPath;

    public ProducerThread(BlockingQueue<File> fileQueue, Path historyPath, Path failedPath, BlockingQueue<Map<String, Object>> queue) {
        this.fileQueue = fileQueue;
        this.queue = queue;
        this.historyPath = historyPath;
        this.failedPath = failedPath;
    }

    @Override
    public void run() {
        while (true) {
            File file = null;
            try {
                file = fileQueue.take();
                // Bind the instance to the generated XMLBeans types.
                DecMessageDocument decMessageDocument = DecMessageDocument.Factory.parse(file);
                // Get and print pieces of the XML instance.
                DecMessageType decMessage = decMessageDocument.getDecMessage();

                DecHeadType decHead = decMessage.getDecHead();

                DecListsType decLists = decMessage.getDecLists();
                // decLists.getDecListArray();

                DecContainersType containers = decMessage.getDecContainers();
                containers.getContainerArray();

                DecLicenseDocusType decLicenseDocus = decMessage.getDecLicenseDocus();
                decLicenseDocus.getLicenseDocuArray();

                BizOrdInfoType bizOrdInfo = decMessage.getBizOrdInfo();

                // 添加队列
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("file", file);
                dataMap.put("history_path", historyPath);
                dataMap.put("failed_path", failedPath);

                queue.put(dataMap);
                System.out.println("Number of products in the queueName after production: " + queue.size());

            } catch (IOException | XmlException | InterruptedException ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();
                if (file.exists()) {
                    Common.movePath(file, failedPath.toString());
                }
            }
        }
    }
}
