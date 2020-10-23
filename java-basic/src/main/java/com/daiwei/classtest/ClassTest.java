package com.daiwei.classtest;

public abstract class ClassTest {

    private int i;
    public abstract void test1();

    public void test2() {
        System.out.println("test2");
    }

    public ClassTest() {
    }

    public ClassTest(int i) {
        this.i = i;
    }

    public static void main(String[] args) {
        ClassTest classTest = new ClassTest() {
            @Override
            public void test1() {
                System.out.println("test1");
            }
        };
        classTest.test1();
    }
}
