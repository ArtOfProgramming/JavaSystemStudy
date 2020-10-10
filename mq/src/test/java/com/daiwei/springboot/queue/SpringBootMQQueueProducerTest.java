package com.daiwei.springboot.queue;

import com.daiwei.activemq.springboot.queue.SpringBootMQQueueProducer;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest(classes = SpringBootMQQueueProducer.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class SpringBootMQQueueProducerTest {

    @Resource
    private SpringBootMQQueueProducer producer;

    @Test
    public void testProduce() {
        producer.ProduceMsg();
    }
}
