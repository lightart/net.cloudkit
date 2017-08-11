//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class HttpClientSSL {
//
//
//    public static void main(String[] args) throws Exception {
//        // withSSL();
//        withOutSSL();
//
//    }
//
//    public static void withSSL() throws Exception {
//        System.out.println("==================with SSL");
//        doPost(SSLUtils.getSSLclient());
//    }
//
//    public static void withOutSSL() throws Exception {
//        System.out.println("==================withOut SSL");
//        HttpClient client = HttpClients.createDefault();
//        doPost(client);
//    }
//
//    public static void doPost(HttpClient client) throws Exception {
//        // http://192.168.1.103:6666/client/declare/
//
//        // 正式接口地址：https://service.singlewindow.gz.cn/CargodeclServices/restful/operatorCompany/import/
//        // 测试接口地址：https://58.63.50.170:7892/CargoDeclaration/restful/operatorCompany/import/
//        // String url = "https://58.63.50.170:7892/CargoDeclaration/restful/operatorCompany/import/";
//        // String url = "https://service.singlewindow.gz.cn/CargodeclServices/restful/operatorCompany/import/";
//
//        String url = "http://192.168.1.103:6666/client/declare/import";
//        String ioFlag = "1";
//        String filePath = "";
//        // String filePath = "D:\\进口报关报文样例.xml";
//        if ("1".equals(ioFlag)) {
//            filePath = "D:\\出口报关报文样例.xml";
//        }
//
//        String reportStr = FileUtils.readTxtFile(filePath);
//        // HTTP Get请求(POST雷同)
//        HttpPost httpPost = new HttpPost(url);
//        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//        nvps.add(new BasicNameValuePair("userName", "GZJHGJ"));
//        nvps.add(new BasicNameValuePair("passWord", "GZJHGJ123"));
//        // nvps.add(new BasicNameValuePair("companyId", "GZEPORT"));
//        nvps.add(new BasicNameValuePair("companyId", "GZJHGJ"));
//        nvps.add(new BasicNameValuePair("ioFlag", ioFlag));
//        nvps.add(new BasicNameValuePair("report", reportStr));
//        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
//        HttpResponse response = client.execute(httpPost);
//        System.out.println(response.getStatusLine().getStatusCode() + response.getStatusLine().getReasonPhrase());
//        String result = EntityUtils.toString(response.getEntity());
//        System.out.println(result);
//
//
////        // 上传随附单据
////        String url = "http://192.168.1.103:6666/client/declare/uploadEdoc";
////        // HTTP Get请求(POST雷同)
////        HttpPost httpPost = new HttpPost(url);
////        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
////        nvps.add(new BasicNameValuePair("userName", "GZJHGJ"));
////        nvps.add(new BasicNameValuePair("passWord", "GZJHGJ123"));
////        nvps.add(new BasicNameValuePair("companyId", "GZJHGJ"));
////        nvps.add(new BasicNameValuePair("invNo", "1170420533947642"));
////        nvps.add(new BasicNameValuePair("invPassword", "078695"));
////        // 放list然后转json
////        nvps.add(new BasicNameValuePair("fileTypes", ""));
////        nvps.add(new BasicNameValuePair("copFileNames", ""));
////        nvps.add(new BasicNameValuePair("copFileDatas", ""));
////        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
////        HttpResponse response = client.execute(httpPost);
////        System.out.println(response.getStatusLine().getStatusCode() + response.getStatusLine().getReasonPhrase());
////        String result = EntityUtils.toString(response.getEntity());
////        System.out.println(result);
//
//
////        // 申报报关单
////        String url = "http://192.168.1.103:6666/client/declare/inspecthg";
////        // HTTP Get请求(POST雷同)
////        HttpPost httpPost = new HttpPost(url);
////        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
////        nvps.add(new BasicNameValuePair("userName", "GZJHGJ"));
////        nvps.add(new BasicNameValuePair("passWord", "GZJHGJ123"));
////        nvps.add(new BasicNameValuePair("companyId", "GZJHGJ"));
////        nvps.add(new BasicNameValuePair("invNo", "1170426516661624"));
////        nvps.add(new BasicNameValuePair("invPassword", "873467"));
////        nvps.add(new BasicNameValuePair("operateType", "1"));
////        nvps.add(new BasicNameValuePair("copCode", "MA59C2N60"));
////        nvps.add(new BasicNameValuePair("copName", "广州巨航国际物流有限公司"));
////        nvps.add(new BasicNameValuePair("typistNo", "8930001001303"));
////        nvps.add(new BasicNameValuePair("decNo", "51105109"));
////        nvps.add(new BasicNameValuePair("cusCode", "5169"));
////        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
////        HttpResponse response = client.execute(httpPost);
////        System.out.println(response.getStatusLine().getStatusCode() + response.getStatusLine().getReasonPhrase());
////        String result = EntityUtils.toString(response.getEntity());
////        System.out.println(result);
//
//
////        // 获以申报回执
////        String url = "http://192.168.1.103:6666/client/declare/queryMessage";
////        // HTTP Get请求(POST雷同)
////        HttpPost httpPost = new HttpPost(url);
////        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
////        nvps.add(new BasicNameValuePair("userName", "GZJHGJ"));
////        nvps.add(new BasicNameValuePair("passWord", "GZJHGJ123"));
////        nvps.add(new BasicNameValuePair("companyId", "GZJHGJ"));
////        nvps.add(new BasicNameValuePair("invNo", "1170420533947642"));
////        nvps.add(new BasicNameValuePair("invPassword", "078695"));
////        nvps.add(new BasicNameValuePair("messageType", "1"));
////        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
////        HttpResponse response = client.execute(httpPost);
////        System.out.println(response.getStatusLine().getStatusCode() + response.getStatusLine().getReasonPhrase());
////        String result = EntityUtils.toString(response.getEntity());
////        System.out.println(result);
//
//    }
//
//}
