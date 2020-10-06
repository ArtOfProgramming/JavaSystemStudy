package com.daiwei.service;

import com.daiwei.bean.User;
import com.daiwei.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public User queryUser(int id) {
        return userDao.queryUser(id);
    }

}
