package com.daiwei.AopMethod;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;

@Aspect
@Order(2)
public class TimeMethod {

    long startTime;
    long endTime;

    @Before("execution(* com.daiwei.Service.UserService.*(..))")
    public void setStartTime() {
        startTime = System.currentTimeMillis();
        System.out.println("startTime");
    }

    @After("execution(* com.daiwei.Service.UserService.*(..))")
    public void time() {
        endTime = System.currentTimeMillis();
        System.out.println("endTime");
        System.out.println(endTime - startTime);
    }
}
