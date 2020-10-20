package com.daiwei.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class User {

    private Integer id;
    private String name;
    private String password;
    private Integer age;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    public User() {
    }

    public User(Integer id, String name, String password, Integer age, Date birth) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.birth = birth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", birth=" + birth +
                '}';
    }
}
