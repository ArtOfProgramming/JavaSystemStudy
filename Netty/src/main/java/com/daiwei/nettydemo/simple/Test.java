package com.daiwei.nettydemo.simple;

import io.netty.util.NettyRuntime;

/**
 * 当前机器的核心数
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(NettyRuntime.availableProcessors());
    }
}
