package net.cloudkit.integration.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ServiceActivatorFacade.java
 */
@Component
public class ServiceActivatorFacade {

    protected static Logger logger = LoggerFactory.getLogger(ServiceActivatorFacade.class);

    @Autowired
    private ServiceFactoryBean serviceFactoryBean;

    // private ServiceExecutor serviceExecutor;

    private Settings settings;
    private String serviceName;
    private RequestContext requestContext;

    /*
    public void setServiceExecutor(ServiceExecutor serviceExecutor) {
        this.serviceExecutor = serviceExecutor;
    }
    */

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setRequestContext(RequestContext requestContext) {
        this.requestContext = requestContext;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public void execute() {
        // serviceExecutor.execute(settings, serviceName, requestContext);
        ServiceExecutor serviceExecutor = serviceFactoryBean.getServiceBean(serviceName);
        if(serviceExecutor != null) {
            serviceExecutor.execute(settings, serviceName, requestContext);
        }
    }

}
