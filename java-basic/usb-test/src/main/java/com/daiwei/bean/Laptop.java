package com.daiwei.bean;

/**
 *  笔记本电脑类
 */
public class Laptop {

    public void powerOn() {
        System.out.println("打开电脑");
    }

    public void powerOff() {
        System.out.println("关闭电脑");
    }

    public void useDevice(Usb usb) {
        usb.open();
        if (usb instanceof Mouse) {
            Mouse mouse = (Mouse) usb;
            mouse.click();
        } else if (usb instanceof Keyboard) {
            Keyboard mouse = (Keyboard) usb;
            mouse.input();
        }
        usb.close();
    }
}
