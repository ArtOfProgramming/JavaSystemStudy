package com.daiwei.usb;

import com.daiwei.usb.bean.Keyboard;
import com.daiwei.usb.bean.Laptop;
import com.daiwei.usb.bean.Mouse;
import com.daiwei.usb.bean.Usb;

public class UsbTest {

    public static void main(String[] args) {
        Laptop laptop = new Laptop();
        laptop.powerOn();

        laptop.useDevice(new Mouse());
        laptop.useDevice(new Keyboard());

        laptop.powerOff();

        Usb usb = new Usb() {
            public void open() {
                System.out.println("匿名内部类开启方法重写");
            }

            public void close() {
                System.out.println("匿名内部类关闭方法重写");
            }
        };
        usb.open();
    }

    public static void test1(int a) {
        System.out.println(a);
    }
}
