package com.daiwei.chibaozi;

import com.daiwei.chibaozi.bean.Baozi;
import com.daiwei.chibaozi.bean.Foodie;
import com.daiwei.chibaozi.bean.Worker;

public class BaoziTest {

    public static void main(String[] args) {
        Baozi baozi = new Baozi();
        new Thread(new Worker(baozi)).start();
        new Thread(new Foodie(baozi)).start();


    }
}
