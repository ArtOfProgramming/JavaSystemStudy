package com.daiwei.tcp.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class TcpClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 使用客户端发送10条数据 "今天天气冷，吃火锅"
        for (int i = 0; i < 10; i++) {
            String msg = "今天天气冷，吃火锅";
            byte[] content = msg.getBytes(CharsetUtil.UTF_8);
            int len = content.length;

            ctx.writeAndFlush(new MessageProtocol(len, content));
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        System.out.println("收到服务器消息:" + msg.toString());
        count++;
        System.out.println("收到服务器消息次数：" + count);
    }

    //    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
//        byte[] bytes = new byte[msg.readableBytes()];
//        msg.readBytes(bytes);
//
//        String message = new String(bytes, CharsetUtil.UTF_8);
//        System.out.println("收到服务器消息:" + message);
//        count++;
//        System.out.println("收到服务器消息次数：" + count);
//    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
