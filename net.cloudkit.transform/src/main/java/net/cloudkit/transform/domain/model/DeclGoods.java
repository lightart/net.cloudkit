package net.cloudkit.transform.domain.model;

/**
 * DeclGoods.java
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2015年08月26日 上午11:38:34
 */
// DECL_GOODS
public class DeclGoods {

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
     * 商品序号
     */
    // GOODS_SEQ
    private Integer goodsSeq;

    /**
     * 备案序号（备案项号）
     */
    // GOODS_ITEM
    private String goodsItem;

    /**
     * 货号
     */
    // GOODS_NO
    private String goodsNo;

    /**
     * 商品编码1. 8位编码2. 2位附加码编码参考hs_code表
     */
    // HSCODE_TS
    private String hscodeTs;

    /**
     * 商品编码1. 8位编码2. 2位附加码编码参考hs_code表
     */
    // HSCODE_T
    private String hscodeT;

    /**
     * 商品编码1. 8位编码2. 2位附加码编码参考hs_code表
     */
    // HSCODE_S
    private String hscodeS;

    /**
     * 商品名称
     */
    // GOODS_NAME
    private String goodsName;

    /**
     * 版本号
     */
    // GOODS_VERSION
    private String goodsVersion;

    /**
     * 编码参考CRITERION_DECLARE表
     */
    // GOODS_MODEL
    private String goodsModel;

    /**
     * 牌子
     */
    // BRAND
    private String brand;

    /**
     * 包装件数
     */
    // PACKEGES
    private Double packeges;

    /**
     * 毛重
     */
    // GROSS_WEIGHT
    private Double grossWeight;

    /**
     * 净重
     */
    // NET_WEIGHT
    private Double netWeight;

    /**
     * 成交数量
     */
    // QTY
    private Double qty;

    /**
     * 编码参考base_data表base_code:unit_code
     */
    // UNIT
    private String unit;

    /**
     * 成交单位名称
     */
    // UNIT_NAME
    private String unitName;

    /**
     * 成交单价
     */
    // PRICE
    private Double price;

    /**
     * 成交总价
     */
    // AMOUNT
    private Double amount;

    /**
     * 编码参考base_data表base_code:currency_code
     */
    // CURRENCY
    private String currency;

    /**
     * 成交币种名称
     */
    // CURRENCY_NAME
    private String currencyName;

    /**
     * 法定数量
     */
    // QTY_1
    private Double qty1;

    /**
     * 编码参考base_data表base_code:unit_code
     */
    // UNIT_1
    private String unit1;

    /**
     * 法定单位名称
     */
    // UNIT_NAME_1
    private String unitName1;

    /**
     * 第二数量
     */
    // QTY_2
    private Double qty2;

    /**
     * 编码参考base_data表base_code:unit_code
     */
    // UNIT_2
    private String unit2;

    /**
     * 第二单位名称
     */
    // UNIT_NAME_2
    private String unitName2;

    /**
     * 编码参考base_data表base_code:country_code
     */
    // DESTINATION_CODE
    private String destinationCode;

    /**
     * 进口原产地/出口目的地名称
     */
    // DESTINATION_NAME
    private String destinationName;

    /**
     * 编码参考base_data表base_code:tax_type_code
     */
    // TAX_TYPE
    private String taxType;

    /**
     * 征免方式名称
     */
    // TAX_TYPE_NAME
    private String taxTypeName;

    /**
     * 编码参考base_data表base_code:use_code
     */
    // USE_TO
    private String useTo;

    /**
     * 用途描述
     */
    // USE_DESC
    private String useDesc;

    /**
     * 编码参考字典1
     */
    // IS_MIXED
    private String isMixed;

    /**
     * 原产国（出口）/ 消费国（进口）
     */
    // COUNTRY_OF_ORIGIN_NAME
    private String countryOfOriginName;

    /**
     * 原产国（出口）/ 消费国（进口）
     */
    // COUNTRY_OF_ORIGIN_CODE
    private String countryOfOriginCode;

    public String getCountryOfOriginCode() {
        return countryOfOriginCode;
    }

    public void setCountryOfOriginCode(String countryOfOriginCode) {
        this.countryOfOriginCode = countryOfOriginCode;
    }

    public String getCountryOfOriginName() {
        return countryOfOriginName;
    }

    public void setCountryOfOriginName(String countryOfOriginName) {
        this.countryOfOriginName = countryOfOriginName;
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

    public Integer getGoodsSeq() {
        return goodsSeq;
    }

    public void setGoodsSeq(Integer goodsSeq) {
        this.goodsSeq = goodsSeq;
    }

    public String getGoodsItem() {
        return goodsItem;
    }

    public void setGoodsItem(String goodsItem) {
        this.goodsItem = goodsItem;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getHscodeTs() {
        return hscodeTs;
    }

    public void setHscodeTs(String hscodeTs) {
        this.hscodeTs = hscodeTs;
    }

    public String getHscodeT() {
        return hscodeT;
    }

    public void setHscodeT(String hscodeT) {
        this.hscodeT = hscodeT;
    }

    public String getHscodeS() {
        return hscodeS;
    }

    public void setHscodeS(String hscodeS) {
        this.hscodeS = hscodeS;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsVersion() {
        return goodsVersion;
    }

    public void setGoodsVersion(String goodsVersion) {
        this.goodsVersion = goodsVersion;
    }

    public String getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPackeges() {
        return packeges;
    }

    public void setPackeges(Double packeges) {
        this.packeges = packeges;
    }

    public Double getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(Double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public Double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Double netWeight) {
        this.netWeight = netWeight;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Double getQty1() {
        return qty1;
    }

    public void setQty1(Double qty1) {
        this.qty1 = qty1;
    }

    public String getUnit1() {
        return unit1;
    }

    public void setUnit1(String unit1) {
        this.unit1 = unit1;
    }

    public String getUnitName1() {
        return unitName1;
    }

    public void setUnitName1(String unitName1) {
        this.unitName1 = unitName1;
    }

    public Double getQty2() {
        return qty2;
    }

    public void setQty2(Double qty2) {
        this.qty2 = qty2;
    }

    public String getUnit2() {
        return unit2;
    }

    public void setUnit2(String unit2) {
        this.unit2 = unit2;
    }

    public String getUnitName2() {
        return unitName2;
    }

    public void setUnitName2(String unitName2) {
        this.unitName2 = unitName2;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getTaxTypeName() {
        return taxTypeName;
    }

    public void setTaxTypeName(String taxTypeName) {
        this.taxTypeName = taxTypeName;
    }

    public String getUseTo() {
        return useTo;
    }

    public void setUseTo(String useTo) {
        this.useTo = useTo;
    }

    public String getUseDesc() {
        return useDesc;
    }

    public void setUseDesc(String useDesc) {
        this.useDesc = useDesc;
    }

    public String getIsMixed() {
        return isMixed;
    }

    public void setIsMixed(String isMixed) {
        this.isMixed = isMixed;
    }
}

