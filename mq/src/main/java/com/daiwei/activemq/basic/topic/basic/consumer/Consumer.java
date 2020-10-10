package com.daiwei.activemq.basic.topic.basic.consumer;

import java.io.IOException;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 主题消息订阅者
 *
 */
public class Consumer {

    // activemq地址
    private final static String ACTIVEMQ_URL = "tcp://192.168.173.130:61616";
    private final static String TOPIC_NAME = "topic1";

    public static void main(String[] args) {
        // 1.创建Active工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = null;
        Session session = null;
        MessageConsumer consumer = null;
        try {
            // 2.通过工厂，获得连接
            connection = activeMQConnectionFactory.createConnection();
            connection.start();

            // 3.创建会话 两个参数 a.事务 b.签收
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // 4.创建目的地 a.队列 b.主题
            Destination destination = session.createTopic(TOPIC_NAME);
            // 5.创建消息生产者
            consumer = session.createConsumer(destination);
            // 6.接受消息 a.receive b.监听
            /*
            1. receive不带参，会一直阻塞
            2. receive带参，等待时间到了，自动返回一个null值，判断跳出
                while (true) {
                    TextMessage message = (TextMessage)consumer.receive(4000L);
                    if (message != null) {
                        System.out.println("消费者接收到消息:" + message.getText());
                    } else {
                        break;
                    }
                }
             */

            // 通过监听的方法
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if (message != null && message instanceof TextMessage) {
                        try {
                            System.out.println("消费者接收到消息:" + ((TextMessage)message).getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            // 9.关闭资源
            if (consumer != null) {
                try {
                    consumer.close();
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
