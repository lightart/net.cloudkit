package net.cloudkit.integration.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.xml.ws.Holder;
import java.net.MalformedURLException;

/**
 * DownloadServiceExecutor.java
 */
// @Component
public class DownloadServiceExecutor extends AbstractServiceExecutor {

    public void execute(Settings settings, String serviceName, RequestContext serviceContext) {
        logger.info(";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; DownloadServiceExecutor ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");

        byte[] requestContext = getRequestContext(serviceContext).getBytes();
        byte[] requestData = "".getBytes();
        Holder<byte[]> responseData = new Holder<>();
        try {
            byte[] result = invoke(serviceName, requestContext, requestData, responseData);

            logger.debug(new String(result));

            // 解压文件
            unzip(responseData.value, settings.getLocalDirectory());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
