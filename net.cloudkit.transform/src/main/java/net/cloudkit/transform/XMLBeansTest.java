//package net.cloudkit.transform;
//
//import noNamespace.GoodsListType;
//import noNamespace.HeadType;
//import noNamespace.ImportCdlBillRequestDocument;
//import noNamespace.ImportCdlBillRequestType;
//import org.apache.xmlbeans.XmlException;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//
//public class XMLBeansTest {
//    public static void main(String[] args) throws IOException {
//
//        // 解析目录
//        Path handlePath = Paths.get("F:\\CDL\\");
//        // 历史目录
//        Path historyPath = Paths.get("F:\\CDL_HIS\\");
//        // 错误目录
//        Path failedPath = Paths.get("F:\\CDL_FAILED\\");
//
//        // 目录不存在则创建目录
//        if (Files.notExists(handlePath)) {
//            Files.createDirectories(handlePath);
//        }
//        if (Files.notExists(historyPath)) {
//            Files.createDirectories(historyPath);
//        }
//        if (Files.notExists(failedPath)) {
//            Files.createDirectories(failedPath);
//        }
//
//        //遍历回执文件夹
//        File[] files = handlePath.toFile().listFiles();
//        StringBuffer headStringBuffer = new StringBuffer();
//        StringBuffer goodListsStringBuffer = new StringBuffer();
//        for (File file : files) {
//            try {
//                // Bind the instance to the generated XMLBeans types.
//                ImportCdlBillRequestDocument importCdlBillRequestDocument = ImportCdlBillRequestDocument.Factory.parse(file);
//                // Get and print pieces of the XML instance.
//                ImportCdlBillRequestType importCdlBillRequest = importCdlBillRequestDocument.getImportCdlBillRequest();
//                HeadType head = importCdlBillRequest.getHead();
//                GoodsListType[] goodsLists = importCdlBillRequest.getGoodsListArray();
//                // System.out.println(importCdlBillRequest.getHead().getAgentCode());
//
//                /*
//                headStringBuffer.append(importCdlBillRequest.getPreImpNo());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getIEFlag());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getIEPort());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getIEDate());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getRecordsNo());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getTradeCode());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getTradeName());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getOwnerCode());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getOwnerName());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getTrafMode());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getShipName());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getVoyageNo());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getBillNo());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getTradeMode());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getLicenseNo());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getTradeCountry());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getDestinationPort());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getDistrictCode());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getPackNum());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getWrapType());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getGrossWt());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getNetWt());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getNotes());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getTradeBondedFlag());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getContaNum());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getDocuCodeCom());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getAgentCode());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getAgentName());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getDeclCustom());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getDeclDate());
//                headStringBuffer.append("\t");
//                headStringBuffer.append(head.getGType());
//                headStringBuffer.append("\r\n");
//                */
//
//                for(int i = 0; i < goodsLists.length; i++) {
//                    GoodsListType goodsList = goodsLists[i];
//                    goodListsStringBuffer.append(importCdlBillRequest.getPreImpNo());
//                    goodListsStringBuffer.append("\t");
//                    goodListsStringBuffer.append(i + 1);
//                    goodListsStringBuffer.append("\t");
//                    goodListsStringBuffer.append(goodsList.getItemNo());
//                    goodListsStringBuffer.append("\t");
//                    goodListsStringBuffer.append(goodsList.getCodeTs());
//                    goodListsStringBuffer.append("\t");
//                    goodListsStringBuffer.append(goodsList.getGName());
//                    goodListsStringBuffer.append("\t");
//                    goodListsStringBuffer.append(goodsList.getGModel());
//                    goodListsStringBuffer.append("\t");
//                    goodListsStringBuffer.append(goodsList.getGQty());
//                    goodListsStringBuffer.append("\t");
//                    goodListsStringBuffer.append(goodsList.getGUnit());
//                    goodListsStringBuffer.append("\t");
//                    goodListsStringBuffer.append(goodsList.getDeclPrice());
//                    goodListsStringBuffer.append("\t");
//                    goodListsStringBuffer.append(goodsList.getDeclTotal());
//                    goodListsStringBuffer.append("\t");
//                    goodListsStringBuffer.append(goodsList.getTradeCurr());
//                    goodListsStringBuffer.append("\t");
//                    goodListsStringBuffer.append(goodsList.getOriginalalCountry());
//                    goodListsStringBuffer.append("\t");
//                    goodListsStringBuffer.append(goodsList.getDutyMode());
//                    goodListsStringBuffer.append("\t");
//                    goodListsStringBuffer.append(goodsList.getUnit1());
//                    goodListsStringBuffer.append("\t");
//                    goodListsStringBuffer.append(goodsList.getUnit2());
//                    goodListsStringBuffer.append("\r\n");
//                }
//
//                // 7282
//                Path moveHisPath = Paths.get(historyPath.toString() + File.separator + file.getName());
//                Files.move(Paths.get(file.getAbsolutePath()), moveHisPath, StandardCopyOption.REPLACE_EXISTING);
//            } catch (IOException | XmlException e) {
//                Path moveFailedPath = Paths.get(failedPath.toString() + File.separator + file.getName());
//                try {
//                    Files.move(Paths.get(file.getAbsolutePath()), moveFailedPath, StandardCopyOption.REPLACE_EXISTING);
//                } catch (IOException e1) {
//                    System.out.println(file.getName());
//                }
//            }
//        }
//
//        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("D:\\result_goods.txt")));
//        writer.write(goodListsStringBuffer.toString());
//        writer.close();
//    }
//}
