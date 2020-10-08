package com.daiwei.nettydemo.protobufcodec;

import com.daiwei.nettydemo.protobuf.StudentPOJO.Student;
import com.daiwei.nettydemo.protobufcodec.MyDataInfo.MyMessage;
import com.daiwei.nettydemo.protobufcodec.MyDataInfo.MyMessage.DataType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import java.util.Random;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当通道就绪就会触发该消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        // 随机发送Student或者Worker
        int random = new Random(System.currentTimeMillis()).nextInt(2);
        MyDataInfo.MyMessage myMessage = null;
        if (random == 0) { // 发送Student
            myMessage = MyMessage.newBuilder().setDataType(DataType.StudentTyep)
                .setStudent(MyMessage.newBuilder().getStudentBuilder()
                    .setId(2).setName("daiwei").build()).build();
        } else {
            myMessage = MyMessage.newBuilder().setDataType(DataType.WorkerType)
                .setWoker(MyMessage.newBuilder().getWokerBuilder()
                    .setAge(15).setName("daiwei").build()).build();
        }
        ctx.writeAndFlush(myMessage);
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
