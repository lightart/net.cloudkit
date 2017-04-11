package net.cloudkit.transform.domain.model;

/**
 * DeclCert.java
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2015年08月26日 上午11:38:34
 */
// DECL_CERT
public class DeclCert {

    private static final long serialVersionUID = 1L;

    /**
     * 平台统一编号
     */
    // DECL_NO
    private String declNo;

    /**
     * 报关单海关编号
     */
    // CUSTOMS_NO
    private String customsNo;

    /**
     * 随附单证编码
     */
    // DOCU_CODE
    private String docuCode;

    /**
     * 随附单证编号
     */
    // CERT_CODE
    private String certCode;

    /**
     * 是否需要联网核查1、否2、是
     */
    // IS_LINK_CHECK
    private Integer isLinkCheck;

    /**
     * 单证核查状态1、待核查 2、核查通过 3、核查不通过
     */
    // CHECK_STATUS
    private Integer checkStatus;

    /**
     * 核查状态描述
     */
    // CHECK_DESC
    private String checkDesc;

    /**
     * 联网单证状态1:通关单无记录或与经营单位不匹配、2:海关未入库、3:海关已入库、4:海关已核销、6:查询明细为空
     */
    // LINK_STATUS
    private Integer linkStatus;

    /**
     * 单证有效日期
     */
    // VALID_TIME
    private Long validTime;

    /**
     * 单证签发日期
     */
    // ISSUED_TIME
    private Long issuedTime;

    public String getDeclNo() {
        return declNo;
    }

    public void setDeclNo(String declNo) {
        this.declNo = declNo;
    }

    public String getCustomsNo() {
        return customsNo;
    }

    public void setCustomsNo(String customsNo) {
        this.customsNo = customsNo;
    }

    public String getDocuCode() {
        return docuCode;
    }

    public void setDocuCode(String docuCode) {
        this.docuCode = docuCode;
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public Integer getIsLinkCheck() {
        return isLinkCheck;
    }

    public void setIsLinkCheck(Integer isLinkCheck) {
        this.isLinkCheck = isLinkCheck;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCheckDesc() {
        return checkDesc;
    }

    public void setCheckDesc(String checkDesc) {
        this.checkDesc = checkDesc;
    }

    public Integer getLinkStatus() {
        return linkStatus;
    }

    public void setLinkStatus(Integer linkStatus) {
        this.linkStatus = linkStatus;
    }

    public Long getValidTime() {
        return validTime;
    }

    public void setValidTime(Long validTime) {
        this.validTime = validTime;
    }

    public Long getIssuedTime() {
        return issuedTime;
    }

    public void setIssuedTime(Long issuedTime) {
        this.issuedTime = issuedTime;
    }
}

