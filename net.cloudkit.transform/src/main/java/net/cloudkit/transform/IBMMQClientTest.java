package net.cloudkit.transform;

import com.ibm.mq.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

public class IBMMQClientTest {

    private static MQQueueManager qMgr;
    private static int CCSID = 1381;
    private static String queueName = "LOCAL_QUEUE";

    public static void connect() throws MQException {

        MQEnvironment.hostname = "120.76.96.11";
        MQEnvironment.channel = "SERVERCONN";
        MQEnvironment.port = 1414;
        MQEnvironment.CCSID = CCSID;
        qMgr = new MQQueueManager("myTest");
    }

    public static void send(String message) {

        int openOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT | MQC.MQOO_INQUIRE;
        System.out.println(openOptions);
        MQQueue queue = null;

        try {
            //建立Q1通道的连接
            queue = qMgr.accessQueue(IBMMQClientTest.queueName, openOptions, null, null, null);

            // 要写入队列的消息
            MQMessage msg = new MQMessage();
            msg.format = MQC.MQFMT_STRING;
            msg.characterSet = CCSID;
            msg.encoding = CCSID;

            //将消息写入消息对象中
            // msg.writeObject(message);
            msg.writeString(message);
            // 设置消息用不过期
            msg.expiry = -1;

            MQPutMessageOptions pmo = new MQPutMessageOptions();

            // 将消息放入队列
            queue.put(msg, pmo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (queue != null) {
                try {
                    queue.close();
                } catch (MQException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public static void receive() {
        int openOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT | MQC.MQOO_INQUIRE;
        MQQueue queue = null;
        try {
            queue = qMgr.accessQueue(IBMMQClientTest.queueName, openOptions, null, null, null);
            System.out.println("该队列当前的深度为:" + queue.getCurrentDepth());
            System.out.println("---------------------------");

            int depth = queue.getCurrentDepth();

            //将队列的里的消息读出来
            while (depth-- > 0) {

                // 要读的队列的消息
                MQMessage msg = new MQMessage();
                MQGetMessageOptions gmo = new MQGetMessageOptions();
                queue.get(msg, gmo);
                System.out.println("消息的大小为：" + msg.getDataLength());
                System.out.println("消息的内容：\n" + msg.readStringOfByteLength(msg.getDataLength()));
                System.out.println("---------------------------");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (queue != null) {
                try {
                    queue.close();
                } catch (MQException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws MQException {
        connect();
        // send("test");
        // receive();
    }
}


//
////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by Fernflower decompiler)
////
//
//package com.ecom.core.util;
//
//    import java.io.IOException;
//    import org.apache.commons.logging.Log;
//    import org.apache.commons.logging.LogFactory;
//    import org.apache.commons.net.ftp.FTPClient;
//    import org.apache.commons.net.ftp.FTPReply;
//    import org.apache.commons.net.ftp.FTPSClient;
//    import org.apache.commons.net.util.TrustManagerUtils;
//
//public class FtpComponent {
//    static Log log = LogFactory.getLog("FtpComponent");
//    public Integer FTP_CONNECT_TIMEOUT = Integer.valueOf(300000);
//    public Integer FTP_DATA_TIMEOUT = Integer.valueOf(300000);
//    public Integer FTP_DEFAULT_TIMEOUT = Integer.valueOf(300000);
//    public Integer FTP_SO_TIMEOUT = Integer.valueOf(300000);
//    public Integer FTP_BUFFERSIZE = Integer.valueOf(2048);
//    public Boolean isFtpLogout = Boolean.valueOf(false);
//    public Boolean isFtpsDataConnectionSecurity = Boolean.valueOf(false);
//    public static String FTPS_PROTOCOL = "SSL";
//    private String hostname;
//    private int port;
//    private String username;
//    private String password;
//
//    public FtpComponent() {
//    }
//
//    public FtpComponent(String hostname, int port, String username, String password) {
//        this.hostname = hostname;
//        this.port = port;
//        this.username = username;
//        this.password = password;
//    }
//
//    public FTPClient connectFtp() throws Exception {
//        FTPClient ftp = new FTPClient();
//        this.connect(ftp);
//        return ftp;
//    }
//
//    public FTPSClient connectFTPS(boolean isFtpsImplicit) throws Exception {
//        FTPSClient ftps = new FTPSClient(FTPS_PROTOCOL, isFtpsImplicit);
//        ftps.setTrustManager(TrustManagerUtils.getAcceptAllTrustManager());
//        this.connect(ftps);
//        if(this.isFtpsDataConnectionSecurity.booleanValue()) {
//            ftps.execPROT("P");
//        }
//
//        return ftps;
//    }
//
//    private void connect(FTPClient ftpClient) throws Exception {
//        try {
//            if(this.FTP_CONNECT_TIMEOUT.intValue() > 0) {
//                ftpClient.setConnectTimeout(this.FTP_CONNECT_TIMEOUT.intValue());
//            }
//
//            if(this.FTP_DEFAULT_TIMEOUT.intValue() > 0) {
//                ftpClient.setDefaultTimeout(this.FTP_DEFAULT_TIMEOUT.intValue());
//            }
//
//            if(this.FTP_DATA_TIMEOUT.intValue() > 0) {
//                ftpClient.setDataTimeout(this.FTP_DATA_TIMEOUT.intValue());
//            }
//
//            if(this.FTP_BUFFERSIZE.intValue() > 0) {
//                ftpClient.setBufferSize(this.FTP_BUFFERSIZE.intValue());
//            }
//
//            ftpClient.connect(this.hostname, this.port);
//            if(this.FTP_SO_TIMEOUT.intValue() > 0) {
//                ftpClient.setSoTimeout(this.FTP_SO_TIMEOUT.intValue());
//            }
//
//            int reply = ftpClient.getReplyCode();
//            if(!FTPReply.isPositiveCompletion(reply)) {
//                ftpClient.disconnect();
//                throw new Exception("FTP server refused connection.");
//            } else if(!ftpClient.login(this.username, this.password)) {
//                if(this.isFtpLogout.booleanValue()) {
//                    ftpClient.logout();
//                }
//
//                throw new Exception("FTPs login error.");
//            }
//        } catch (Exception var5) {
//            if(ftpClient.isConnected()) {
//                try {
//                    ftpClient.disconnect();
//                } catch (IOException var4) {
//                    var5.printStackTrace();
//                }
//            }
//
//            throw var5;
//        }
//    }
//
//    public String getHostname() {
//        return this.hostname;
//    }
//
//    public void setHostname(String hostname) {
//        this.hostname = hostname;
//    }
//
//    public int getPort() {
//        return this.port;
//    }
//
//    public void setPort(int port) {
//        this.port = port;
//    }
//
//    public String getUsername() {
//        return this.username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return this.password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}
