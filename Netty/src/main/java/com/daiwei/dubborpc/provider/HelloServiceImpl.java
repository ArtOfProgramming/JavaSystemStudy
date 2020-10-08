package com.daiwei.dubborpc.provider;

import com.daiwei.dubborpc.publicinterface.HelloService;

public class HelloServiceImpl implements HelloService {

    // 当有消费方调用该方法时，就返回一个结果
    @Override
    public String hello(String name) {
        System.out.println("收到客户端消息:" + name);
        return new StringBuilder("hello ").append(name).toString();
    }
}
