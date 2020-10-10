package com.daiwei.activemq.springboot.queue;

import java.util.UUID;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * springboot activemq queue
 */
@Component
@SpringBootApplication
@ComponentScan(basePackages = {"com.daiwei.activemq.springboot.queue"},
    excludeFilters= {@ComponentScan.Filter(type= FilterType.REGEX, pattern = {"com.daiwei.activemq.springboot.queue.SpringBootMQQueueProducer"})})
@EnableJms // 开启JMS
@EnableScheduling
public class SpringBootMQQueueConsumer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @JmsListener(destination = "${myqueue}")
    public void receive(TextMessage message) throws JMSException {
        System.out.println("消费者收到消息" + message.getText());
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMQQueueConsumer.class, args);
    }
}

