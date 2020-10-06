package com.daiwei;

import com.daiwei.bean.Bank;
import com.daiwei.bean.User;
import com.daiwei.service.UserService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 需要保证数据库中有数据
 */
public class TransactionTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
//        UserService userService = (UserService) context.getBean("userService");
//        User user = new User.UserBuilder(10).nameBuilder("daiwei1").ageBuilder(20).balanceBuilder(1000000000).build();
//        userService.addUser(user);
//        System.out.println(userService.queryUser(16).toString());
        Bank bank1 = (Bank) context.getBean("bank");
        bank1.login(15);
        Bank bank2 =(Bank) context.getBean("bank");
        bank2.login(16);
        System.out.println(bank1.getBalance());
        System.out.println(bank2.getBalance());
        try {
            bank1.transfer(16, 20);
        } catch (Exception e) {

        }
        System.out.println(bank1.getBalance());
        System.out.println(bank2.getBalance());
    }
}
