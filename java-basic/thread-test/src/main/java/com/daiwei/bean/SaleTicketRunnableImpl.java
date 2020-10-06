package com.daiwei.bean;

import java.util.Formatter;

public class SaleTicketRunnableImpl implements Runnable {

    private static int ticket = 100;

    private static Object object = new Object();

    @Override
    public void run() {
        synchronized (object) {

            while (ticket > 0) {

                System.out.println(new Formatter().format("正在卖第%d票", ticket));
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ticket--;
            }
        }
    }
}
