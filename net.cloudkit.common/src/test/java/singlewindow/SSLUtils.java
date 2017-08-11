//import org.apache.http.client.HttpClient;
//import org.apache.http.conn.ssl.DefaultHostnameVerifier;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.conn.ssl.SSLContexts;
//import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
//import org.apache.http.impl.client.HttpClients;
//
//import javax.net.ssl.SSLContext;
//import java.io.FileInputStream;
//import java.security.KeyStore;
//import java.security.cert.Certificate;
//import java.security.cert.CertificateFactory;
//
//public class SSLUtils {
//
//    public static HttpClient getSSLclient() throws Exception {
//        KeyStore clientKeyStore = KeyStore.getInstance("PKCS12");
//        //企业存放客户端证书路径
//        // clientKeyStore.load(new FileInputStream("D:\\test\\client.p12"), "123456".toCharArray());
//        clientKeyStore.load(new FileInputStream("D:\\production\\client.p12"), "123456".toCharArray());
//        KeyStore trustKeyStore = KeyStore.getInstance("jks");
//        trustKeyStore.load(null);
//        CertificateFactory factory = CertificateFactory.getInstance("x509");
//        //企业存放ca路径
//        // Certificate cacert = factory.generateCertificate(new FileInputStream("D:\\test\\ca-cert.cer"));
//        Certificate cacert = factory.generateCertificate(new FileInputStream("D:\\production\\ca.crt"));
//        trustKeyStore.setCertificateEntry("ca", cacert);
//
//
//        SSLContext sslContext = SSLContexts
//            .custom()
//            .loadKeyMaterial(clientKeyStore, "123456".toCharArray())
//            .loadTrustMaterial(trustKeyStore, new TrustSelfSignedStrategy())
//            .build();
//        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new DefaultHostnameVerifier());
//
//        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
//    }
//}
