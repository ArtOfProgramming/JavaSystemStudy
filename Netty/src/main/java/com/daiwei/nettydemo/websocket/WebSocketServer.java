package com.daiwei.nettydemo.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketServer {

    public static void main(String[] args) {

        //创建两个线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(); //8个NioEventLoop
        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workerGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    // 因为基于Http协议，使用Http协议的编码和解码器
                    pipeline.addLast(new HttpServerCodec());

                    // 是以块方式写的，添加ChunckedWriterHandler处理器
                    pipeline.addLast(new ChunkedWriteHandler());

                    /*
                     1.http数据在传输过程中是分段的，HttpObjectAggregator,就是可以将多个段聚合起来
                     2.这就是为什么浏览器发送大量数据时，就会发出多次http请求
                     */
                    pipeline.addLast(new HttpObjectAggregator(8192));

                    /*
                    1.对应websocket,它的数据是以帧（frame）形式传递的
                    2.可以看到WebSocketFrame下面有六个子类
                    3.浏览器请求时ws://localhost:7000/xxx 表示请求的uri
                    4.WebSocketServerProtocolHandler将http协议升级为ws协议，保持长连接
                     */
                    pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));

                    // 自定义handler 处理业务逻辑
                    pipeline.addLast(new WebSocketHandler());
                }
            });

            //启动服务器
            ChannelFuture channelFuture = serverBootstrap.bind(7000).sync();
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
