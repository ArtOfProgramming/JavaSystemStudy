package com.daiwei.tcp.protocol;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.net.InetSocketAddress;

public class TcpClient {

    public static void main(String[] args) {

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new MyTcpClientChannelInitializer());

            // 连接服务器
            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("127.0.0.1", 6668)).sync();

            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
