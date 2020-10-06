package com.daiwei.bean;

import java.util.ArrayList;
import java.util.List;

public class Card {

    private List<CartType> list;

    public List<CartType> getList() {
        return list;
    }

    public void setList(List<CartType> list) {
        this.list = list;
    }

    public Card() {
        this.list = new ArrayList<>();
        addNormalCard(list, "红桃");
        addNormalCard(list, "黑桃");
        addNormalCard(list, "方块");
        addNormalCard(list, "梅花");
        addAbnormalCard(list);
    }
    
    public static void addNormalCard(List<CartType> list, String type) {
        list.add(new CartType(type, "2"));
        list.add(new CartType(type, "3"));
        list.add(new CartType(type, "4"));
        list.add(new CartType(type, "5"));
        list.add(new CartType(type, "6"));
        list.add(new CartType(type, "7"));
        list.add(new CartType(type, "8"));
        list.add(new CartType(type, "9"));
        list.add(new CartType(type, "10"));
        list.add(new CartType(type, "J"));
        list.add(new CartType(type, "Q"));
        list.add(new CartType(type, "K"));
        list.add(new CartType(type, "A"));
    }

    public static void addAbnormalCard(List<CartType> list) {
        list.add(new CartType("大王", ""));
        list.add(new CartType("小王", ""));
    }
}
