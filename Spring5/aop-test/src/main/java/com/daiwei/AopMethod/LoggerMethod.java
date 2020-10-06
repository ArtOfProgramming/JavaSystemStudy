package com.daiwei.AopMethod;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)
public class LoggerMethod {

    @Before("execution(* com.daiwei.Service.UserService.*(..))")
    public void logger() {
        System.out.println("logger");
    }

}
