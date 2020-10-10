package com.daiwei.activemq.broker;

import java.io.IOException;
import org.apache.activemq.broker.BrokerService;

/**
 * 嵌入式broker
 */

public class EmbedBroker {

    public static void main(String[] args) {

        // ActiceMQ也支持在vm中通讯基于嵌入式的broker
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        try {
            brokerService.addConnector("tcp://localhost:61616");
            brokerService.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
