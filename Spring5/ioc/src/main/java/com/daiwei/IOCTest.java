package com.daiwei;

import com.daiwei.Service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
//        User user = (User)applicationContext.getBean("user");
//        System.out.println(user.toString());
//        System.out.println((User) applicationContext.getBean("userFactoryBean"));
        UserService userService = (UserService)applicationContext.getBean("userService");
        userService.add(null);
        userService.getUser("hello");
        applicationContext.close();
    }
}
