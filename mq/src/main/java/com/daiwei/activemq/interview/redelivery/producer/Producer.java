package com.daiwei.activemq.interview.redelivery.producer;

import java.util.UUID;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.ScheduledMessage;

/**
 * 消息生产者
 * 1.消息重发 a.用了事务，rollback() b.用了事务,没有commit() c.CLIENT_ACKNOWLEDGE，调用receive() 会产生
 * 开启事务，但是没有复现出不提交出现消息的问题。可能是activemq版本问题
 * DLQ死信队列
 * 2.幂等性问题 a.数据库主键 b.redis
 */
public class Producer {

    // activemq地址
    private final static String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    private final static String QUEUE_NAME = "queue-redelivery";

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
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

            // 4.创建目的地 a.队列 b.主题
            Destination destination = session.createQueue(QUEUE_NAME);
            // 5.创建消息生产者
            producer = session.createProducer(destination);


            // 6.使用消息生产者，发送几条消息到MQ队列
            for (int i = 0; i < 5; i++) {
                // 7.创建消息
                TextMessage textMessage = session.createTextMessage("nihao daiwei " + i);
                producer.send(textMessage);
            }
//            session.commit();
            System.out.println("消息发布完成");
        } catch (JMSException e) {
            e.printStackTrace();
            if (session != null) {
                try {
                    session.rollback();
                } catch (JMSException jmsException) {
                    jmsException.printStackTrace();
                }
            }
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
