package com.daiwei.doudizhu.bean;

import java.util.ArrayList;
import java.util.List;

public class Dutu {
    private String name;
    private List<CartType> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CartType> getList() {
        return list;
    }

    public void setList(List<CartType> list) {
        this.list = list;
    }

    public void show() {
        System.out.println(name + "牌数:" + list.size());
        for (CartType cartType : this.list) {
            System.out.println(cartType.toString());
        }
        System.out.println("----------------------");
    }

    public Dutu() {
    }

    public Dutu(String name) {
        this.name = name;
        this.list = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Dutu{" +
            "name='" + name + '\'' +
            ", list=" + list +
            '}';
    }
}
