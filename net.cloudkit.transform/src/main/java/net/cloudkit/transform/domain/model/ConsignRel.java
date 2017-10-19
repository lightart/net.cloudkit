package net.cloudkit.transform.domain.model;

import java.util.Objects;

/**
 * CONSIGN_REL
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2015年08月26日 上午11:38:34
 */
public class ConsignRel {

	private static final long serialVersionUID = 1L;

	/** COMPANY_CODE 对应公司代码 所属用户公司代码 */
	private String companyCode;

	/** CONSIGN_REL_NO 流水号 */
	private String consignRelNo;

	/** BATCH_NO 批次号 */
	private String batchNo;

	/** PALT_NO 平台编号 */
	private String paltNo;

	/** CONSIGN_TYPE 委托类别 1、客户录单所属公司非报关行2、代理录单所属公司报关行3、客户自报经营单位与申报单位的海关代一致 */
	private Integer consignType;

	/** CONSIGN_MODE 1、自报 2、委托 */
	private Integer consignMode;

	/** CONSIGN_TIME 委托日期 */
	private Long consignTime;

	/** SHIP_NO 委托方代码 编码参考SHIPPER表 */
	private String shipNo;

	/** EXPORT_CODE 经营单位代码 */
	private String exportCode;

	/** EXPORT_NAME 经营单位名称 */
	private String exportName;

	/** SHIP_CODE 收发货单位代码 */
	private String shipCode;

	/** SHIP_NAME 收发货单位名称 */
	private String shipName;

	/** CREATE_COM_CODE 制单单位(录入单位) */
	private String createComCode;

	/** CREATE_COM_NAME 制单单位名称 */
	private String createComName;

	/** DECL_COM_CODE 申报单位代码 */
	private String declComCode;

	/** DECL_COM_NAME 申报单位名称 */
	private String declComName;

	/** STATUS 状态 1、待委托2、已委托3、取消委托 */
	private Integer status;

	/** SOURCE_PALT_NO 来源平台编号 */
	private String sourcePaltNo;

	/** SOURCE 制单类型(数据来源) 1、报关单2、清单3、公路舱单 */
	private Integer source;

	/** CONSIGN_ITEM 委托事项 1、报关单2、集报清单3、公路舱单4、海运舱单 */
	private Integer consignItem;

	/** 进出口方式 I-进口，E-出口 */
    // IE_TYPE
	private String ieType;

	/** 运输方式 */
    // TRANS_TYPE_CODE
	private String transTypeCode;

	/** 运输方式名称 */
    // TRANS_TYPE_NAME
	private String transTypeName;

	/** 目的国 */
    // DEST_COUNTRY_CODE
	private String destCountryCode;

	/** 目的国名称 */
    // DEST_COUNTRY_NAME
	private String destCountryName;

	/** 货物项数 */
    // TOTAL_NUM
	private Integer totalNum;

	/** 包装件数 */
    // TOTAL_PACKEGES
	private Integer totalPackeges;

	/** 毛重 */
    // TOTAL_GROSS_WEIGHT
	private Double totalGrossWeight;

	/** 净重 */
    // TOTAL_NET_WEIGHT
	private Double totalNetWeight;

	/** 总金额 */
    // TOTAL_AMOUNT
	private Double totalAmount;

	/** 币种 */
    // CURRENCY_CODE
	private String currencyCode;

	/** 币种名称 */
    // CURRENCY_NAME
	private String currencyName;

	/** 贸易方式（监管方式） */
    // TRADE_TYPE_CODE
	private String tradeTypeCode;

	/** 贸易方式名称 */
    // TRADE_TYPE_NAME
	private String tradeTypeName;

	/** 合同协议号 */
    // CONTR_NO
	private String contrNo;

	/** 进出口岸代码 */
    // IE_PORT
	private String iePort;

	/** 进出口岸名称 */
    // IE_PORT_NAME
	private String iePortName;

	/** 委托人 */
    // CONSIGN_USER
	private String consignUser;

	/** 委托确认人 */
    // CONSIGN_CFM_USER
	private String consignCfmUser;

	/** 委托确认日期 */
    // CONSIGN_CFM_TIME
	private Long consignCfmTime;

	/**预录入编号*/
    // PRE_INPUT_NO
	private String preInputNo;

	/**外商公司代码*/
    // FC_CODE
	private String fcCode;

	/**外商公司名称*/
    // FC_NAME
	private String fcName;

	/**GMY平台系统号 平台入库时自动编号*/
    // BUS_NO
	private String busNo;

	/**工作单号*/
    // WORK_NO
	private String workNo;

	/**客户委托号*/
    // CONSIGN_NO
	private String consignNo;

	/** 操作部门 */
    // OPS_DEPT
	private String opsDept;

	/** 接单备注 */
    // CONSIGN_REMARK
	private String consignRemark;

	/** 项目代码 */
    // PROJECT_NO
	private String projectNo;

	/** 客户代码 */
    // LINK_EXPORT_CODE
	private String linkExportCode;

	/** 客户名称、往来单位全称 */
    // LINK_EXPORT_NAME
	private String linkExportName;

	public String getPreInputNo() {
		return preInputNo;
	}

	public void setPreInputNo(String preInputNo) {
		this.preInputNo = preInputNo;
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

	public String getConsignCfmUser() {
		return consignCfmUser;
	}

	public void setConsignCfmUser(String consignCfmUser) {
		this.consignCfmUser = consignCfmUser;
	}

	public Long getConsignCfmTime() {
		return consignCfmTime;
	}

	public void setConsignCfmTime(Long consignCfmTime) {
		this.consignCfmTime = consignCfmTime;
	}

	public String getCompanyCode(){
		return companyCode;
	}

	public void setCompanyCode(String companyCode){
		this.companyCode=companyCode;
	}

	public String getConsignRelNo() {
		return consignRelNo;
	}

	public void setConsignRelNo(String consignRelNo) {
		this.consignRelNo = consignRelNo;
	}

	public String getBatchNo(){
		return batchNo;
	}

	public void setBatchNo(String batchNo){
		this.batchNo=batchNo;
	}

	public String getPaltNo(){
		return paltNo;
	}

	public void setPaltNo(String paltNo){
		this.paltNo=paltNo;
	}

	public Integer getConsignType(){
		return consignType;
	}

	public void setConsignType(Integer consignType){
		this.consignType=consignType;
	}

	public Integer getConsignMode() {
		return consignMode;
	}

	public void setConsignMode(Integer consignMode) {
		this.consignMode = consignMode;
	}

	public Long getConsignTime(){
		return consignTime;
	}

	public void setConsignTime(Long consignTime){
		this.consignTime=consignTime;
	}

	public String getShipNo(){
		return shipNo;
	}

	public void setShipNo(String shipNo){
		this.shipNo=shipNo;
	}

	public String getExportCode(){
		return exportCode;
	}

	public void setExportCode(String exportCode){
		this.exportCode=exportCode;
	}

	public String getExportName(){
		return exportName;
	}

	public void setExportName(String exportName){
		this.exportName=exportName;
	}

	public String getShipCode(){
		return shipCode;
	}

	public void setShipCode(String shipCode){
		this.shipCode=shipCode;
	}

	public String getShipName(){
		return shipName;
	}

	public void setShipName(String shipName){
		this.shipName=shipName;
	}

	public String getCreateComCode(){
		return createComCode;
	}

	public void setCreateComCode(String createComCode){
		this.createComCode=createComCode;
	}

	public String getCreateComName(){
		return createComName;
	}

	public void setCreateComName(String createComName){
		this.createComName=createComName;
	}

	public String getDeclComCode(){
		return declComCode;
	}

	public void setDeclComCode(String declComCode){
		this.declComCode=declComCode;
	}

	public String getDeclComName(){
		return declComName;
	}

	public void setDeclComName(String declComName){
		this.declComName=declComName;
	}

	public Integer getStatus(){
		return status;
	}

	public void setStatus(Integer status){
		this.status=status;
	}

	public String getSourcePaltNo() {
		return sourcePaltNo;
	}

	public void setSourcePaltNo(String sourcePaltNo) {
		this.sourcePaltNo = sourcePaltNo;
	}

	public Integer getSource(){
		return source;
	}

	public void setSource(Integer source){
		this.source=source;
	}

	public Integer getConsignItem(){
		return consignItem;
	}

	public void setConsignItem(Integer consignItem){
		this.consignItem=consignItem;
	}

	public String getIeType(){
		return ieType;
	}

	public void setIeType(String ieType){
		this.ieType=ieType;
	}

	public String getTransTypeCode(){
		return transTypeCode;
	}

	public void setTransTypeCode(String transTypeCode){
		this.transTypeCode=transTypeCode;
	}

	public String getTransTypeName(){
		return transTypeName;
	}

	public void setTransTypeName(String transTypeName){
		this.transTypeName=transTypeName;
	}

	public String getDestCountryCode(){
		return destCountryCode;
	}

	public void setDestCountryCode(String destCountryCode){
		this.destCountryCode=destCountryCode;
	}

	public String getDestCountryName(){
		return destCountryName;
	}

	public void setDestCountryName(String destCountryName){
		this.destCountryName=destCountryName;
	}

	public Integer getTotalNum(){
		return totalNum;
	}

	public void setTotalNum(Integer totalNum){
		this.totalNum=totalNum;
	}

	public Integer getTotalPackeges(){
		return totalPackeges;
	}

	public void setTotalPackeges(Integer totalPackeges){
		this.totalPackeges=totalPackeges;
	}

	public Double getTotalGrossWeight() {
		return totalGrossWeight;
	}

	public void setTotalGrossWeight(Double totalGrossWeight) {
		this.totalGrossWeight = totalGrossWeight;
	}

	public Double getTotalNetWeight(){
		return totalNetWeight;
	}

	public void setTotalNetWeight(Double totalNetWeight){
		this.totalNetWeight=totalNetWeight;
	}

	public Double getTotalAmount(){
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount){
		this.totalAmount=totalAmount;
	}

	public String getCurrencyCode(){
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode){
		this.currencyCode=currencyCode;
	}

	public String getCurrencyName(){
		return currencyName;
	}

	public void setCurrencyName(String currencyName){
		this.currencyName=currencyName;
	}

	public String getTradeTypeCode(){
		return tradeTypeCode;
	}

	public void setTradeTypeCode(String tradeTypeCode){
		this.tradeTypeCode=tradeTypeCode;
	}

	public String getTradeTypeName(){
		return tradeTypeName;
	}

	public void setTradeTypeName(String tradeTypeName){
		this.tradeTypeName=tradeTypeName;
	}

	public String getContrNo(){
		return contrNo;
	}

	public void setContrNo(String contrNo){
		this.contrNo=contrNo;
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

	public String getConsignUser() {
		return consignUser;
	}

	public void setConsignUser(String consignUser) {
		this.consignUser = consignUser;
	}

	public String getBusNo() {
		return busNo;
	}

	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}

	public String getWorkNo() {
		return workNo;
	}

	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}

	public String getConsignNo() {
		return consignNo;
	}

	public void setConsignNo(String consignNo) {
		this.consignNo = consignNo;
	}

	public String getOpsDept() {
		return opsDept;
	}

	public void setOpsDept(String opsDept) {
		this.opsDept = opsDept;
	}

	public String getConsignRemark() {
		return consignRemark;
	}

	public void setConsignRemark(String consignRemark) {
		this.consignRemark = consignRemark;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getLinkExportCode() {
		return linkExportCode;
	}

	public void setLinkExportCode(String linkExportCode) {
		this.linkExportCode = linkExportCode;
	}

	public String getLinkExportName() {
		return linkExportName;
	}

	public void setLinkExportName(String linkExportName) {
		this.linkExportName = linkExportName;
	}

}

