package com.daiwei.activemq.springboot.topic;

import java.util.UUID;
import javax.jms.Queue;
import javax.jms.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication
@ComponentScan(basePackages = {"com.daiwei.activemq.springboot.topic"},
    excludeFilters= {@ComponentScan.Filter(type= FilterType.REGEX, pattern = {"com.daiwei.activemq.springboot.topic.SpringBootMQTopicConsumer"})})
@EnableJms // 开启JMS
@EnableScheduling
public class SpringBootMQTopicProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Topic topic;

    public void ProduceMsg() {
        jmsMessagingTemplate.convertAndSend(topic, "****" + UUID.randomUUID().toString().substring(0, 6));
    }

    // 间隔三秒定投
    @Scheduled(fixedDelay = 3000)
    public void produceMsgScheduled() {
        jmsMessagingTemplate.convertAndSend(topic, "****scheduled:" + UUID.randomUUID().toString().substring(0, 6));
        System.out.println("scheduled send ok");
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMQTopicProducer.class, args);
    }
}
