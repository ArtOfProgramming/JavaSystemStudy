package com.daiwei.nettydemo.protobuf;

import com.daiwei.nettydemo.protobuf.StudentPOJO.Student;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当通道就绪就会触发该消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        // 发送一个Student对象给服务端
        Student student = Student.newBuilder().setId(3).setName("daiwei").build();
        ctx.writeAndFlush(student);
    }

    /**
     * 当通道有读取事件时，会触发
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("接收到服务器的消息：" + byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("服务器的地址是：" + ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();
    }
}
