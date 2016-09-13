package net.cloudkit.integration.services;

import org.springframework.stereotype.Component;

/**
 * ServiceFactoryBean.java
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
