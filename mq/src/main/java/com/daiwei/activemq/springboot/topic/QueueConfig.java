package com.daiwei.activemq.springboot.topic;

import javax.jms.Queue;
import javax.jms.Topic;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Value("${mytopic}")
    private String mytopic;

    @Bean
    public Topic queue() {
        return new ActiveMQTopic(mytopic);
    }
}
