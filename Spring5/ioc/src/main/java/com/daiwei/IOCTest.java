package com.daiwei;

import com.daiwei.Service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@ComponentScan("com.daiwei")
public class IOCTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(IOCTest.class);
//        User user = (User)applicationContext.getBean("user");
//        System.out.println(user.toString());
//        System.out.println((User) applicationContext.getBean("userFactoryBean"));
        UserService userService = (UserService)applicationContext.getBean("userService");
        userService.add(null);
        userService.getUser("hello");
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        System.out.println("*********************************");
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        System.out.println("*********************************");
        System.out.println((byte)210);
    }
}
