package net.cloudkit.integration.services;

public interface ServiceExecutor {

    void execute(Settings settings, String serviceName, RequestContext serviceContext);
}
