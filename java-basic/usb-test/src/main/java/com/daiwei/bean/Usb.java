package com.daiwei.bean;

/**
 * Usb接口
 */
public interface Usb {

    public User user = new User();

    public abstract void open();

    public abstract void close();
}
