package net.cloudkit.transform;

import com.ibm.mq.*;
import com.ibm.mq.constants.CMQC;
import com.ibm.mq.constants.MQConstants;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class IBMMQComponent {

    public static void main(String[] args) {
        // 回执 QLCqhz01 QTCqhz01
        // IBMMQComponent mq = new IBMMQComponent("120.76.96.117", Integer.valueOf(1401), "SYSTEM.DEF.SVRCONN", "QMCqhz01", "TEST_LOCAL", "D:\\trans_mq\\downloads");
        IBMMQComponent mq = new IBMMQComponent();
        mq.setHostname("120.76.96.117");
        mq.setPort(1401);
        mq.setChannel("SYSTEM.DEF.SVRCONN");
        mq.setQueueManagerName("QMCqhz01");
        mq.setQueueName("TEST_LOCAL");
        mq.setCcsid(Integer.valueOf(819));
        mq.setReceivePath("D:\\trans_mq\\downloads");
        // mq.setUserid("Administrator");
        // mq.setPassword("password");

//        try {
//            mq.send(new File("D:\\trans_mq\\uploads\\message.txt"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        mq.ibmMQReceiveCallback = new IBMMQReceiveCallback() {
//
//            public void execute(byte[] data) {
//                System.out.println(new String(data));
//            }
//        };

        try {
            mq.receive();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        try {
//            Thread.sleep(100000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public static int MQ_DEFAULT_PORT = 1414;
    public static int MQ_DEFAULT_CCSID = 1381;

    // MQ服务器的IP地址
    private String hostname;
    // MQ端口
    private Integer port = MQ_DEFAULT_PORT;

    // 服务器连接的通道
    private String channel;
    // MQ的队列管理器名称
    private String queueManagerName;
    // MQ远程队列的名称
    private String queueName;
    // 服务器MQ服务使用的编码 1381
    private Integer ccsid;

    private String userid;
    private String password;

    private String receivePath;

    private IBMMQReceiveCallback ibmMQReceiveCallback;

    public IBMMQComponent() {

    }

    public IBMMQComponent(String hostname, Integer port, String channel, String queueManagerName, String queueName) {
        this.hostname = hostname;
        this.port = port;
        this.channel = channel;
        this.queueManagerName = queueManagerName;
        this.queueName = queueName;
    }

    public IBMMQComponent(String hostname, Integer port, String channel, String queueManagerName, String queueName, String receivePath) {
        this.hostname = hostname;
        this.port = port;
        this.channel = channel;
        this.queueManagerName = queueManagerName;
        this.queueName = queueName;
        this.receivePath = receivePath;
    }

    /**
     * 往MQ发送消息.
     *
     * @param id
     * @param data
     * @throws Exception
     */
    public void send(String id, byte[] data) throws Exception {
        MQQueue queue = null;
        MQQueueManager queueManager = null;
        try {
            queueManager = createConnection();

            // 设置将要连接的队列属性
            // 目标为远程队列，所有这里不可以用MQOO_INPUT_AS_Q_DEF属性 int openOptions = CMQC.MQOO_INPUT_AS_Q_DEF | CMQC.MQOO_OUTPUT;
            // 适合远程队列与本地队列
            int openOptions = CMQC.MQOO_OUTPUT | CMQC.MQOO_FAIL_IF_QUIESCING;

            queue = queueManager.accessQueue(this.queueName, openOptions, null, null, null);
            // delete the queue when it is closed
            //queue.setCloseOptions(MQConstants.MQCO_DELETE)

            // 定义一个消息
            MQMessage message = new MQMessage();
            message.correlationId = id.getBytes();
            // 将数据放入消息缓冲区
            message.write(data);

            // 设置写入消息的属性（默认属性）
            MQPutMessageOptions pmo = new MQPutMessageOptions();
            queue.put(message, pmo);

            queueManager.commit();
            return;
        } finally {
            try {
                if (queue != null) {
                    queue.close();
                    queue = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (queueManager != null) {
                try {
                    queueManager.close();
                    queueManager.disconnect();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /**
     * 往MQ队列发送消息.
     *
     * @param file
     * @throws Exception
     */
    public void send(File file) throws Exception {
        send(file.getName(), FileUtils.readFileToByteArray(file));
    }

    /**
     * 从MQ队列获取消息.
     *
     * @throws Exception
     */
    public void receive() throws Exception {
        MQQueue queue = null;
        MQQueueManager queueManager = null;
        try {
            queueManager = createConnection();
            // int openOptions = CMQC.MQOO_INPUT_AS_Q_DEF | CMQC.MQOO_OUTPUT | CMQC.MQOO_INQUIRE | CMQC.MQGMO_SYNCPOINT | CMQC.MQGMO_WAIT | CMQC.MQGMO_FAIL_IF_QUIESCING;
            int openOptions = CMQC.MQGMO_SYNCPOINT | CMQC.MQGMO_FAIL_IF_QUIESCING;

            queue = queueManager.accessQueue(this.queueName, openOptions, null, null, null);
            // delete the queue when it is closed
            //queue.setCloseOptions(MQConstants.MQCO_DELETE)

            MQGetMessageOptions gmo = new MQGetMessageOptions();
            // 2; Get messages under sync point control 在同步点控制下获取消息
            gmo.options += CMQC.MQGMO_SYNCPOINT;
            // 1; Wait if no messages on the Queue 如果在队列上没有消息则等待
            gmo.options += CMQC.MQGMO_WAIT;
            // 8192; Fail if Qeue Manager Quiescing 如果队列管理器停顿则失败
            gmo.options += CMQC.MQGMO_FAIL_IF_QUIESCING;
            // Sets the time limit for the wait. 设置等待的毫秒时间限制
            gmo.waitInterval = 100;

             // 关闭了就重新打开
            if(queueManager==null || !queueManager.isConnected()){
                queueManager = new MQQueueManager(getQueueManagerName());
            }

            boolean flag = true;
            while (flag) {
                try {
                    MQMessage inMsg = new MQMessage();
                    // 从队列中取出消息
                    queue.get(inMsg, gmo);

                    byte[] bb = new byte[inMsg.getDataLength()];
                    inMsg.readFully(bb);

                    // queueManager.commit();
                    if (this.ibmMQReceiveCallback != null) {
                        this.ibmMQReceiveCallback.execute(bb);
                    } else {
                        saveReceiveObject(bb);
                    }
                } catch (Exception e) {
                    if (((e instanceof MQException)) && (e.getMessage() != null) && (e.getMessage().contains("2033"))) {
                        flag = false;
                    } else {
                        throw e;
                    }
                }
            }
            return;
        } finally {
            try {
                if (queue != null) {
                    queue.close();
                    queue = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (queueManager != null) {
                try {
                    queueManager.close();
                    queueManager.disconnect();
                } catch (MQException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Save receive object.
     *
     * @param message
     * @throws IOException
     */
    private void saveReceiveObject(byte[] message)
        throws IOException {
        if (message != null) {
            String messageId = UUID.randomUUID().toString();
            File tmpFile = new File(this.receivePath + File.separatorChar + messageId + ".dat");
            System.out.println(tmpFile.getAbsoluteFile());
            FileUtils.writeByteArrayToFile(tmpFile, message);
        }
    }

    /**
     * Create connection.
     *
     * @return
     * @throws MQException
     */
    public MQQueueManager createConnection() throws MQException {
        MQEnvironment.hostname = this.hostname;
        MQEnvironment.channel = this.channel;
        if (this.userid != null) {
            MQEnvironment.userID = this.userid;
        }
        if (this.password != null) {
            MQEnvironment.password = this.password;
        }
        if (this.ccsid != null) {
            MQEnvironment.CCSID = this.ccsid.intValue();
        }
        MQEnvironment.port = this.port.intValue();
        MQEnvironment.properties.put("transport", "MQSeries");

        // 初始化队列管理器对象并连接
        MQQueueManager queueManager = new MQQueueManager(this.queueManagerName);
        return queueManager;
    }

    /**
     * Close connection.
     *
     * @param queueManager
     */
    public void closeConnection(MQQueueManager queueManager) {
        if (queueManager != null) {
            try {
                queueManager.close();
                queueManager.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getHostname() {
        return this.hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return this.port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getQueueManagerName() {
        return this.queueManagerName;
    }

    public void setQueueManagerName(String queueManagerName) {
        this.queueManagerName = queueManagerName;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCcsid() {
        return this.ccsid;
    }

    public void setCcsid(Integer ccsid) {
        this.ccsid = ccsid;
    }

    public String getQueueName() {
        return this.queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getReceivePath() {
        return this.receivePath;
    }

    public void setReceivePath(String receivePath) {
        this.receivePath = receivePath;
    }

    public IBMMQReceiveCallback getIbmMQReceiveCallback() {
        return this.ibmMQReceiveCallback;
    }

    public void setIbmMQReceiveCallback(IBMMQReceiveCallback ibmMQReceiveCallback) {
        this.ibmMQReceiveCallback = ibmMQReceiveCallback;
    }
}
