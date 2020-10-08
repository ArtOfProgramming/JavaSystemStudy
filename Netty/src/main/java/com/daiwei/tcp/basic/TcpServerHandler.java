package com.daiwei.tcp.basic;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import java.util.UUID;

public class TcpServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);

        // 将bytes转成String
        System.out.println("服务器接收到数据：" + new String(bytes, CharsetUtil.UTF_8));
        count++;
        System.out.println("服务器接收到消息量=" + count);

        // 服务器回送数据，回送一个随机id
        ByteBuf byteBuf = Unpooled.copiedBuffer(UUID.randomUUID().toString() + " ", CharsetUtil.UTF_8);
        ctx.writeAndFlush(byteBuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
