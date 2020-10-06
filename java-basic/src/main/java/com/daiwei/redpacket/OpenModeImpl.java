package com.daiwei.redpacket;

import java.util.ArrayList;
import java.util.Random;

/**
 * 红包分配策略
 * 1.随机分配
 * 2.随机分配单个红包值不能大于所有金额的一半
 */
public class OpenModeImpl implements OpenMode {

    @Override
    public ArrayList<Integer> divide(int totalMoney, int totalCount) {

        ArrayList<Integer> tempList = new ArrayList<>();

        if (totalMoney < 0) {
            System.out.println("余额不足");
            return null;
        }

        int balance = totalMoney;

        for (int i = 0; i < totalCount; i++) {
            int tempMoney;
            tempMoney = new Random().nextInt(balance);
            while(tempMoney > (totalMoney / 2)) {
                tempMoney = new Random().nextInt(balance);
            }
            balance -= tempMoney;
            tempList.add(tempMoney);
        }

        return tempList;
    }
}
