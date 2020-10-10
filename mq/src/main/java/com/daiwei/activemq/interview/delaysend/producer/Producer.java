package com.daiwei.activemq.interview.delaysend.producer;

import java.util.UUID;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;
import org.apache.activemq.ScheduledMessage;

/**
 * 消息生产者
 * 1.延时投递和定时投递
 */
public class Producer {

    // activemq地址
    private final static String ACTIVEMQ_URL = "failover:(tcp://192.168.173.130:61616,tcp://192.168.173.130:61617,tcp://192.168.173.130:61618)?randomize=false";
    private final static String QUEUE_NAME = "queue-delay";

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
            long delay = 3 * 1000;
            long period = 4 * 1000;
            int repeat = 5;

            // 6.使用消息生产者，发送几条消息到MQ队列
            for (int i = 0; i < 5; i++) {
                // 7.创建消息
                TextMessage textMessage = session.createTextMessage("nihao daiwei " + i);
                textMessage.setJMSMessageID(UUID.randomUUID().toString() + "---daiweitest");
                textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
                textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, period);
                textMessage.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, repeat);
                activeMQMessageProducer.send(textMessage);
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
