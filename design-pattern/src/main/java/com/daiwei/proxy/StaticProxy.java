package com.daiwei.proxy;

import java.util.Date;
import java.util.Random;

public class StaticProxy {

    public static void main(String[] args) {
        new BreathMarkProxy(new LogProxy(new Tank())).move();
    }
}

interface Moveable {
    void move();
}

class Tank implements Moveable {

    @Override
    public void move() {
        System.out.println("tank move");

        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class LogProxy implements Moveable {

    public LogProxy(Moveable m) {
        this.m = m;
    }

    private Moveable m;

    @Override
    public void move() {
        m.move();
        System.out.println("log tank move");
    }

}

class BreathMarkProxy implements Moveable {

    private Moveable m;

    public BreathMarkProxy(Moveable m) {
        this.m = m;
    }

    @Override
    public void move() {
        long startTime = new Date().getTime();
        m.move();
        long endTime = new Date().getTime();
        System.out.println(endTime - startTime);
    }
}