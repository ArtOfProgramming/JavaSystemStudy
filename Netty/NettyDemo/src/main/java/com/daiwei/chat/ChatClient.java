package com.daiwei.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class ChatClient {

    String host;
    int port;

    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() {

        // 创建一个EventLoopGroup;
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            // 创建引导
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new StringEncoder());
                        ch.pipeline().addLast(new ChatClientHandler());
                    }
                });

            // 连接服务器
            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress(host, port)).sync();

            channelFuture.addListener(future -> {
                if (channelFuture.isSuccess()) {
                    System.out.println("连接上服务器" + channelFuture.channel().remoteAddress());
                } else {
                    System.out.println("连接服务器失败" + channelFuture.channel().remoteAddress());
                }
            });

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String msg = scanner.nextLine();

                channelFuture.channel().writeAndFlush(msg + "\n");
            }

            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new ChatClient("127.0.0.1", 6669).run();

    }
}
