package net.cloudkit.integration.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Holder;

/*
 * 通过annotation方式实现webservice
 * targetNamespace属性定义了自己的命名空间，
 * serviceName则定义了< definitions >标签和<service>标签的name属性
 */
// Service Endpoint Interface
@WebService(
    name = "ServiceProviderFacade",
    targetNamespace = "http://ws.interfaces.quickpass.cloudkit.net/ServiceProviderFacade",
    serviceName = "ServiceProviderFacade"
)
// SOAP绑定方式，默认是document wrapped方式
// @SOAPBinding(style = SOAPBinding.Style.RPC)
// 1.Document Wrapped：
// @SOAPBinding(style=SOAPBinding.Style.DOCUMENT, use=SOAPBinding.Use.LITERAL, parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)
// 2.Document Bare：
// @SOAPBinding(style=SOAPBinding.Style.DOCUMENT, use=SOAPBinding.Use.LITERAL, parameterStyle=SOAPBinding.ParameterStyle.BARE)
// 3.RPC：
@SOAPBinding(
    style = SOAPBinding.Style.RPC,
    use = SOAPBinding.Use.ENCODED,
    parameterStyle = SOAPBinding.ParameterStyle.WRAPPED
)
// @SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
/**
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2015年08月26日 上午11:38:34
 */
public interface ServiceProviderFacade {

    /**
     * 服务处理
     *
     * @param serviceName
     * @param requestContext
     * @param requestData
     * @param responseData
     * @return
     */
    @WebMethod(action = "service", operationName = "service", exclude = false)
    @WebResult(name = "result")
    byte[] service(
        @WebParam(name = "serviceName") String serviceName,
        @WebParam(name = "requestContext") byte[] requestContext,
        @WebParam(name = "requestData") byte[] requestData,
        @WebParam(name = "responseData", mode = WebParam.Mode.OUT) Holder<byte[]> responseData
    );

}
