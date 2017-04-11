package net.cloudkit.transform.domain.model;

/**
 * DeclContainer.java
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2015年08月26日 上午11:38:34
 */
// DECL_CONTAINER
public class DeclContainer {

    private static final long serialVersionUID = 1L;

    /**
     * 平台统一编号
     */
    //  DECL_NO
    private String declNo;

    /**
     * 报关单海关编号
     */
    //  CUSTOMS_NO
    private String customsNo;

    /**
     * 集装箱编号
     */
    //  CONTA_NO
    private String contaNo;

    /**
     * 编码参考字典1
     */
    //  CONTA_SIZE
    private String contaSize;

    /**
     * 编码参考字典2
     */
    //  CONTA_TYPE
    private String contaType;

    /**
     * 集装箱自重
     */
    //  CONTA_GROSS_WEIGHT
    private Double contaGrossWeight;


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

    public String getContaNo() {
        return contaNo;
    }

    public void setContaNo(String contaNo) {
        this.contaNo = contaNo;
    }

    public String getContaSize() {
        return contaSize;
    }

    public void setContaSize(String contaSize) {
        this.contaSize = contaSize;
    }

    public String getContaType() {
        return contaType;
    }

    public void setContaType(String contaType) {
        this.contaType = contaType;
    }

    public Double getContaGrossWeight() {
        return contaGrossWeight;
    }

    public void setContaGrossWeight(Double contaGrossWeight) {
        this.contaGrossWeight = contaGrossWeight;
    }

}

