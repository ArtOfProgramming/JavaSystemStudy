package com.daiwei.activemq.spring.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class SpringMQQueueProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringMQQueueProducer producer = (SpringMQQueueProducer) context.getBean("springMQQueueProducer");

        for (int i = 0; i < 5; i++) {
            final int id = i;
            producer.jmsTemplate.send(new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                  Message message = session.createTextMessage("nihao spring jms " + id);
                  return message;
                }
            });
        }
    }
}
