package com.daiwei.state;

public class ThreadStateTest {

    public static void main(String[] args) {
        Thread thread = null;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("睡眠被打断");
                    e.printStackTrace();
                }
                System.out.println("代维你好");
            }
        });
        System.out.println(thread.getState());
        thread.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        System.out.println(thread.getState());
        thread.stop();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.getState());
    }
}
