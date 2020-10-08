package com.daiwei.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {

    public static void main(String[] args) throws Throwable {
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles","true");
        Moveable1 m = (Moveable1) Proxy.newProxyInstance(Tank1.class.getClassLoader(), new Class[]{
            Moveable1.class
        }, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object o = method.invoke(new Tank1());
                System.out.println("log tank");
                return o;
            }
        });
        m.move();
    }
}

interface Moveable1 {
    void move();
}

class Tank1 implements Moveable1  {

    @Override
    public void move() {
        System.out.println("tank move");
    }
}
