package com.daiwei.activemq.spring.queue;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * Spring-jms queue
 */
@Service
public class SpringMQQueueConsumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringMQQueueConsumer consumer = (SpringMQQueueConsumer) context.getBean("springMQQueueConsumer");
        String retValue = (String) consumer.jmsTemplate.receiveAndConvert();
        while (retValue != null) {
            System.out.println(retValue);
            retValue = (String) consumer.jmsTemplate.receiveAndConvert();
        }
//        TextMessage message = (TextMessage)consumer.jmsTemplate.receive();
//        while (message != null) {
//            try {
//                System.out.println(message.getText());
//            } catch (JMSException e) {
//                e.printStackTrace();
//            }
//            message = (TextMessage)consumer.jmsTemplate.receive();
//        }
    }
}
