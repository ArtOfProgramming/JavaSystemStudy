package com.daiwei.dubborpc.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class NettyClient {

    // 创建线程池
    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static NettyClientHandler client;

    // 编写方式使用代理模式，获取一个代理对象

    public Object getBean(final Class<?> serviceClass, final String providerName) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(serviceClass);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
                throws Throwable {
                if (client == null) {
                    initClient("127.0.0.1", 6668);
                }

                // 设置要发送给服务器的信息
                client.setPara(providerName + objects[0]);

                return executorService.submit(client).get();
            }
        });
        return enhancer.create();
//        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class<?> [] {serviceClass},
//            (proxy, method, args) -> {
//                if (client == null) {
//                    initClient("127.0.0.1", 6668);
//                }
//
//                // 设置要发送给服务器的信息
//                client.setPara(providerName + args[0]);
//
//                return executorService.submit(client).get();
//            });
    }

    public void startClient(String hostname, int port) {
        initClient(hostname, port);
    }

    // 初始化客户端
    private static void initClient(String hostname, int port) {

        client = new NettyClientHandler();
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(client);
                    }
                });

            ChannelFuture channelFuture = bootstrap.connect(hostname, port).sync();

//            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
//            eventLoopGroup.shutdownGracefully();
        }


    }
}
