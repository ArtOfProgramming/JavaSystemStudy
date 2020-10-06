package com.daiwei.bean;

import java.util.Arrays;
public class User {

    private String name;
    private int age;
    private String[] contactNames;

    public void init() {
        System.out.println("User工厂开始初始化");
    }

    public void destroy() {
        System.out.println("User工厂开始销毁");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("设置参数");
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User() {
        System.out.println("构造函数调用");
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String[] getContactNames() {
        return contactNames;
    }

    public void setContactNames(String[] contactNames) {
        this.contactNames = contactNames;
    }

    @Override
    public String toString() {
        return "User{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", contactNames=" + Arrays.toString(contactNames) +
            '}';
    }
}
