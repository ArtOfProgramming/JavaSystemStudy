package com.daiwei.bean;

import com.daiwei.bean.User.UserBuilder;

public class User {

    private int id;
    private String name;
    private int age;
    private int balance;

    public static class UserBuilder {
        protected int a;
        private User user = new User();

        public UserBuilder(int a) {
            this.a = a;
        }

        public UserBuilder nameBuilder(String name) {
            user.name = name;
            return this;
        }

        public UserBuilder ageBuilder(int age){
            user.age = age;
            return this;
        }

        public UserBuilder balanceBuilder(int balance) {
            user.balance = balance;
            return this;
        }

        public User build(){
            return user;
        }
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", age=" + age +
            ", balance=" + balance +
            '}';
    }

    public static void main(String[] args) {
        ClassA classA = new ClassA();
    }
}

class ClassA {

    public ClassA() {
        UserBuilder userBuilder = new UserBuilder(10);
        System.out.println(userBuilder.a);
    }
}