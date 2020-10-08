package com.daiwei.dubborpc.netty;

import com.daiwei.dubborpc.provider.HelloServiceImpl;
import com.daiwei.dubborpc.publicinterface.HelloService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 获取客户端发送的消息，并调用服务
        System.out.println("msg=" + msg);
        // 客户端在调用服务器api时，我们需要定一个协议
        // 比如我们要求 每次发送消息时，必须以某个字符串开头
        if (msg.toString().startsWith("HelloService#hello#")) {
            String result = new HelloServiceImpl().hello(msg.toString().split("#")[2]);
            ctx.writeAndFlush(result);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
