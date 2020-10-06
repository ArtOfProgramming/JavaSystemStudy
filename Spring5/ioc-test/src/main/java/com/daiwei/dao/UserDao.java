package com.daiwei.dao;

import com.daiwei.bean.User;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    public void add(User user) {
        System.out.println("insert into user (...) values (...)");
    }

    public User getUser(String name) {
        System.out.println("select * from user where name = ?");
        return null;
    }
}
