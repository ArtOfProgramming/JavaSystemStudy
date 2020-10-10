package com.daiwei.activemq.spring.topic;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * spring-jms topic
 * spring整合active的topic时，消息丢失严重（原因待分析，发送时睡眠会有帮助）
 * listener的方式没有问题
 */
@Service
public class SpringMQTopicConsumer {

    @Autowired
    @Qualifier("jmsTemplate1")
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringMQTopicConsumer consumer = (SpringMQTopicConsumer) context.getBean("springMQTopicConsumer");
        TextMessage message = (TextMessage)consumer.jmsTemplate.receive();
        while (message != null) {
            try {
                System.out.println(message.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
            message = (TextMessage)consumer.jmsTemplate.receive();
        }
//        String retValue = (String) consumer.jmsTemplate.receiveAndConvert();
//        while (retValue != null) {
//            System.out.println(retValue);
//            retValue = (String) consumer.jmsTemplate.receiveAndConvert();
//        }
        System.out.println("*****");
    }
}
