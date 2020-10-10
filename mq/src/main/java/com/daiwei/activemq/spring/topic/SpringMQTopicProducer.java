package com.daiwei.activemq.spring.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class SpringMQTopicProducer {

    @Autowired
    @Qualifier("jmsTemplate1")
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringMQTopicProducer producer = (SpringMQTopicProducer) context.getBean("springMQTopicProducer");

        for (int i = 0; i < 5; i++) {
            final int id = i;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            producer.jmsTemplate.send(new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                  Message message = session.createTextMessage("nihao spring jms topic " + id);
                  return message;
                }
            });
        }
    }
}
