package com.daiwei.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

//@Aspect
public class LoggerMethod {

    @Pointcut("execution(* com.daiwei.service.AService.*(..))")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void logger() {
        System.out.println("logger");
    }
}
