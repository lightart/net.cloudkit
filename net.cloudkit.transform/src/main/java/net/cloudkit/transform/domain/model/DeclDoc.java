package net.cloudkit.transform.domain.model;

/**
 * DeclDoc.java
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2015年08月26日 上午11:38:34
 */
// DECL_DOC
public class DeclDoc {

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
     * 编码参考字典1
     */
    // DOC_FRM
    private Integer docFrm;

    /**
     * 编码参考字典2
     */
    // DOC_TYPE
    private String docType;

    /**
     * 单证名称
     */
    // DOC_NAME
    private String docName;

    /**
     * 单证编号
     */
    // DOC_NO
    private String docNo;

    /**
     * 经营单位代码
     */
    // EXPORT_CODE
    private String exportCode;

    /**
     * 经营单位名称
     */
    // EXPORT_NAME
    private String exportName;

    /**
     * 文件路径
     */
    // DOC_PATH
    private String docPath;

    /**
     * 随附单据上传状态 10010未上传(初始状态)，10020已提交上传，10030上传成功，10040上传失败
     */
    // UPLOAD_STATUS
    private Integer uploadStatus;

    /**
     * 随附单据上传失败信息
     */
    // UPLOAD_ERROR_DETAIL
    private String uploadErrorDetail;

    /**
     * DOC_TYPE 0开头为US、1开头为S
     */
    // EDOC_FOMAT_TYPE
    private String edocFomatType;

    /**
     * F否、T是
     */
    // EDOCDECL_IS_REUPLOAD
    private String edocdeclIsReupload;

    public Integer getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(Integer uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getUploadErrorDetail() {
        return uploadErrorDetail;
    }

    public void setUploadErrorDetail(String uploadErrorDetail) {
        this.uploadErrorDetail = uploadErrorDetail;
    }

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

    public Integer getDocFrm() {
        return docFrm;
    }

    public void setDocFrm(Integer docFrm) {
        this.docFrm = docFrm;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getExportCode() {
        return exportCode;
    }

    public void setExportCode(String exportCode) {
        this.exportCode = exportCode;
    }

    public String getExportName() {
        return exportName;
    }

    public void setExportName(String exportName) {
        this.exportName = exportName;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public String getEdocFomatType() {
        return edocFomatType;
    }

    public void setEdocFomatType(String edocFomatType) {
        this.edocFomatType = edocFomatType;
    }

    public String getEdocdeclIsReupload() {
        return edocdeclIsReupload;
    }

    public void setEdocdeclIsReupload(String edocdeclIsReupload) {
        this.edocdeclIsReupload = edocdeclIsReupload;
    }

}

