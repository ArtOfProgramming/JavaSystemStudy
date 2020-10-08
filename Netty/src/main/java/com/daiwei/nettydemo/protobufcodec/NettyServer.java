package com.daiwei.nettydemo.protobufcodec;

import com.daiwei.nettydemo.protobuf.StudentPOJO;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

public class NettyServer {

    public static void main(String[] args) throws InterruptedException {

        // 1.创建BossGroup 和 WorkGroup
        // 2.bossGroup 只是处理连接请求，真正和客户端业务处理会交给workGroup。
        // 3.两个都是无限循环
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup(2);

        try {
            // 创建服务器端的启动对象，配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();

            // 使用链式编程设置参数
            bootstrap.group(bossGroup, workGroup) // 设置两个线程组
                .channel(NioServerSocketChannel.class)   // 使用NioSocketChannel作为服务器的通道实现
                .option(ChannelOption.SO_BACKLOG, 128) // 设置线程队列连接个数
                .childOption(ChannelOption.SO_KEEPALIVE, true) // 设置保持活动连接状态
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    // 给pipeline 设置处理器
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("decoder", new ProtobufDecoder(MyDataInfo.MyMessage.getDefaultInstance()));
                        pipeline.addLast(new NettyServerHandler());
                    }
                }); // 给workGroup 的 eventloop 对应的管道设置处理器

            System.out.println("服务器 isready");

            // 绑定一个端口并同步，生成一个channelFuture对象
            ChannelFuture channelFuture = bootstrap.bind(6668).sync();

            channelFuture.addListener(future -> {
               if (future.isSuccess()) {
                   System.out.println("服务器监听6668端口成功");
               } else {
                   System.out.println("服务器监听6668端口失败");
               }
            });

            // 对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {

        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
