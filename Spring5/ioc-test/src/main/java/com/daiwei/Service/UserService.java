package com.daiwei.Service;

import com.daiwei.dao.UserDao;
import com.daiwei.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    public void add(User user) {
        userDao.add(user);
    }

    public User getUser(String name) {
        return userDao.getUser(name);
    }

}
