package com.daiwei.activemq.basic.topic.pubandsub.producer;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 主题消息发布者
 */
public class Publisher {

    // activemq地址
    private final static String ACTIVEMQ_URL = "tcp://192.168.173.130:61616";
    private final static String TOPIC_NAME = "topic1_persistent";

    public static void main(String[] args) {
        // 1.创建Active工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = null;
        Session session = null;
        MessageProducer publisher = null;
        try {
            // 2.通过工厂，获得连接
            connection = activeMQConnectionFactory.createConnection();

            // 3.创建会话 两个参数 a.事务 b.签收
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // 4.创建目的地 a.队列 b.主题
            Topic topic = session.createTopic(TOPIC_NAME);
            // 5.创建消息生产者
            publisher = session.createProducer(topic);
            publisher.setDeliveryMode(DeliveryMode.PERSISTENT);
            connection.start();
            // 6.使用消息生产者，发送几条消息到MQ队列
            for (int i = 0; i < 5; i++) {
                // 7.创建消息
                TextMessage textMessage = session.createTextMessage("nihao daiwei " + i);
                // 8.发送消息
                publisher.send(textMessage);
            }
            System.out.println("消息发布完成");
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            // 9.关闭资源
            if (publisher != null) {
                try {
                    publisher.close();
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
