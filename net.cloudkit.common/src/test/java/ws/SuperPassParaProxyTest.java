package ws;

import org.apache.commons.compress.compressors.deflate.DeflateCompressorInputStream;

import javax.xml.namespace.QName;
import javax.xml.ws.Holder;
import javax.xml.ws.Service;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.UUID;

/**
 * QP 参数查询
 */
public class SuperPassParaProxyTest {

    public static void main(String[] args) throws Exception {


        // CodeLists_Const不在数据库中，靠本XML文件直接初始化
        // MFT2008更改原因代码 RMFT8ChangeReasonCode (AlphaNumber)
        // 001	货物漏装
        // 002	临时拉货
        // 003	错装、多装货物
        // 004	因疏忽而造成的舱单必填数据元差错
        // 005	更正因疏忽而造成的舱单选填数据元差错
        // 006	补充因疏忽而造成的舱单选填数据元遗漏
        // 007	更正因代理人等外部因素而造成的舱单必填数据元差错
        // 008	更正因代理人等外部因素而造成的舱单选填数据元差错
        // 009	补充因代理人等外部因素而造成的舱单选填数据元遗漏
        // 010	重复的舱单数据
        // 011	上一港口漏卸的货物
        // 012	在本监管场所错卸的货物
        // 013	货物在进入海关监管场所后失窃
        // 014	货物在进入海关监管场所后失踪
        // 015	根据案件处理结果修改
        // 999	其他
        //
        // MFT2008申报类型 RMFT8DeclareTypeCode (AlphaNumber)
        // MT1401	进口原始舱单
        // MT2401	出口预配舱单
        // MT5401	进口理货报告
        // MT5402	出口理货报告
        // MT3402	运抵报告
        // MT7402	出口装箱清单
        // MT8401	进口落装申请
        // MT8402	出口落装申请
        // MT8403	进口落装改配申请
        // MT8404	出口落装改配申请
        // MT4401	载货运输工具进境承运确报申请
        // MT4402	载货运输工具出境承运确报申请
        // MT4403	空载运输工具进境承运确报申请
        // MT4404	空载运输工具出境承运确报申请
        // MT4405	空集装箱运输工具进境承运确报申请
        // MT4406	空集装箱运输工具出境承运确报申请
        //
        // MFT2008申报类型 RMFT8DeclareTypeCodeStat (AlphaNumber)
        // MT1401	进口原始舱单
        // MT2401	出口预配舱单
        // MT5401	进口理货报告
        // MT5402	出口理货报告
        // MT3402	运抵报告
        // MT7402	出口装箱清单
        // MT8401	进口落装申请
        // MT8402	出口落装申请
        // MT8403	进口落装改配申请
        // MT8404	出口落装改配申请
        // MT4401	载货运输工具进境承运确报申请
        // MT4402	载货运输工具出境承运确报申请
        // MT4403	空载运输工具进境承运确报申请
        // MT4404	空载运输工具出境承运确报申请
        // MT4405	空集装箱运输工具进境承运确报申请
        // MT4406	空集装箱运输工具出境承运确报申请
        //
        // MFT2008申报类型 RMFT8FunctionTypeCode (AlphaNumber)
        // 2	新增
        // 3	删除申请
        // T	暂存
        // 5	变更申请
        // 9	舱单主要数据申报
        // 0	舱单次要数据申报
        // 11	通关回执
        //
        // MFT2008申报类型 (AlphaNumber) RMFT8ReponseTypeCode
        // M1	M1-手机暂存
        // M2	M2-手机提交
        // M3	QP端退回手机端
        // 01	01-海关接受申报
        // 02	02-待海关人工审核
        // 03	03-海关退单
        // 11	11-海关放行
        // 12	12-海关拒装
        // 13	13-海关禁卸
        // T	T-暂存
        // D	D-申报成功
        // E1	E1-数据中心入库成功
        // E2	E2-数据中心入库失败
        // E3	E3-发往海关成功
        // E4	E4-发往海关失败
        //
        // 公路舱单手机版 驾驶员确认标识 (AlphaNumber) MasterAffirmSign
        // 0	未确认
        // 1	已接受
        // 2	未接受
        //
        // 公路舱单手机版 确认申报方式 (AlphaNumber) DeclareType
        // 0	人工确认申报
        // 1	自动确认申报
        //
        // MFT2008手机人工确认申报单证类型 RMFT8MobileTypeCode (AlphaNumber)
        // MT4403	空载运输工具进境承运确报申请
        // MT4404	空载运输工具出境承运确报申请
        // MT4405	空集装箱运输工具进境承运确报申请
        // MT4406	空集装箱运输工具出境承运确报申请
        //
        // 公路舱单手机版 报文功能 RMFT8MobileFuncCode (AlphaNumber)
        // M1	手机暂存
        // M2	手机提交
        // M3	QP端退回手机端
        // 2	增加
        // 3	删除申请
        // T	暂存
        // 5	变更申请
        // 9	舱单申报
        // 0	二次申报(次要数据)
        // 11	通关回执

        URL url = new URL("http://ceesb.chinaport.gov.cn/SuperPassParaProxy/Proxy_Services/SuperPass_Proxy?wsdl");
        QName qname = new QName("http://www.cneport.com/webservices/superpass", "SuperPass");
        Service service = Service.create(url, qname);
        SuperPass superPass = service.getPort(SuperPass.class);

        /*
        String serviceName = "eport.superpass.pub.para.CustomsFlagStatus";
        byte[] requestContext = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?><RequestContext><Group name=\"SystemInfo\"><Key name=\"ClientId\">5300001128334</Key><Key name=\"CertNo\">c1f4bb</Key><Key name=\"SaicSysNo\">618882068</Key><Key name=\"DEP_IN_CODE\">5300</Key><Key name=\"REG_CO_CGAC\">4403941436</Key><Key name=\"ENT_SEQ_NO\">000000000000063462</Key><Key name=\"IcCode\">8800000246746</Key><Key name=\"OperatorName\">周金泉</Key><Key name=\"DEP_CODE_CHG\">5300</Key><Key name=\"NAME_FULL\">南精机电（深圳）有限公司</Key></Group><Group name=\"DataPresentation\"><Key name=\"SignatureAlgorithm\" /><Key name=\"EncryptAlgorithm\" /><Key name=\"CompressAlgorithm\" /></Group></RequestContext>".getBytes();
        byte[] requestData = "<?xml version=\"1.0\"?>\n<CustomsFlagStatusRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n<CustomsCode>5300</CustomsCode>\n</CustomsFlagStatusRequest>".getBytes();
        */

        // 参数查询接口
        String serviceName = "eport.superpass.pub.para.LoadIntoMemory";
        byte[] requestContext = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?><RequestContext><Group name=\"SystemInfo\"><Key name=\"ClientId\">5300001128334</Key><Key name=\"CertNo\">b6c29b</Key><Key name=\"SaicSysNo\">618882068</Key><Key name=\"DEP_IN_CODE\">5300</Key><Key name=\"REG_CO_CGAC\">4403941436</Key><Key name=\"ENT_SEQ_NO\">000000000000063462</Key><Key name=\"SessionId\">2015-7-14</Key><Key name=\"IcCode\">8910000270086</Key><Key name=\"OperatorName\">吴友玲</Key><Key name=\"DEP_CODE_CHG\">5300</Key><Key name=\"NAME_FULL\">南精机电（深圳）有限公司</Key></Group><Group name=\"DataPresentation\"><Key name=\"SignatureAlgorithm\" /><Key name=\"EncryptAlgorithm\" /><Key name=\"CompressAlgorithm\" /></Group></RequestContext>".getBytes();
        // 拖挂车类型代码表 MFT8TrayType
        // SELECT TRAY_CODE,TRAY_NAME FROM MFT8_TRAY_TYPE ORDER BY TRAY_CODE
        // 运输方式代码表 RMFT8TransportType
        // SELECT CODE, NAME FROM mft8_transport_type ORDER BY CODE
        // 海关代码 RMFT8CustomsCode
        // SELECT customs_CODE, customs_NAME FROM customs ORDER BY customs_CODE
        // 国内港口代码(CN003) RMFT8PortCode
        // SELECT CODE, CHNAME FROM mft8_location_name ORDER BY CODE
        // 币值 RMFT8Curr
        // SELECT CODE, NAME FROM MFT8_ROAD_CURR ORDER BY CODE
        // 收货地点代码 RMFT8ReceiptPlace
        // SELECT CODE, NAME FROM mft8_entity ORDER BY CODE
        // 国别代码 RMFT8CountryCode
        // SELECT CODE, NAME FROM mft8_country_code ORDER BY CODE
        // 货物海关状态代码 RMFT8CustomStatus
        // SELECT CODE, NAME FROM MFT8_ROAD_CUSTOMS_STATUS ORDER BY CODE
        // 运费支付方法代码 RMFT8TransPayCode
        // SELECT CODE, NAME FROM MFT8_ROAD_PAYMENT_METHOD ORDER BY CODE
        // 包装种类代码(CN005) RMFT8WrapTypeCode
        // SELECT CODE, NAME FROM mft8_packaging ORDER BY CODE
        // 运输条款代码 RMFT8TransLicCode
        // SELECT CODE, NAME FROM MFT8_CONTR_CAR_COND ORDER BY CODE
        // 码头作业指令代码 RMFT8PortWorkCode
        // SELECT CODE, NAME FROM MFT8_HAND_INSTR ORDER BY CODE
        // 通讯方式代码 RMFT8CommunicationCode
        // SELECT CODE, NAME FROM mft8_communi_type ORDER BY CODE
        // 邮政编码 RMFT8PostCode
        // SELECT CODE, NAME FROM mft8_post_code ORDER BY CODE
        // 集装箱尺寸和类型 RMFT8EquipSizeCode
        // SELECT CODE, SHAPE,LENGTH,HEIGHT,WIDTH FROM mft8_equip_size_type ORDER BY CODE
        // 集装箱来源类型代码 RMFT8EquipSupCode
        // SELECT CODE, NAME FROM MFT8_EQUIP_SUP ORDER BY CODE
        // 集装箱空箱重箱标志代码 RMFT8EquipFullCode
        // SELECT CODE, NAME FROM mft8_equip_full ORDER BY CODE
        // 集装箱封志人代码 RMFT8EquipSealCode
        // SELECT CODE, NAME FROM mft8_seal_agency ORDER BY CODE
        // 危险品代码 RMFT8DanGoodsCode
        // SELECT CODE, CHNAME FROM mft8_dan_goods ORDER BY CODE
        // 海关手续代码 RMFT8CusProCode
        // SELECT CODE, NAME FROM mft8_cus_procedure ORDER BY CODE
        // 承运人货物分批到/发货标识 RMFT8TransportSplitCode
        // SELECT CODE, NAME FROM mft8_indication ORDER BY CODE
        // 集装箱残损范围代码 RMFT8DamageAreaCode
        // SELECT CODE, NAME FROM MFT8_DAMAGE_AREA ORDER BY CODE
        // 集装箱残损类型代码 RMFT8DamageTypeCode
        // SELECT CODE, NAME FROM MFT8_DAMAGE_TYPE_DES ORDER BY CODE
        // IATA航站代码(UN005) RMFT8IATACode
        // SELECT CODE, NAME FROM mft8_iata_code ORDER BY CODE
        // 联合国贸易口岸代码(UN009) RMFT8LocodePort
        // SELECT CODE, NAME FROM mft8_un_locode_port ORDER BY CODE
        // RMFT8ComplexCode
        // SELECT CODE_TS, G_NAME FROM complex ORDER BY CODE_TS
        // byte[] requestData = "<?xml version=\"1.0\"?>\n<LoadIntoMemoryRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n<QuerySql>select table_name from user_tables</QuerySql>\n</LoadIntoMemoryRequest>".getBytes();

        // SELECT COUNTRY_CODE FROM ( SELECT A.*, ROWNUM RN FROM (SELECT * FROM AGREEMENT_RATE) A WHERE ROWNUM <= 40 ) WHERE RN >= 21
        // byte[] requestData = "<?xml version=\"1.0\"?>\n<LoadIntoMemoryRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n<QuerySql>SELECT * FROM AGREEMENT_RATE</QuerySql>\n</LoadIntoMemoryRequest>".getBytes();
        // byte[] requestData = "<?xml version=\"1.0\"?>\n<LoadIntoMemoryRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n<QuerySql>SELECT column_name FROM user_tab_columns WHERE TABLE_NAME = 'AGREEMENT_RATE'</QuerySql>\n</LoadIntoMemoryRequest>".getBytes();
        // byte[] requestData = "<?xml version=\"1.0\"?>\n<LoadIntoMemoryRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n<QuerySql>SELECT CODE_TS,AGREEMENT_ID,COUNTRY_CODE,BEGIN_DATE,G_NAME,END_DATE,DUTY_TYPE,DUTY_RATE,COM_V_RATE,COM_Q_RATE,COM_UNIT_FLAG,COM_CTL_PRICE,COM_CTL_CURR,NOTE_S,REG_TYPE,REG_RATE,REG_Q_RATE,REG_LOW_Q_RATE,REG_CTL_PRICE,REG_CTL_CURR,TAX_TYPE,TAX_RATE,OUT_TYPE,OUT_RATE FROM ( SELECT A.*, ROWNUM RN FROM (SELECT * FROM AGREEMENT_RATE) A WHERE ROWNUM &lt;= 40 ) WHERE RN &gt;= 1</QuerySql>\n</LoadIntoMemoryRequest>".getBytes();
        // byte[] requestData = "<?xml version=\"1.0\"?>\n<LoadIntoMemoryRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><QuerySql><![CDATA[SELECT CODE_TS,AGREEMENT_ID,COUNTRY_CODE,BEGIN_DATE,G_NAME,END_DATE,DUTY_TYPE,DUTY_RATE,COM_V_RATE,COM_Q_RATE,COM_UNIT_FLAG,COM_CTL_PRICE,COM_CTL_CURR,NOTE_S,REG_TYPE,REG_RATE,REG_Q_RATE,REG_LOW_Q_RATE,REG_CTL_PRICE,REG_CTL_CURR,TAX_TYPE,TAX_RATE,OUT_TYPE,OUT_RATE FROM (SELECT A.*, ROWNUM RN FROM (SELECT * FROM AGREEMENT_RATE WHERE END_DATE > TO_DATE('08/18/2016','MM/dd/yyyy')) A WHERE ROWNUM <= 400000) WHERE RN >= 300001]]></QuerySql></LoadIntoMemoryRequest>".getBytes();
        // byte[] requestData = "<?xml version=\"1.0\"?>\n<LoadIntoMemoryRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><QuerySql><![CDATA[SELECT COUNT(1) AS COUNT FROM AGREEMENT_RATE WHERE END_DATE > TO_DATE('08/18/2016','MM/dd/yyyy')]]></QuerySql></LoadIntoMemoryRequest>".getBytes();
        // byte[] requestData = "<?xml version=\"1.0\"?>\n<LoadIntoMemoryRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><QuerySql><![CDATA[SELECT CODE, NAME FROM mft8_un_locode_port ORDER BY CODE]]></QuerySql></LoadIntoMemoryRequest>".getBytes();
        byte[] requestData = "<?xml version=\"1.0\"?>\n<LoadIntoMemoryRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><QuerySql><![CDATA[SELECT MFTEC_CODE, MFTEC_NAME FROM MFTEC_CODE ORDER BY MFTEC_CODE]]></QuerySql></LoadIntoMemoryRequest>".getBytes();

        Holder<byte[]> responseData = new Holder<byte[]>();
        System.out.println(new String(superPass.service(serviceName, requestContext, requestData, responseData)));

        // deflate 解压
        DeflateCompressorInputStream gis = new DeflateCompressorInputStream(
            new ByteArrayInputStream(responseData.value)
        );
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int count;
        byte data[] = new byte[1024];
        while ((count = gis.read(data, 0, 1024)) != -1) {
            baos.write(data, 0, count);
        }
        gis.close();

        data = baos.toByteArray();
        baos.flush();
        baos.close();

        // System.out.println(new String(data));
        // System.out.println(new String(responseData.value, "UTF-8"));
        FileOutputStream fos = new FileOutputStream(
            new StringBuffer()
                .append("D:/")
                .append(UUID.randomUUID())
                .append(".xml")
                .toString()
        );
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        osw.write(new String(data));
        osw.flush();
    }
}
