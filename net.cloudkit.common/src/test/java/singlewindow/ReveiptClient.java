//package singlewindow;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ReveiptClient {
//    public static void main(String[] args) throws Exception {
//        //getReceipt();
//        deleteReceipt();
//    }
//
//    public static void getReceipt() throws Exception {
//        String url = "https://58.63.50.170:7892/CargoDeclaration/restful/operatorCompany/getReceipt/";
//        //String url ="http://localhost:8080/CargoDeclaration/restful/operatorCompany/getReceipt/";
//        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//        nvps.add(new BasicNameValuePair("companyId", "GZEPORT"));
//        nvps.add(new BasicNameValuePair("invNos", "0160323519902326,0150824516901765,1150827519001803"));
//        doPost(url, nvps);
//    }
//
//    public static void deleteReceipt() throws Exception {
//        String url = "https://58.63.50.170:7892/CargoDeclaration/restful/operatorCompany/deleteReceipt/";
//        //String url = "http://localhost:8080/CargoDeclaration/restful/operatorCompany/deleteReceipt/";
//        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//        nvps.add(new BasicNameValuePair("companyId", "GZEPORT"));
//        String reportStr = FileUtils.readTxtFile("关检删除回执.xml");
//        nvps.add(new BasicNameValuePair("report", reportStr));
//        doPost(url, nvps);
//    }
//
//
//    public static void doPost(String url, List<NameValuePair> nvps) throws Exception {
//        HttpPost httpPost = new HttpPost(url);//HTTP Get请求(POST雷同)
//        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
//
//        // HttpClient client = HttpClients.createDefault();
//        HttpClient client = SSLUtils.getSSLclient();
//        HttpResponse response = client.execute(httpPost);
//        System.out.println(response.getStatusLine().getStatusCode() + response.getStatusLine().getReasonPhrase());
//        String result = EntityUtils.toString(response.getEntity());
//        System.out.println(result);
//    }
//}
