package net.cloudkit.transform;

import com.ibm.mq.*;

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
