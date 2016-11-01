package net.cloudkit.integration.services;

import javax.xml.ws.Holder;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 上传服务 UploadServiceExecutor.java
 */
public class UploadServiceExecutor extends AbstractServiceExecutor {

    public void execute(Settings settings, String serviceName, RequestContext serviceContext) {
        logger.debug(";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; UploadServiceExecutor ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");

        // upload.receipt
        byte[] requestContext = getRequestContext(serviceContext).getBytes();

        Holder<byte[]> responseData = new Holder<>();
        try {
            Path downloadPath = Paths.get(settings.getLocalDirectory());
            Path historyPath = Paths.get(downloadPath.getParent().toString() + File.separator + downloadPath.getFileName() + "History");

            // 创建目录
            if (Files.notExists(downloadPath)) {
                Files.createDirectories(downloadPath);
            }
            if (Files.notExists(historyPath)) {
                Files.createDirectories(historyPath);
            }

            File[] files = downloadPath.toFile().listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    continue;
                }

                StringBuffer data = new StringBuffer("");
                System.out.println(file.toPath());
                BufferedReader reader = Files.newBufferedReader(file.toPath(), Charset.defaultCharset());
                String line;
                while ((line = reader.readLine()) != null) {
                    data.append(line);
                }
                reader.close();

                System.out.println(data.toString());
                byte[] requestData = data.toString().getBytes();
                byte[] result = invoke(serviceName, requestContext, requestData, responseData);

                logger.debug(new String(result));

                // 移到历史文件目录
                Path pathTo = Paths.get(historyPath.toString() + File.separator + new SimpleDateFormat("yyyy/MM/dd/HH").format(new Date()) + File.separator + file.getName());
                if (Files.notExists(pathTo)) {
                    Files.createDirectories(pathTo);
                }

                // 调用文件移动方法
                Files.move(Paths.get(file.getAbsolutePath()), pathTo, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
