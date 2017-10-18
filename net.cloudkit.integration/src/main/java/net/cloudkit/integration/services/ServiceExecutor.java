package net.cloudkit.integration.services;

/**
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2015年08月26日 上午11:38:34
 */
public interface ServiceExecutor {

    void execute(Settings settings, String serviceName, RequestContext serviceContext);
}
