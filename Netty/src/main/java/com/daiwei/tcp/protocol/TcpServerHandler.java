package com.daiwei.tcp.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import java.util.UUID;

public class TcpServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {


        System.out.println("服务器接收到数据：" + msg.toString());
        count++;
        System.out.println("服务器接收到消息量=" + count);

        // 服务器回送数据，回送一个随机id
        byte[] content = UUID.randomUUID().toString().getBytes(CharsetUtil.UTF_8);
        int len = content.length;
        ctx.writeAndFlush(new MessageProtocol(len, content));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
