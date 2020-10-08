package com.daiwei.dubborpc.provider;

import com.daiwei.dubborpc.netty.NettyServer;

public class RpcServerBootStrap {

    public static void main(String[] args) {
        new NettyServer().startServer("127.0.0.1", 6668);
    }
}
