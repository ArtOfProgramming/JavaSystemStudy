package com.daiwei.activemq.basic.topic.pubandsub.consumer;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 主题消息订阅者
 */
public class Subscriber {

    // activemq地址
    private final static String ACTIVEMQ_URL = "tcp://192.168.173.130:61616";
    private final static String TOPIC_NAME = "topic1_persistent";

    public static void main(String[] args) {
        // 1.创建Active工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = null;
        Session session = null;
        TopicSubscriber subscriber = null;
        try {
            // 2.通过工厂，获得连接
            connection = activeMQConnectionFactory.createConnection();
            connection.setClientID("z3");

            // 3.创建会话 两个参数 a.事务 b.签收
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // 4.创建目的地 a.队列 b.主题
            Topic topic = session.createTopic(TOPIC_NAME);
            // 5.创建消息生产者
            subscriber = session.createDurableSubscriber(topic, "remark...");
            connection.start();
            // 6.接受消息 a.receive b.监听
            // 1. receive不带参，会一直阻塞
            // 2. receive带参，等待时间到了，自动返回一个null值，判断跳出
            TextMessage message = (TextMessage) subscriber.receive();
            while (message != null) {
                message = (TextMessage) subscriber.receive(4000L);
                if (message != null) {
                    System.out.println("消费者接收到消息:" + message.getText());
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            // 9.关闭资源
            if (subscriber != null) {
                try {
                    subscriber.close();
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
