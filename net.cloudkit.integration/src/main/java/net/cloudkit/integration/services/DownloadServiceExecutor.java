package net.cloudkit.integration.services;

import javax.xml.ws.Holder;
import java.net.MalformedURLException;

/**
 * 下载服务 DownloadServiceExecutor.java
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2015年08月26日 上午11:38:34
 */
public class DownloadServiceExecutor extends AbstractServiceExecutor {

    public void execute(Settings settings, String serviceName, RequestContext serviceContext) {
        logger.debug(";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; DownloadServiceExecutor ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");

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
