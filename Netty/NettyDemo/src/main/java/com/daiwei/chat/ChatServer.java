package com.daiwei.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 1.编写一个netty群聊系统，非阻塞
 * 2.实现多人群聊
 * 3.服务端：监测用户上线，离线，并实现消息转发
 * 4.客户端：通过channel可以无阻塞发送消息给其他用户，同时可以接受其他用户发送的消息
 */
public class ChatServer {

    int port;

    public ChatServer(int port) {
        this.port = port;
    }

    public void run() {

        // 创建bossGroup和workerGroup
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 配置启动引导serverBootStrap
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast("decoder", new StringDecoder());
                        ch.pipeline().addLast("encoder", new StringEncoder());
                        ch.pipeline().addLast(new ChatServerHandler());
                    }
                });

            // 绑定端口开启监听
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

            channelFuture.addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println("服务器监听" + port + "端口成功");
                } else {
                    System.out.println("服务器监听" + port + "端口失败");
                }
            });

            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new ChatServer(6669).run();
    }
}
