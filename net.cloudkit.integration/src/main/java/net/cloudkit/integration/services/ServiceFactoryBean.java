package net.cloudkit.integration.services;

import org.springframework.stereotype.Component;

/**
 * ServiceFactoryBean.java
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2015年08月26日 上午11:38:34
 */
@Component
public class ServiceFactoryBean {

    public static final String DOWNLOAD_SAVE_DATA_KEY = "download.save.data";
    public static final String UPLOAD_RECEIPT_KEY = "upload.receipt";

    public ServiceExecutor getServiceBean(String serviceName) {
        ServiceExecutor serviceExecutor = null;
        if(serviceName.equals(DOWNLOAD_SAVE_DATA_KEY)) {
            serviceExecutor = new DownloadServiceExecutor();
        } else if(serviceName.equals(UPLOAD_RECEIPT_KEY)){
            serviceExecutor = new UploadServiceExecutor();
        }
        return serviceExecutor;
    }
}
