package com.daiwei;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan
//@EnableAspectJAutoProxy
public class BootSpring {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(BootSpring.class);
        System.out.println(annotationConfigApplicationContext.getBean("AService"));
    }
}
