package com.daiwei.bean;

import org.springframework.beans.factory.FactoryBean;

public class UserFactoryBean implements FactoryBean<User> {

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public User getObject() throws Exception {
        User user = new User("nihao", 20);
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
