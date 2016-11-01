package net.cloudkit.integration.services;

import net.cloudkit.integration.utils.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.ws.Holder;
import javax.xml.ws.Service;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * AbstractServiceExecutor.java
 */
public abstract class AbstractServiceExecutor implements ServiceExecutor {

    protected static Logger logger = LoggerFactory.getLogger(AbstractServiceExecutor.class);

    /** 接口地址 */
    public static final String WS_INTERFACE_SPEC_KEY = "ws.interface_spec";

    /** properties 配置 */
    protected static Properties propertiesConfiguration;

    // 初始化 properties 配置
    static {
        propertiesConfiguration = SpringContextHolder.getBean("propertiesConfiguration");
    }

    @Override
    public abstract void execute(Settings settings, String serviceName, RequestContext serviceContext);

    /**
     * 获取请求上下文
     *
     * @param requestContext
     * @return
     */
    protected String getRequestContext(RequestContext requestContext) {
        // TODO 构造请求上下文
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<requestContext xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns=\"http://www.cloudkit.net/schema/\">" +
                "    <!-- 请求KEY（由平台分配，并绑定） -->" +
                "    <property name=\"hashKey\" value=\"" + requestContext.getHashKey() + "\" />" +
                "    <!-- 类型 -->" +
                "    <property name=\"requestType\" value=\"" + requestContext.getRequestType() + "\" />" +
                "    <!-- 来源主机 source host -->" +
                "    <property name=\"sourceAddress\" value=\"192.168.1.16\" />" +
                "    <!-- 来源主机MAC址 -->" +
                "    <property name=\"mac\" value=\"54-04-a6-cb-14-df\" />" +
                "    <property name=\"username\" value=\"\" />" +
                "    <property name=\"password\" value=\"\" />" +
                "    <!-- dataPresentation -->" +
                "    <!-- 压缩算法 -->" +
                "    <property name=\"compressAlgorithm\" value=\"gzip\"/>" +
                "    <!-- 签名算法 -->" +
                "    <property name=\"signatureAlgorithm\" value=\"MD5/SHA\"/>" +
                "    <!-- 加密算法 -->" +
                "    <property name=\"encryptAlgorithm\" value=\"3DES\"/>" +
                "    <!-- 编码 -->" +
                "    <property name=\"encoding\" value=\"UTF-8\" />" +
                "    <!-- 版本 -->" +
                "    <property name=\"version\" value=\"1.0\" />" +
                "    <!-- 请求时间戳 -->" +
                "    <property name=\"timestamp\" value=\"" + new Date().getTime() + "\" />" +
                "    <!-- 说明 -->" +
                "    <property name=\"description\" value=\"\" />" +
                "    <!-- 附加参数 -->" +
                "    <property name=\"arguments\">" +
                "            <map>" +
                "                    <entry key=\"saicSysNo\" value=\"766350979\" value-type=\"\" />" +
                "                    <entry key=\"depCodeChg\" value=\"5300\" value-type=\"\" />" +
                "                    <entry key=\"depInCode\" value=\"5300\" value-type=\"\" />" +
                "                    <entry key=\"externalSeq\" value=\"5e97bd0204d08a0dd4b57df4a9d262a1\" value-type=\"\" />" +
                "                    <entry key=\"clientId\" value=\"5300001976914\" value-type=\"\" />" +
                "                    <entry key=\"declPort \" value=\"5301,5318,5317,5316,5304,5321,5141,5320,5341,3101,3104,\" value-type=\"\" />" +
                "                    <entry key=\"qpApiType\" value=\"2\" value-type=\"\" />" +
                "                    <entry key=\"comCode\" value=\"JHWL\" value-type=\"\" />" +
                "                    <entry key=\"comType\" value=\"2\" value-type=\"\" />" +
                "                    <entry key=\"operatorName\" value=\"杨倩\" value-type=\"\" />" +
                "                    <entry key=\"comName\" value=\"深圳市巨航国际物流有限公司\" value-type=\"\" />" +
                "                    <entry key=\"certNo\" value=\"df630b\" value-type=\"\" />" +
                "                    <entry key=\"iePort \" value=\"5301,5318,5317,5316,5304,5321,5141,5320,5341,3101,3104,\" value-type=\"\" />" +
                "                    <entry key=\"regCoCgac\" value=\"4403180237\" value-type=\"\" />" +
                "                    <entry key=\"icCode\" value=\"8930000011040\" value-type=\"\" />" +
                "                    <entry key=\"ownerCode\" value=\"4403180237\" value-type=\"\" />" +
                "                    <entry key=\"ieFlag\" value=\"E\" value-type=\"\" />" +
                "                    <entry key=\"suFlag\" value=\"S\" value-type=\"\" />" +
                "                    <entry key=\"qpApiType\" value=\"3\" value-type=\"\" />" +
                "            </map>" +
                "    </property>" +
                "</requestContext>";
    }

    /**
     * 调用 Web Service
     *
     * @param serviceName
     * @param requestContext
     * @param requestData
     * @param responseData
     * @return result
     * @throws MalformedURLException
     */
    protected byte[] invoke(String serviceName, byte[] requestContext, byte[] requestData, Holder<byte[]> responseData) throws MalformedURLException {
        // 调用外部接口
        URL url = new URL(propertiesConfiguration.getProperty(WS_INTERFACE_SPEC_KEY));
        QName qname = new QName("http://ws.interfaces.quickpass.cloudkit.net/ServiceProviderFacade", "ServiceProviderFacade");
        Service service = Service.create(url, qname);
        ServiceProviderFacade superPass = service.getPort(ServiceProviderFacade.class);
        // Holder<byte[]> responseData = new Holder<byte[]>();
        byte[] result = superPass.service(serviceName, requestContext, requestData, responseData);

        // logger.debug("responseContext: \n" + new String(result) + "\nresponseData: \n" + new String(responseData.value));
        return result;
    }

    /**
     * zip解压缩
     *
     * @param data
     * @param target 解压地址
     */
    protected void unzip(byte[] data, String target) {

        ByteArrayInputStream bis = null;
        ZipInputStream zip = null;
        Path formalPath = Paths.get(target);
        Path tempPath = Paths.get(formalPath.getParent().toString() + File.separator + formalPath.getFileName() + "Temp");
        try {
            // 创建文件夹
            if (Files.notExists(formalPath)) {
                Files.createDirectories(tempPath);
            }
            if (Files.notExists(tempPath)) {
                Files.createDirectories(tempPath);
            }

            bis = new ByteArrayInputStream(data);
            zip = new ZipInputStream(bis);
            ZipEntry entry = null;
            while ((entry = zip.getNextEntry()) != null && !entry.isDirectory()) {
                byte[] buf = new byte[1024];
                Path pathFile = tempPath.resolve(entry.getName());
                OutputStream baos = new FileOutputStream(pathFile.toFile());

                int num;
                while ((num = zip.read(buf, 0, buf.length)) != -1) {
                    baos.write(buf, 0, num);
                }

                baos.flush();
                zip.closeEntry();
                baos.close();

                Path pathTo = formalPath.resolve(entry.getName());
                // 调用文件移动方法
                Files.move(pathFile, pathTo, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception ex) {
            // ex.printStackTrace();
            logger.error(ex.getMessage(), ex);
        } finally {
            if (zip != null) {
                try {
                    zip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
