package net.cloudkit.transform.domain.model;

/**
 * DeclState.java
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2015年08月26日 上午11:38:34
 */
// DECL_STATE
public class DeclState {

    private static final long serialVersionUID = 1L;

    /**
     * 所属公司代码
     */

    // COMPANY_CODE
    private String companyCode;

    /**
     * 所属公司名称
     */

    // COMPANY_NAME
    private String companyName;

    /**
     * 海关注册代码
     */

    // EXPORT_CODE
    private String exportCode;

    /**
     * 平台统一编号
     */

    // DECL_NO
    private String declNo;

    /**
     * 业务状态
     */

    // STATUS
    private String status;

    /**
     * 委托状态 1、未委托2、已委托3、取消委托
     */

    // CONSIGN_STATUS
    private Integer consignStatus;

    /**
     * 委托人
     */

    // CONSIGN_USER
    private String consignUser;

    /**
     * 委托时间
     */

    // CONSIGN_TIME
    private Long consignTime;

    /**
     * 委托接收人
     */

    // CONSIGN_RECEIVE_USER
    private String consignReceiveUser;

    /**
     * 委托接收时间
     */

    // CONSIGN_RECEIVE_TIME
    private Long consignReceiveTime;

    /**
     * 制单人
     */

    // CREATE_USER
    private String createUser;

    /**
     * 制单日期
     */

    // CREATE_DATE
    private Long createDate;

    /**
     * 初审单人
     */

    // VERIFY_USER
    private String verifyUser;

    /**
     * 初审单时间
     */

    // VERIFY_TIME
    private Long verifyTime;

    /**
     * 复审单人
     */

    // RE_VERIFY_USER
    private String reVerifyUser;

    /**
     * 复审单时间
     */

    // RE_VERIFY_TIME
    private Long reVerifyTime;

    /**
     * 是否逻辑审核 1、未审核2、已审核
     */

    // IS_CHECK
    private Integer isCheck;

    /**
     * 审核人
     */

    // CHECK_USER
    private String checkUser;

    /**
     * 审核时间
     */

    // CHECK_TIME
    private Long checkTime;

    /**
     * 申报人
     */

    // DECL_USER
    private String declUser;

    /**
     * 申报时间
     */

    // DECL_TIME
    private Long declTime;

    /**
     * 放行时间
     */

    // RELEASE_TIME
    private Long releaseTime;

    /**
     * QP状态
     */

    // QP_STATUS
    private String qpStatus;

    /**
     * 申报模式 1、自报2、委托
     */

    // DECL_MODE
    private Integer declMode;

    /**
     * 是否锁定 控制单证暂停1、正常 ２、锁定
     */

    // LOCKED
    private Integer locked;

    /**
     * 锁定时间
     */

    // LOCK_TIME
    private Long lockTime;

    /**
     * 优先级
     */

    // PRIORITY
    private Integer priority;

    /**
     * 修改人
     */

    // MODIFY_USER
    private String modifyUser;

    /**
     * 修改日期
     */

    // MODIFY_DATE
    private Long modifyDate;

    /**
     * QP原因
     */

    // QP_REASON
    private String qpReason;


    /**
     * 申报通道
     */

    // DECL_CHANNEL
    private Integer declChannel;

    /**
     * 小清单下载状态
     */

    // CDS_STATUS
    private Integer cdsStatus;

    /**
     * 版本号
     */

    // VERSION
    private String version;

    /**
     * 结关时间
     */

    // CLEARANCE_TIME
    private Long clearanceTime;

    public Long getClearanceTime() {
        return clearanceTime;
    }

    public void setClearanceTime(Long clearanceTime) {
        this.clearanceTime = clearanceTime;
    }

    public Integer getCdsStatus() {
        return cdsStatus;
    }

    public void setCdsStatus(Integer cdsStatus) {
        this.cdsStatus = cdsStatus;
    }

    public Integer getDeclChannel() {
        return declChannel;
    }

    public void setDeclChannel(Integer declChannel) {
        this.declChannel = declChannel;
    }

    public String getQpReason() {
        return qpReason;
    }

    public void setQpReason(String qpReason) {
        this.qpReason = qpReason;
    }

    public String getDeclNo() {
        return declNo;
    }

    public void setDeclNo(String declNo) {
        this.declNo = declNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getConsignStatus() {
        return consignStatus;
    }

    public void setConsignStatus(Integer consignStatus) {
        this.consignStatus = consignStatus;
    }

    public String getConsignUser() {
        return consignUser;
    }

    public void setConsignUser(String consignUser) {
        this.consignUser = consignUser;
    }

    public Long getConsignTime() {
        return consignTime;
    }

    public void setConsignTime(Long consignTime) {
        this.consignTime = consignTime;
    }

    public String getConsignReceiveUser() {
        return consignReceiveUser;
    }

    public void setConsignReceiveUser(String consignReceiveUser) {
        this.consignReceiveUser = consignReceiveUser;
    }

    public Long getConsignReceiveTime() {
        return consignReceiveTime;
    }

    public void setConsignReceiveTime(Long consignReceiveTime) {
        this.consignReceiveTime = consignReceiveTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getVerifyUser() {
        return verifyUser;
    }

    public void setVerifyUser(String verifyUser) {
        this.verifyUser = verifyUser;
    }

    public Long getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Long verifyTime) {
        this.verifyTime = verifyTime;
    }

    public String getReVerifyUser() {
        return reVerifyUser;
    }

    public void setReVerifyUser(String reVerifyUser) {
        this.reVerifyUser = reVerifyUser;
    }

    public Long getReVerifyTime() {
        return reVerifyTime;
    }

    public void setReVerifyTime(Long reVerifyTime) {
        this.reVerifyTime = reVerifyTime;
    }

    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    public String getDeclUser() {
        return declUser;
    }

    public void setDeclUser(String declUser) {
        this.declUser = declUser;
    }

    public Long getDeclTime() {
        return declTime;
    }

    public void setDeclTime(Long declTime) {
        this.declTime = declTime;
    }

    public Long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getQpStatus() {
        return qpStatus;
    }

    public void setQpStatus(String qpStatus) {
        this.qpStatus = qpStatus;
    }

    public Integer getDeclMode() {
        return declMode;
    }

    public void setDeclMode(Integer declMode) {
        this.declMode = declMode;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public Long getLockTime() {
        return lockTime;
    }

    public void setLockTime(Long lockTime) {
        this.lockTime = lockTime;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Long getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Long modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getExportCode() {
        return exportCode;
    }

    public void setExportCode(String exportCode) {
        this.exportCode = exportCode;
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }

    public Long getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Long checkTime) {
        this.checkTime = checkTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}

