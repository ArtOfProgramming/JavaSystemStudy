package com.daiwei.dubborpc.consumer;

import com.daiwei.dubborpc.netty.NettyClient;
import com.daiwei.dubborpc.publicinterface.HelloService;

/**
 * 使用了JDK动态代理技术
 */
public class RpcClientBootStrap {

    // 定义协议头
    public static final String providerName = "HelloService#hello#";

    public static void main(String[] args) {

        // 创建一个消费者
        NettyClient customer = new NettyClient();

        HelloService helloService = (HelloService) customer.getBean(HelloService.class, providerName);

        // 通过代理对象调用服务提供者
        String result = helloService.hello("你好 daiwei");
        System.out.println("收到服务器返回" + result);
    }
}
