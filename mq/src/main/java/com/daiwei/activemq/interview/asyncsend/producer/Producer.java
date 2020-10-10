package com.daiwei.activemq.interview.asyncsend.producer;

import java.util.UUID;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;

/**
 * 消息生产者
 * 1.高可用 （主从集群）
 * 2.异步发送 activemq默认异步 a.（url+jms.useAsyncSend=true） b.activeMQConnectionFactory.setUseAsyncSend(true)  ((ActiveMQConnection)connection).setUseAsyncSend(true)
 * 异步发送，消费速度远小于发送速度时，可能丢失。mq挂掉时，内存数据全部丢失。同步发送会阻塞，等待broker回复成功。异步发送，可以自己加毁掉，需要ActiveMQMessageProducer
 */
public class Producer {

    // activemq地址
    private final static String ACTIVEMQ_URL = "failover:(tcp://192.168.173.130:61616,tcp://192.168.173.130:61617,tcp://192.168.173.130:61618)?randomize=false";
    private final static String QUEUE_NAME = "queue-async";

    public static void main(String[] args) {
        // 1.创建Active工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = null;
        Session session = null;
        ActiveMQMessageProducer activeMQMessageProducer = null;
        try {
            // 2.通过工厂，获得连接
            activeMQConnectionFactory.setUseAsyncSend(true);
            connection = activeMQConnectionFactory.createConnection();
            connection.start();

            // 3.创建会话 两个参数 a.事务 b.签收
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // 4.创建目的地 a.队列 b.主题
            Destination destination = session.createQueue(QUEUE_NAME);
            // 5.创建消息生产者
            activeMQMessageProducer =  (ActiveMQMessageProducer) session.createProducer(destination);
            // 6.使用消息生产者，发送几条消息到MQ队列
            for (int i = 0; i < 5; i++) {
                // 7.创建消息
                TextMessage textMessage = session.createTextMessage("nihao daiwei " + i);
                textMessage.setJMSMessageID(UUID.randomUUID().toString() + "---daiweitest");
                String msgID = textMessage.getJMSMessageID();
                // 8.发送消息
                activeMQMessageProducer.send(textMessage, new AsyncCallback() {
                    @Override
                    public void onSuccess() {
                        System.out.println(msgID + "消息发送成功");
                    }

                    @Override
                    public void onException(JMSException e) {
                        System.out.println(msgID + "消息发送失败");
                        // 可以加上重试及存储后续重试逻辑
                    }
                });
            }
            System.out.println("消息发布完成");
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            // 9.关闭资源
            if (activeMQMessageProducer != null) {
                try {
                    activeMQMessageProducer.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
