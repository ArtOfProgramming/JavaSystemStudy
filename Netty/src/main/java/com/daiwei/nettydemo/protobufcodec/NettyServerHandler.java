package com.daiwei.nettydemo.protobufcodec;

import com.daiwei.nettydemo.protobuf.StudentPOJO.Student;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * 使用SimpleChannelInboundHandler 可以不用强转类型
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 读取数据实际（这里我们可以读取客户端发送的消息）
     * @param ctx 上下文对象，含有 管道pipeline， 通道channel，地址
     * @param msg 客户端发送的数据，默认是Object
     * @throws Exception
     */
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        // 从客户端接受Student
//        StudentPOJO.Student student = (StudentPOJO.Student) msg;
//        System.out.printf("收到客户端的数据：" + student.toString());
//    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.printf("收到客户端的数据：" + msg.toString());
    }

    /**
     * 数据读取完毕
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 将数据写入到缓冲并刷新
        // 一般讲，我们对这个发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端", CharsetUtil.UTF_8));
    }

    /**
     * 处理异常，一般是需要关闭通道
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
