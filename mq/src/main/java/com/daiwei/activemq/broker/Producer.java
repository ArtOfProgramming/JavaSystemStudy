package com.daiwei.activemq.broker;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息生产者
 */
public class Producer {

    // activemq地址
    private final static String ACTIVEMQ_URL = "tcp://localhost:61616";
    private final static String QUEUE_NAME = "queue1";

    public static void main(String[] args) {
        // 1.创建Active工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;
        try {
            // 2.通过工厂，获得连接
            connection = activeMQConnectionFactory.createConnection();
            connection.start();

            // 3.创建会话 两个参数 a.事务 b.签收
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // 4.创建目的地 a.队列 b.主题
            Destination destination = session.createQueue(QUEUE_NAME);
            // 5.创建消息生产者
            producer = session.createProducer(destination);
            // 6.使用消息生产者，发送几条消息到MQ队列
            for (int i = 0; i < 5; i++) {
                // 7.创建消息
                TextMessage textMessage = session.createTextMessage("nihao daiwei " + i);
                // 8.发送消息
                producer.send(textMessage);
            }
            System.out.println("消息发布完成");
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            // 9.关闭资源
            if (producer != null) {
                try {
                    producer.close();
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
