package net.cloudkit.transform;

import com.ibm.mq.*;
import com.ibm.mq.constants.CMQC;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * API reason codes
 * https://www.ibm.com/support/knowledgecenter/SSFKSJ_9.0.0/com.ibm.mq.tro.doc/q040710_.htm
 * <p>
 * IBM MQ Downloads
 * https://www.ibm.com/developerworks/mydeveloperworks/blogs/messaging/entry/downloads?lang=en
 */
public class IBMMQComponent {

    public static void main(String[] args) {

        // 回执 QLCqhz01 QTCqhz01
        // IBMMQComponent mq = new IBMMQComponent("120.76.96.117", Integer.valueOf(1401), "SYSTEM.DEF.SVRCONN", "QMCqhz01", "TEST_LOCAL", "D:\\trans_mq\\downloads");
        IBMMQComponent mq = new IBMMQComponent();
        mq.setHostname("192.168.1.1");
        mq.setPort(1401);
        mq.setChannel("SYSTEM.DEF.SVRCONN");
        mq.setQueueManagerName("QMCqhz01");
        mq.setQueueName("TEST_LOCAL");
        mq.setCcsid(Integer.valueOf(819));
        mq.setReceivePath("D:\\Program Files\\ePortClient\\exchange\\downloads");
        // mq.setUserId("Administrator");
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

        while (true) {
            try {
                mq.receive();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static int MQ_DEFAULT_PORT = 1414;
    public static int MQ_DEFAULT_CCSID = 1381;
    public static String MQ_DEFAULT_CONN_CHANNEL = "SYSTEM.DEF.SVRCONN";

    // MQ服务器的IP地址
    private String hostname;
    // MQ端口
    private Integer port = MQ_DEFAULT_PORT;

    // 服务器连接的通道
    private String channel = MQ_DEFAULT_CONN_CHANNEL;
    // MQ的队列管理器名称
    private String queueManagerName;
    // MQ远程队列的名称
    private String queueName;
    // 服务器MQ服务使用的编码 1381
    private Integer ccsid = MQ_DEFAULT_CCSID;

    private String userId;
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
            /**
             * CMQC.MQOO_OUTPUT: Open the queue to put messages.
             * CMQC.MQOO_FAIL_IF_QUIESCING: The MQOPEN call fails if the queue manager is in quiescing state. This option is valid for all types of object.
             * CMQC.MQOO_INQUIRE: Open the object to query attributes.
             *
             * https://www.ibm.com/support/knowledgecenter/SSFKSJ_9.0.0/com.ibm.mq.javadoc.doc/WMQJavaClasses/com/ibm/mq/constants/CMQC.html
             */
            int openOptions = CMQC.MQOO_OUTPUT | CMQC.MQOO_FAIL_IF_QUIESCING | CMQC.MQOO_INQUIRE;

            queue = queueManager.accessQueue(this.queueName, openOptions, null, null, null);
            // delete the queue when it is closed
            //queue.setCloseOptions(MQConstants.MQCO_DELETE)

            // TODO
            //currentDepth = queue.getCurrentDepth();

            // 定义一个消息
            MQMessage message = new MQMessage();
            //message.format = CMQC.MQFMT_STRING;
            message.correlationId = id.getBytes();
            // 将数据放入消息缓冲区
            message.write(data);

            // 设置写入消息的属性（默认属性）
            MQPutMessageOptions pmo = new MQPutMessageOptions();
            queue.put(message, pmo);

            queueManager.commit();
            return;
        } finally {
            /*
            try {
                if (queue != null) {
                    queue.close();
                    queue = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            */

            /*
            if (queueManager != null) {
                try {
                    queueManager.close();
                    queueManager.disconnect();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            */
            closeQueue(queue);
            closeConnection(queueManager);
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
            /**
             * CMQC.MQGMO_SYNCPOINT: The request is to operate within the normal unit-of-work protocols.
             * CMQC.MQGMO_FAIL_IF_QUIESCING: Force the MQGET call to fail if the queue manager is in the quiescing state.
             * CMQC.MQOO_INQUIRE: Open the object to query attributes.
             *
             * https://www.ibm.com/support/knowledgecenter/SSFKSJ_9.0.0/com.ibm.mq.javadoc.doc/WMQJavaClasses/com/ibm/mq/constants/CMQC.html
             */
            int openOptions = CMQC.MQGMO_SYNCPOINT | CMQC.MQGMO_FAIL_IF_QUIESCING | CMQC.MQOO_INQUIRE;

            queue = queueManager.accessQueue(this.queueName, openOptions, null, null, null);
            // delete the queue when it is closed
            //queue.setCloseOptions(MQConstants.MQCO_DELETE)

            MQGetMessageOptions gmo = new MQGetMessageOptions();
            // Get messages under sync point control 在同步点控制下获取消息
            gmo.options += CMQC.MQGMO_SYNCPOINT;
            // Wait if no messages on the Queue 如果在队列上没有消息则等待
            gmo.options += CMQC.MQGMO_WAIT;
            // Fail if Qeue Manager Quiescing 如果队列管理器停顿则失败
            gmo.options += CMQC.MQGMO_FAIL_IF_QUIESCING;
            // Sets the time limit for the wait. 设置等待的毫秒时间限制
            gmo.waitInterval = 100;

            // 关闭了就重新打开
            if (queueManager == null || !queueManager.isConnected()) {
                queueManager = new MQQueueManager(getQueueManagerName());
            }

            int currentDepth = queue.getCurrentDepth();
            System.out.println("该队列当前的深度为:" + currentDepth);

            // 取出消息方法一 isOverload
            while (currentDepth-- > 0) {
                try {
                    MQMessage inMessage = new MQMessage();

                    // 从队列中取出消息
                    queue.get(inMessage, gmo);

                    // LOG.info("Consumed one message from [" + this.toString() + "].");
                    // LOG.info("消息的大小为：" + inMessage.getDataLength());
                    // new Long((long)inMessage.getTotalMessageLength())
                    // Long.valueOf(inMessage.putDateTime.getTimeInMillis())
                    // LOG.info("消息的内容：\n" + inMessage.readStringOfByteLength(inMessage.getDataLength()));

                    byte[] bytes = new byte[inMessage.getDataLength()];
                    inMessage.readFully(bytes);

                    // queueManager.commit();
                    if (this.ibmMQReceiveCallback != null) {
                        this.ibmMQReceiveCallback.execute(bytes);
                    } else {
                        saveReceiveObject(bytes);
                    }
                } catch (MQException e) {
                    // 并发取出时可能已取空
                    if(e.completionCode == 2033) {
                        /*
                        if(LOG.isDebugEnabled()) {
                            LOG.debug("Queue [" + this.toString() + "] is empty.");
                        }
                        */
                        break;
                    } else {
                        throw e;
                    }
                }
            }

            // 取出消息方法二
            /*
            boolean flag = true;
            while (flag) {
                try {
                    MQMessage inMessage = new MQMessage();

                    // 从队列中取出消息
                    queue.get(inMessage, gmo);
                    System.out.println("消息的大小为：" + inMessage.getDataLength());
                    System.out.println("消息的内容：\n" + inMessage.readStringOfByteLength(inMessage.getDataLength()));

                    byte[] bytes = new byte[inMessage.getDataLength()];
                    inMessage.readFully(bytes);

                    // queueManager.commit();
                    if (this.ibmMQReceiveCallback != null) {
                        this.ibmMQReceiveCallback.execute(bytes);
                    } else {
                        saveReceiveObject(bytes);
                    }
                } catch (MQException e) {
                    if (((e instanceof MQException)) && (e.getMessage() != null) && (e.getMessage().contains("2033"))) {
                        flag = false;
                    } else {
                        throw e;
                    }

                    *//*
                    if(e.completionCode == 2033) {
                        if(LOG.isDebugEnabled()) {
                            LOG.debug("Queue [" + this.toString() + "] is empty.");
                        }
                        break;
                    }
                    *//*
                }
            }
            */
            return;
        } finally {
            /*
            try {
                if (queue != null) {
                    queue.close();
                    queue = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            */

            /*
            if (queueManager != null) {
                try {
                    queueManager.close();
                    queueManager.disconnect();
                } catch (MQException e) {
                    e.printStackTrace();
                }
            }
            */

            closeQueue(queue);
            closeConnection(queueManager);
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
        MQEnvironment.port = this.port.intValue();
        MQEnvironment.channel = this.channel;
        if (this.ccsid != null) {
            MQEnvironment.CCSID = this.ccsid.intValue();
        }
        if (this.userId != null) {
            MQEnvironment.userID = this.userId;
        }
        if (this.password != null) {
            MQEnvironment.password = this.password;
        }
        MQEnvironment.properties.put("transport", "MQSeries");

        /*
        Properties props = new Properties();
        props.put("hostname", this.hostname);
        props.put("port", Integer.valueOf(this.port));
        props.put("channel", this.channel);
        props.put("CCSID", Integer.valueOf(this.ccsid));
        if(this.userId != null) {
            props.put("userID", this.userId);
        }
        if(this.password != null) {
            props.put("password", this.password);
        }
        */

        // 初始化队列管理器对象并连接
        //MQQueueManager queueManager = new MQQueueManager(this.queueManagerName, props);
        MQQueueManager queueManager = new MQQueueManager(this.queueManagerName);

        return queueManager;
    }

    public void closeQueue(MQQueue queue) {
        try {
            if (queue != null) {
                queue.close();
            }
        } catch (Exception e) {
            //LOG.error("Failed to close MQQueue.", e);
        } finally {
            queue = null;
        }
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
                // e.printStackTrace();
                //LOG.error("Failed to close MQQueueManager.", e);
            } finally {
                queueManager = null;
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

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String toString() {
        return this.getClass().getName() + ", { IBM MQ://" + (this.userId != null && !this.userId.isEmpty()?this.userId + "@":"") + this.hostname + ":" + this.port + "/" + this.queueManagerName + "/" + this.channel + "/" + this.queueName;
    }
}

interface IBMMQReceiveCallback {

    void execute(byte[] paramArrayOfByte);
}
