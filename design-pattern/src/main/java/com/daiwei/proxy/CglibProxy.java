package com.daiwei.proxy;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Tank2.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
                throws Throwable {
                System.out.println("before");
                Object b = method.invoke(new Tank2(), objects);
//                Object b = methodProxy.invoke(new Tank2(), objects);
                System.out.println("after");
                return b;
            }
        });
        Tank2 tank2 = (Tank2) enhancer.create();
        tank2.move();
    }
}


class Tank2 {

    public void move() {
        System.out.println("tank move3");
    }
}