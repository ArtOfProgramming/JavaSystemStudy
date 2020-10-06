package com.daiwei.thread.create;

import com.daiwei.thread.create.bean.MyThread;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程测试
 * 1.继承Thread类，实现run方法
 * 2.实现Runable接口
 * 3.实现Callable接口，FutureTask
 */

public class ThreadTest {

    public static void main(String[] args) {

        new MyThread().start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("代维你好-2");
            }
        }).start();

        FutureTask futureTask = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                return "代维你好-3";
            }
        });
        new Thread(futureTask).start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
