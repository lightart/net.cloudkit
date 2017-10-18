package net.cloudkit.integration.services;

import java.util.HashMap;
import java.util.Map;

/**
 * RequestContext.java
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2015年08月26日 上午11:38:34
 */
public class RequestContext {

    /** 请求KEY（由平台分配，并绑定） **/
    private String hashKey;
    /** 类型 **/
    private String requestType;
    /** 来源主机 source host **/
    private String sourceAddress;
    /** 来源主机MAC址 **/
    private String mac;
    private String username;
    private String password;
    /** 压缩算法 **/
    private String compressAlgorithm = "gzip";
    /** 签名算法 **/
    private String signatureAlgorithm;
    /** 加密算法 **/
    private String encryptAlgorithm = "3DES";
    /** 编码 **/
    private String encoding = "UTF-8";
    /** 版本 **/
    private String version = "1.0";
    /** 请求时间戳 **/
    private String timestamp;
    /** 说明 **/
    private String description;
    /** 附加参数 **/
    private Map<String, String> arguments = new HashMap<>();

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompressAlgorithm() {
        return compressAlgorithm;
    }

    public void setCompressAlgorithm(String compressAlgorithm) {
        this.compressAlgorithm = compressAlgorithm;
    }

    public String getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    public void setSignatureAlgorithm(String signatureAlgorithm) {
        this.signatureAlgorithm = signatureAlgorithm;
    }

    public String getEncryptAlgorithm() {
        return encryptAlgorithm;
    }

    public void setEncryptAlgorithm(String encryptAlgorithm) {
        this.encryptAlgorithm = encryptAlgorithm;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getArguments() {
        return arguments;
    }

    public void setArguments(Map<String, String> arguments) {
        this.arguments = arguments;
    }

}
