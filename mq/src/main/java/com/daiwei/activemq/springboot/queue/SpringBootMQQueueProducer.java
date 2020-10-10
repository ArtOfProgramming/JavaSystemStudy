package com.daiwei.activemq.springboot.queue;

import java.util.UUID;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@SpringBootApplication
@ComponentScan(basePackages = {"com.daiwei.activemq.springboot.queue"},
    excludeFilters= {@ComponentScan.Filter(type= FilterType.REGEX, pattern = {"com.daiwei.activemq.springboot.queue.SpringBootMQQueueConsumer"})})
@EnableJms // 开启JMS
@EnableScheduling
public class SpringBootMQQueueProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    public void ProduceMsg() {
        jmsMessagingTemplate.convertAndSend(queue, "****" + UUID.randomUUID().toString().substring(0, 6));
    }

    // 间隔三秒定投
    @Scheduled(fixedDelay = 3000)
    public void produceMsgScheduled() {
        jmsMessagingTemplate.convertAndSend(queue, "****scheduled:" + UUID.randomUUID().toString().substring(0, 6));
        System.out.println("scheduled send ok");
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMQQueueProducer.class, args);
    }
}
