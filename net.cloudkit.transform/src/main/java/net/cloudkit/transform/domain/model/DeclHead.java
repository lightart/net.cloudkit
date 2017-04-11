package net.cloudkit.transform.domain.model;

/**
 * DeclHead.java
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2015年08月26日 上午11:38:34
 */
// DECL_HEAD
public class DeclHead {

    private static final long serialVersionUID = 1L;

    /**
     * 平台统一编号
     */
    // DECL_NO
    private String declNo;

    /**
     * 报关单预录入编号
     */
    // PRE_CUSTOMS_NO
    private String preCustomsNo;

    /**
     * 报关单海关编号
     */
    // CUSTOMS_NO
    private String customsNo;

    /**
     * 电子口岸统一编号
     */
    // EPROT_NO
    private String eprotNo;

    /**
     * 录单编号
     */
    // PRE_INPUT_NO
    private String preInputNo;

    /**
     * I-进口，E-出口
     */
    // IE_TYPE
    private String ieType;

    /**
     * 编码参考字典1
     */
    // DECL_TYPE
    private String declType;

    /**
     * 进口：进口日期，出口：出口日期
     */
    // IE_DATE
    private Long ieDate;

    /**
     * 申报日期
     */
    // DECL_DATE
    private Long declDate;

    /**
     * 编码参考base_data表base_code:custom_code
     */
    // DECL_PORT
    private String declPort;

    /**
     * 申报口岸名称
     */
    // DECL_PORT_NAME
    private String declPortName;

    /**
     * 编码参考base_data表base_code:custom_code
     */
    // IE_PORT
    private String iePort;

    /**
     * 进出口岸名称
     */
    // IE_PORT_NAME
    private String iePortName;

    /**
     * 主管海关
     */
    // REG_CUS_CODE
    private String regCusCode;

    /**
     * 主管海关名称
     */
    // REG_CUS_NAME
    private String regCusName;

    /**
     * 备案号：12位数字+字母
     */
    // HAND_BOOK_NO
    private String handBookNo;

    /**
     * 合同协议号
     */
    // CONTR_NO
    private String contrNo;

    /**
     * 编码参考SHIPPER表
     */
    // SHIP_NO
    private String shipNo;

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
     * 加工单位编码（进口：收货单位，出口：发货单位）
     */
    // SHIP_CODE
    private String shipCode;

    /**
     * 收发货单位名称
     */
    // SHIP_NAME
    private String shipName;

    /**
     * 编码参考FOREIGN_COMPAN表
     */
    // FC_CODE
    private String fcCode;

    /**
     * 单位名称（外商公司）
     */
    // FC_NAME
    private String fcName;

    /**
     * 分销中心代码
     */
    // RCV_CODE
    private String rcvCode;

    /**
     * 分销中心类别
     */
    // RCV_TYPE
    private String rcvType;

    /**
     * 申报单位代码
     */
    // DECL_COM_CODE
    private String declComCode;

    /**
     * 申报单位名称
     */
    // DECL_COM_NAME
    private String declComName;

    /**
     * 编码参考base_data表base_code: trans_type_code
     */
    // TRANS_TYPE_CODE
    private String transTypeCode;

    /**
     * 运输方式名称
     */
    // TRANS_TYPE_NAME
    private String transTypeName;

    /**
     * 运输工具
     */
    // TRANS_TOOL_NAME
    private String transToolName;

    /**
     * 航次号
     */
    // VOYAGE_NO
    private String voyageNo;

    /**
     * 提运单号
     */
    // BILL_NO
    private String billNo;

    /**
     * 进口：启运国（地区），出口：运抵国（地区）编码参考base_data表base_code: COUNTRY_CODE
     */
    // COUNTRY_CODE
    private String countryCode;

    /**
     * 运抵国家名称
     */
    // COUNTRY_NAME
    private String countryName;

    /**
     * 进口：装货港 ，出口：指运港编码参考base_data表base_code: PORT_CODE
     */
    // DEST_PORT_CODE
    private String destPortCode;

    /**
     * 指运港名称
     */
    // DEST_PORT_NAME
    private String destPortName;

    /**
     * 编码参考base_data表base_code: COUNTRY_CODE
     */
    // DEST_COUNTRY_CODE
    private String destCountryCode;

    /**
     * 目的国名称
     */
    // DEST_COUNTRY_NAME
    private String destCountryName;

    /**
     * 进口：境内目的地 ，出口：境内货源地编码参考base_data表base_code: REGION_CODE
     */
    // REGION_CODE
    private String regionCode;

    /**
     * 境内货源地名称
     */
    // REGION_NAME
    private String regionName;

    /**
     * 编码参考base_data表base_code: TRADE_TYPE_CODE
     */
    // TRADE_TYPE_CODE
    private String tradeTypeCode;

    /**
     * 贸易方式名称
     */
    // TRADE_TYPE_NAME
    private String tradeTypeName;

    /**
     * 编码参考base_data表base_code: TAX_KINDS_CODE
     */
    // TAX_KINDS_CODE
    private String taxKindsCode;

    /**
     * 征免性质名称
     */
    // TAX_KINDS_NAME
    private String taxKindsName;

    /**
     * 征税比例
     */
    // TAX_RATIO
    private Double taxRatio;

    /**
     * 编码参考base_data表base_code: Tax_com_CODE
     */
    // TAX_UNIT_CODE
    private String taxUnitCode;

    /**
     * 纳税单位名称
     */
    // TAX_UNIT_NAME
    private String taxUnitName;

    /**
     * 编码参考base_data表base_code: TAX_TYPE_CODE
     */
    // TAX_TYPE_CODE
    private String taxTypeCode;

    /**
     * 征免方式名称
     */
    // TAX_TYPE_NAME
    private String taxTypeName;

    /**
     * 编码参考base_data表base_code: TRADE_TERMS_CODE
     */
    // TRADE_TERMS_CODE
    private String tradeTermsCode;

    /**
     * 成交方式名称
     */
    // TRADE_TERMS_NAME
    private String tradeTermsName;

    /**
     * 编码参考base_data表base_code: PAYMENT_CODE
     */
    // PAYMENT_CODE
    private String paymentCode;

    /**
     * 结汇方式名称
     */
    // PAYMENT_NAME
    private String paymentName;

    /**
     * 编码参考base_data表base_code: CURRENCY_CODE
     */
    // CURRENCY_CODE
    private String currencyCode;

    /**
     * 成交币种名称
     */
    // CURRENCY_NAME
    private String currencyName;

    /**
     * 许可证号
     */
    // LICENCE_NO
    private String licenceNo;

    /**
     * 批准文号
     */
    // APPROVE_NO
    private String approveNo;

    /**
     * 编码参考字典2
     */
    // FEE_MARK
    private String feeMark;

    /**
     * 编码参考base_data表base_code:currency_code
     */
    // FEE_CURR
    private String feeCurr;

    /**
     * 运费币制名称
     */
    // FEE_CURR_NAME
    private String feeCurrName;

    /**
     * 运费率
     */
    // FEE_RATE
    private Double feeRate;

    /**
     * 编码参考字典2
     */
    // INSUR_MARK
    private String insurMark;

    /**
     * 编码参考base_data表base_code:currency_code
     */
    // INSUR_CURR
    private String insurCurr;

    /**
     * 保费币制名称
     */
    // INSUR_CURR_NAME
    private String insurCurrName;

    /**
     * 保费率
     */
    // INSUR_RATE
    private Double insurRate;

    /**
     * 编码参考字典3
     */
    // ADDIT_EXPEN_MARK
    private String additExpenMark;

    /**
     * 编码参考base_data表base_code:currency_code
     */
    // ADDIT_EXPEN_CURR
    private String additExpenCurr;

    /**
     * 杂费币制名称
     */
    // ADDIT_EXPEN_CURR_NAME
    private String additExpenCurrName;

    /**
     * 杂费率
     */
    // ADDIT_EXPEN_RATE
    private Double additExpenRate;

    /**
     * 编码参考base_data表base_code: package_type_code
     */
    // PACKAGE_TYPE_CODE
    private String packageTypeCode;

    /**
     * 包装种类名称
     */
    // PACKAGE_TYPE_NAME
    private String packageTypeName;


    /**
     * 货物总金额
     */
    // TOTAL_AMOUNT
    private Double totalAmount;

    /**
     * 包装件数
     */
    // TOTAL_PACKEGES
    private Integer totalPackeges;

    /**
     * 毛重
     */
    // TOTAL_GROSS_WEIGHT
    private Double totalGrossWeight;

    /**
     * 净重
     */
    // TOTAL_NET_WEIGHT
    private Double totalNetWeight;

    /**
     * 关联报关单
     */
    // RELATIVE_DECL_ID
    private String relativeDeclId;

    /**
     * 关联备案
     */
    // RELATIVE_EMS_NO
    private String relativeEmsNo;

    /**
     * 监管仓号
     */
    // BONDED_NO
    private String bondedNo;

    /**
     * 货场代码
     */
    // YARD_NO
    private String yardNo;

    /**
     * 成品料件标志
     */
    // PRODUCT_FLAG
    private Integer productFlag;

    /**
     * 用途 编码参考base_data表base_code:use_code
     */
    // USE_TO
    private String useTo;

    /**
     * 用途描述
     */
    // USE_DESC
    private String useDesc;

    /**
     * 报关员
     */
    // DECLARER_NO
    private String declarerNo;

    /**
     * 联系方式
     */
    // DECLARER_CONTACT
    private String declarerContact;

    /**
     * 关联报关单
     */
    // FACTORY
    private String factory;

    /**
     * 模拟关税
     */
    // PRE_DUTY
    private Double preDuty;

    /**
     * 备注
     */
    // REMARK
    private String remark;

    /**
     * 数据来源 1、平台录入  2、平台转存 3、外部接口
     */
    // SOURCES
    private Integer sources;

    /**
     * 第三接入企业单号
     */
    // WS_LINK_NO
    private String wsLinkNO;

    /**
     * 关检关联号
     */
    // CUS_INSPE_NO
    private String cusInspeNo;

    /**
     * 商品项数
     */
    // GOODS_NUM
    private Integer goodsNum;

    /**
     * 工缴费
     */
    // PROCESSING_FEES
    private Double processingFees;

    /**
     * 经营单位信用代码
     */
    // EXPORT_CREDIT_CODE
    private String exportCreditCode;

    /**
     * 收发货单位信用代码
     */
    // SHIP_CREDIT_CODE
    private String shipCreditCode;

    /**
     * 申报单位信用代码
     */
    // DECL_CREDIT_CODE
    private String declCreditCode;

    /**
     * 价格影响确认:1、是；0、否
     */
    // PRICE_IMPACT
    private Integer priceImpact;

    /**
     * 支付特许权使用费确认:1、是；0、否
     */
    // PAY_FRANCHISE
    private Integer payFranchise;

    /**
     * 特殊关系确认:1、是；0、否
     */
    // SPECIAL_RELATION
    private Integer specialRelation;

    /**
     * 贸易国别(等于运抵国)
     */
    // TRADE_COUNTRY_NAME
    private String tradeCountryName;

    /**
     * 贸易国别(等于运抵国)
     */
    // TRADE_COUNTRY_CODE
    private String tradeCountryCode;

    //外部接口客服数据
    // SOURCES_CLIENT
    private Integer sourcesClient;

    //录入员IC卡号
    // INPUTER_IC_NO
    private String inputerIcNo;


    /**
     * 是否转关单：1、是；2否
     */
    // IS_TRANSIT
    private Integer isTransit;
    // TRN_PRE_ID
    private String trnPreId;

    /**
     * 业务类型 0/空：报关单 1：备案清单,2:普惠，3：汇总征税
     */
    // BUS_TYPE
    private Integer busType;

    /**
     * 1.暂存QP，2.申报，3.暂存申报一体 ,4.制单
     */
    // QP_TASK_ACTION_TYPE
    private Integer qpTaskActionType;

    /**
     * 汇总征税提示信息
     */
    // COLLECT_TAX_TIP
    private String collectTaxTip;

    /**
     * 普惠提示信息
     */
    // AGREEMENT_RATE_TIP
    private String agreementRateTip;

    /**
     * 结关日期
     */
    // CLEARANCE_DATE
    private Long clearanceDate;

    /**
     * 是否汇总征税 1：是，2否
     */
    // IS_COLLECT_TAX
    private Integer isCollectTax;

    /**
     * 是否清单: 1是，0否
     */
    // IS_CDS
    private Integer isCds;

    /**
     * 大清单号，小清单号为电子口岸统一编号eprot_no
     */
    // CDS_NO
    private String cdsNo;

    /**
     * 报关单关联单证，null或1不关联其他单证，2关联公路舱单，3关联海运舱单
     */
    // RELATION_TYPE
    private Integer relationType;

    /**
     * 海关EDI标识: 默认为 1 （普通报关单） ，运输工具 @开头 的转关 = 5  运输工具 空白 的转关 = 3
     */
    // EDI_ID
    private Integer ediId;

    /**
     * 关检号
     */
    // INV_NO
    private String invNo;

    /**
     * 关检密码
     */
    // INV_PWD
    private String invPwd;

    /**
     * 装货港代码
     */
    // LOADING_PORT_CODE
    private String loadingPortCode;

    /**
     * 装货港名称
     */
    // LOADING_PORT_NAME
    private String loadingPortName;

    public String getLoadingPortCode() {
        return loadingPortCode;
    }

    public void setLoadingPortCode(String loadingPortCode) {
        this.loadingPortCode = loadingPortCode;
    }

    public String getLoadingPortName() {
        return loadingPortName;
    }

    public void setLoadingPortName(String loadingPortName) {
        this.loadingPortName = loadingPortName;
    }

    public String getInvNo() {
        return invNo;
    }

    public void setInvNo(String invNo) {
        this.invNo = invNo;
    }

    public String getInvPwd() {
        return invPwd;
    }

    public void setInvPwd(String invPwd) {
        this.invPwd = invPwd;
    }

    public Integer getEdiId() {
        return ediId;
    }

    public void setEdiId(Integer ediId) {
        this.ediId = ediId;
    }

    public Integer getRelationType() {
        return relationType;
    }

    public void setRelationType(Integer relationType) {
        this.relationType = relationType;
    }

    public Integer getIsCds() {
        return isCds;
    }

    public void setIsCds(Integer isCds) {
        this.isCds = isCds;
    }

    public String getCdsNo() {
        return cdsNo;
    }

    public void setCdsNo(String cdsNo) {
        this.cdsNo = cdsNo;
    }

    public Integer getIsCollectTax() {
        return isCollectTax;
    }

    public void setIsCollectTax(Integer isCollectTax) {
        this.isCollectTax = isCollectTax;
    }

    public Long getClearanceDate() {
        return clearanceDate;
    }

    public void setClearanceDate(Long clearanceDate) {
        this.clearanceDate = clearanceDate;
    }

    public String getCollectTaxTip() {
        return collectTaxTip;
    }

    public void setCollectTaxTip(String collectTaxTip) {
        this.collectTaxTip = collectTaxTip;
    }

    public String getAgreementRateTip() {
        return agreementRateTip;
    }

    public void setAgreementRateTip(String agreementRateTip) {
        this.agreementRateTip = agreementRateTip;
    }

    public Integer getQpTaskActionType() {
        return qpTaskActionType;
    }

    public void setQpTaskActionType(Integer qpTaskActionType) {
        this.qpTaskActionType = qpTaskActionType;
    }

    public Integer getBusType() {
        return busType;
    }

    public void setBusType(Integer busType) {
        this.busType = busType;
    }

    public String getTrnPreId() {
        return trnPreId;
    }

    public void setTrnPreId(String trnPreId) {
        this.trnPreId = trnPreId;
    }

    public Integer getIsTransit() {
        return isTransit;
    }

    public void setIsTransit(Integer isTransit) {
        this.isTransit = isTransit;
    }

    public Integer getSourcesClient() {
        return sourcesClient;
    }

    public void setSourcesClient(Integer sourcesClient) {
        this.sourcesClient = sourcesClient;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getCusInspeNo() {
        return cusInspeNo;
    }

    public void setCusInspeNo(String cusInspeNo) {
        this.cusInspeNo = cusInspeNo;
    }

    public String getWsLinkNO() {
        return wsLinkNO;
    }

    public void setWsLinkNO(String wsLinkNO) {
        this.wsLinkNO = wsLinkNO;
    }

    public String getDeclNo() {
        return declNo;
    }

    public void setDeclNo(String declNo) {
        this.declNo = declNo;
    }

    public String getPreCustomsNo() {
        return preCustomsNo;
    }

    public void setPreCustomsNo(String preCustomsNo) {
        this.preCustomsNo = preCustomsNo;
    }

    public String getCustomsNo() {
        return customsNo;
    }

    public void setCustomsNo(String customsNo) {
        this.customsNo = customsNo;
    }

    public String getEprotNo() {
        return eprotNo;
    }

    public void setEprotNo(String eprotNo) {
        this.eprotNo = eprotNo;
    }

    public String getPreInputNo() {
        return preInputNo;
    }

    public void setPreInputNo(String preInputNo) {
        this.preInputNo = preInputNo;
    }

    public String getIeType() {
        return ieType;
    }

    public void setIeType(String ieType) {
        this.ieType = ieType;
    }

    public String getDeclType() {
        return declType;
    }

    public void setDeclType(String declType) {
        this.declType = declType;
    }

    public Long getIeDate() {
        return ieDate;
    }

    public void setIeDate(Long ieDate) {
        this.ieDate = ieDate;
    }

    public Long getDeclDate() {
        return declDate;
    }

    public void setDeclDate(Long declDate) {
        this.declDate = declDate;
    }

    public String getDeclPort() {
        return declPort;
    }

    public void setDeclPort(String declPort) {
        this.declPort = declPort;
    }

    public String getDeclPortName() {
        return declPortName;
    }

    public void setDeclPortName(String declPortName) {
        this.declPortName = declPortName;
    }

    public String getIePort() {
        return iePort;
    }

    public void setIePort(String iePort) {
        this.iePort = iePort;
    }

    public String getIePortName() {
        return iePortName;
    }

    public void setIePortName(String iePortName) {
        this.iePortName = iePortName;
    }

    public String getRegCusCode() {
        return regCusCode;
    }

    public void setRegCusCode(String regCusCode) {
        this.regCusCode = regCusCode;
    }

    public String getRegCusName() {
        return regCusName;
    }

    public void setRegCusName(String regCusName) {
        this.regCusName = regCusName;
    }

    public String getHandBookNo() {
        return handBookNo;
    }

    public void setHandBookNo(String handBookNo) {
        this.handBookNo = handBookNo;
    }

    public String getContrNo() {
        return contrNo;
    }

    public void setContrNo(String contrNo) {
        this.contrNo = contrNo;
    }

    public String getShipNo() {
        return shipNo;
    }

    public void setShipNo(String shipNo) {
        this.shipNo = shipNo;
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

    public String getShipCode() {
        return shipCode;
    }

    public void setShipCode(String shipCode) {
        this.shipCode = shipCode;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getFcCode() {
        return fcCode;
    }

    public void setFcCode(String fcCode) {
        this.fcCode = fcCode;
    }

    public String getFcName() {
        return fcName;
    }

    public void setFcName(String fcName) {
        this.fcName = fcName;
    }

    public String getRcvCode() {
        return rcvCode;
    }

    public void setRcvCode(String rcvCode) {
        this.rcvCode = rcvCode;
    }

    public String getRcvType() {
        return rcvType;
    }

    public void setRcvType(String rcvType) {
        this.rcvType = rcvType;
    }

    public String getDeclComCode() {
        return declComCode;
    }

    public void setDeclComCode(String declComCode) {
        this.declComCode = declComCode;
    }

    public String getDeclComName() {
        return declComName;
    }

    public void setDeclComName(String declComName) {
        this.declComName = declComName;
    }

    public String getTransTypeCode() {
        return transTypeCode;
    }

    public void setTransTypeCode(String transTypeCode) {
        this.transTypeCode = transTypeCode;
    }

    public String getTransTypeName() {
        return transTypeName;
    }

    public void setTransTypeName(String transTypeName) {
        this.transTypeName = transTypeName;
    }

    public String getTransToolName() {
        return transToolName;
    }

    public void setTransToolName(String transToolName) {
        this.transToolName = transToolName;
    }

    public String getVoyageNo() {
        return voyageNo;
    }

    public void setVoyageNo(String voyageNo) {
        this.voyageNo = voyageNo;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getDestPortCode() {
        return destPortCode;
    }

    public void setDestPortCode(String destPortCode) {
        this.destPortCode = destPortCode;
    }

    public String getDestPortName() {
        return destPortName;
    }

    public void setDestPortName(String destPortName) {
        this.destPortName = destPortName;
    }

    public String getDestCountryCode() {
        return destCountryCode;
    }

    public void setDestCountryCode(String destCountryCode) {
        this.destCountryCode = destCountryCode;
    }

    public String getDestCountryName() {
        return destCountryName;
    }

    public void setDestCountryName(String destCountryName) {
        this.destCountryName = destCountryName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getTradeTypeCode() {
        return tradeTypeCode;
    }

    public void setTradeTypeCode(String tradeTypeCode) {
        this.tradeTypeCode = tradeTypeCode;
    }

    public String getTradeTypeName() {
        return tradeTypeName;
    }

    public void setTradeTypeName(String tradeTypeName) {
        this.tradeTypeName = tradeTypeName;
    }

    public String getTaxKindsCode() {
        return taxKindsCode;
    }

    public void setTaxKindsCode(String taxKindsCode) {
        this.taxKindsCode = taxKindsCode;
    }

    public String getTaxKindsName() {
        return taxKindsName;
    }

    public void setTaxKindsName(String taxKindsName) {
        this.taxKindsName = taxKindsName;
    }

    public Double getTaxRatio() {
        return taxRatio;
    }

    public void setTaxRatio(Double taxRatio) {
        this.taxRatio = taxRatio;
    }

    public String getTaxUnitCode() {
        return taxUnitCode;
    }

    public void setTaxUnitCode(String taxUnitCode) {
        this.taxUnitCode = taxUnitCode;
    }

    public String getTaxUnitName() {
        return taxUnitName;
    }

    public void setTaxUnitName(String taxUnitName) {
        this.taxUnitName = taxUnitName;
    }

    public String getTaxTypeCode() {
        return taxTypeCode;
    }

    public void setTaxTypeCode(String taxTypeCode) {
        this.taxTypeCode = taxTypeCode;
    }

    public String getTaxTypeName() {
        return taxTypeName;
    }

    public void setTaxTypeName(String taxTypeName) {
        this.taxTypeName = taxTypeName;
    }

    public String getTradeTermsCode() {
        return tradeTermsCode;
    }

    public void setTradeTermsCode(String tradeTermsCode) {
        this.tradeTermsCode = tradeTermsCode;
    }

    public String getTradeTermsName() {
        return tradeTermsName;
    }

    public void setTradeTermsName(String tradeTermsName) {
        this.tradeTermsName = tradeTermsName;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }

    public String getApproveNo() {
        return approveNo;
    }

    public void setApproveNo(String approveNo) {
        this.approveNo = approveNo;
    }

    public String getFeeMark() {
        return feeMark;
    }

    public void setFeeMark(String feeMark) {
        this.feeMark = feeMark;
    }

    public String getFeeCurr() {
        return feeCurr;
    }

    public void setFeeCurr(String feeCurr) {
        this.feeCurr = feeCurr;
    }

    public String getFeeCurrName() {
        return feeCurrName;
    }

    public void setFeeCurrName(String feeCurrName) {
        this.feeCurrName = feeCurrName;
    }

    public Double getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(Double feeRate) {
        this.feeRate = feeRate;
    }

    public String getInsurMark() {
        return insurMark;
    }

    public void setInsurMark(String insurMark) {
        this.insurMark = insurMark;
    }

    public String getInsurCurr() {
        return insurCurr;
    }

    public void setInsurCurr(String insurCurr) {
        this.insurCurr = insurCurr;
    }

    public String getInsurCurrName() {
        return insurCurrName;
    }

    public void setInsurCurrName(String insurCurrName) {
        this.insurCurrName = insurCurrName;
    }

    public Double getInsurRate() {
        return insurRate;
    }

    public void setInsurRate(Double insurRate) {
        this.insurRate = insurRate;
    }

    public String getAdditExpenMark() {
        return additExpenMark;
    }

    public void setAdditExpenMark(String additExpenMark) {
        this.additExpenMark = additExpenMark;
    }

    public String getAdditExpenCurr() {
        return additExpenCurr;
    }

    public void setAdditExpenCurr(String additExpenCurr) {
        this.additExpenCurr = additExpenCurr;
    }

    public String getAdditExpenCurrName() {
        return additExpenCurrName;
    }

    public void setAdditExpenCurrName(String additExpenCurrName) {
        this.additExpenCurrName = additExpenCurrName;
    }

    public Double getAdditExpenRate() {
        return additExpenRate;
    }

    public void setAdditExpenRate(Double additExpenRate) {
        this.additExpenRate = additExpenRate;
    }

    public String getPackageTypeCode() {
        return packageTypeCode;
    }

    public void setPackageTypeCode(String packageTypeCode) {
        this.packageTypeCode = packageTypeCode;
    }

    public String getPackageTypeName() {
        return packageTypeName;
    }

    public void setPackageTypeName(String packageTypeName) {
        this.packageTypeName = packageTypeName;
    }

    public Integer getTotalPackeges() {
        return totalPackeges;
    }

    public void setTotalPackeges(Integer totalPackeges) {
        this.totalPackeges = totalPackeges;
    }

    public Double getTotalGrossWeight() {
        return totalGrossWeight;
    }

    public void setTotalGrossWeight(Double totalGrossWeight) {
        this.totalGrossWeight = totalGrossWeight;
    }

    public Double getTotalNetWeight() {
        return totalNetWeight;
    }

    public void setTotalNetWeight(Double totalNetWeight) {
        this.totalNetWeight = totalNetWeight;
    }

    public String getRelativeDeclId() {
        return relativeDeclId;
    }

    public void setRelativeDeclId(String relativeDeclId) {
        this.relativeDeclId = relativeDeclId;
    }

    public String getRelativeEmsNo() {
        return relativeEmsNo;
    }

    public void setRelativeEmsNo(String relativeEmsNo) {
        this.relativeEmsNo = relativeEmsNo;
    }

    public String getBondedNo() {
        return bondedNo;
    }

    public void setBondedNo(String bondedNo) {
        this.bondedNo = bondedNo;
    }

    public String getYardNo() {
        return yardNo;
    }

    public void setYardNo(String yardNo) {
        this.yardNo = yardNo;
    }

    public Integer getProductFlag() {
        return productFlag;
    }

    public void setProductFlag(Integer productFlag) {
        this.productFlag = productFlag;
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

    public String getDeclarerNo() {
        return declarerNo;
    }

    public void setDeclarerNo(String declarerNo) {
        this.declarerNo = declarerNo;
    }

    public String getDeclarerContact() {
        return declarerContact;
    }

    public void setDeclarerContact(String declarerContact) {
        this.declarerContact = declarerContact;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public Double getPreDuty() {
        return preDuty;
    }

    public void setPreDuty(Double preDuty) {
        this.preDuty = preDuty;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSources() {
        return sources;
    }

    public void setSources(Integer sources) {
        this.sources = sources;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getProcessingFees() {
        return processingFees;
    }

    public void setProcessingFees(Double processingFees) {
        this.processingFees = processingFees;
    }

    public String getExportCreditCode() {
        return exportCreditCode;
    }

    public void setExportCreditCode(String exportCreditCode) {
        this.exportCreditCode = exportCreditCode;
    }

    public String getShipCreditCode() {
        return shipCreditCode;
    }

    public void setShipCreditCode(String shipCreditCode) {
        this.shipCreditCode = shipCreditCode;
    }

    public String getDeclCreditCode() {
        return declCreditCode;
    }

    public void setDeclCreditCode(String declCreditCode) {
        this.declCreditCode = declCreditCode;
    }

    public Integer getPriceImpact() {
        return priceImpact;
    }

    public void setPriceImpact(Integer priceImpact) {
        this.priceImpact = priceImpact;
    }

    public Integer getPayFranchise() {
        return payFranchise;
    }

    public void setPayFranchise(Integer payFranchise) {
        this.payFranchise = payFranchise;
    }

    public Integer getSpecialRelation() {
        return specialRelation;
    }

    public void setSpecialRelation(Integer specialRelation) {
        this.specialRelation = specialRelation;
    }

    public String getTradeCountryName() {
        return tradeCountryName;
    }

    public void setTradeCountryName(String tradeCountryName) {
        this.tradeCountryName = tradeCountryName;
    }

    public String getTradeCountryCode() {
        return tradeCountryCode;
    }

    public void setTradeCountryCode(String tradeCountryCode) {
        this.tradeCountryCode = tradeCountryCode;
    }

    public String getInputerIcNo() {
        return inputerIcNo;
    }

    public void setInputerIcNo(String inputerIcNo) {
        this.inputerIcNo = inputerIcNo;
    }
}

