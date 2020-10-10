package com.daiwei.activemq.broker;

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
 * 消息消费者
 *
 * 1. 先生产， 只启动1号消费者。问题：1号消费者能消费消息吗？（Y）
 * 2. 先生产， 先启动1号消费者再启动2号消费者，问题：2号消费者还能消费消息吗？ 1号迅速消费完 2号没得消费
 * 3. 先启动两个消费者， 消费策略为轮训 先到先得
 * 4. MQ挂了，消息的持久化和丢失情况
 */
public class Consumer {

    // activemq地址
    private final static String ACTIVEMQ_URL = "tcp://localhost:61616";
    private final static String QUEUE_NAME = "queue1";

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
            Destination destination = session.createQueue(QUEUE_NAME);
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
