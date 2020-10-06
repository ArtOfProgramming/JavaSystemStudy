package com.daiwei.Service;

public class UserService {

    public void add() {

        System.out.println("add");
    }

    public void getUser() {
        System.out.println("get");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
