//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPFile;
//import org.apache.commons.net.ftp.FTPReply;
//
//import java.io.IOException;
//
//public class FTPTest {
//
//
//    // public static FTPClient ftpClient;
//
//    public static void main(String[] args) {
//        FTPTest ftpTest = new FTPTest();
//        ftpTest.execute();
//    }
//
//    public void execute() {
//        // ftpClient = getFtpClient();
//
//        for (int i = 0; i < 10000; i++) {
//            FTPClient ftpClient = getFtpClient();
//            try {
//
//                FTPFile[] ftpFiles = ftpClient.listFiles("./");
//                System.out.println("获取回执文件成功，数量：" + ftpFiles.length);
//                if (ftpFiles != null && ftpFiles.length > 0) {
//                    // TODO
//                }
//                // Thread.sleep(1000 * 60);
//                ftpClient.logout();
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                //关闭ftp服务
//                closeFtp(ftpClient);
//            }
//        }
//
//
////        Runnable runnable = () -> {
////
////        };
////
////        for(int i = 0; i < 10; i++) {
////            new Thread(runnable).start();
////        }
//    }
//
//    //获取ftp客户端，并切换到回执目录
//    public FTPClient getFtpClient() {
//        FTPClient ftpClient = new FTPClient();
//        try {
//            ftpClient.connect("61.152.176.30", 21);
//            int reply = ftpClient.getReplyCode();
//            if (!FTPReply.isPositiveCompletion(reply)) {
//                ftpClient.disconnect();
//                System.err.println("FTP server refused connection.");
//            }
//            ftpClient.login("EMVSDFHD", "EMVS209");
//            // 切换目录
//            ftpClient.changeWorkingDirectory("tmp");
//            // 设置传输超时时间为60秒
//            ftpClient.setDataTimeout(60000);
//            // 连接超时为60秒
//            ftpClient.setConnectTimeout(60000);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return ftpClient;
//    }
//
//    public void closeFtp(FTPClient ftpClient) {
//        // 关闭ftp服务
//        if (ftpClient.isConnected()) {
//            try {
//                ftpClient.disconnect();
//            } catch (IOException ioe) {
//                ioe.printStackTrace();
//            }
//        }
//    }
//}
