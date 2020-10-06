package com.daiwei.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    // 定义一个channel组 ，管理所有的channel
    // GlobalEventExecutor.INSTANCE 全局事件执行器，是一个单例
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM--dd HH:mm:ss");

    /**
     * 表示连接一旦被执行，第一个被执行
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        // 将该客户加入聊天的消息推送给其他在线的客户端
        // 该方法会将channelGroup中所有的channel遍历，并发送消息，我们不需要自己遍历
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "加入聊天" + sdf.format(new Date()) + "\n");
        channelGroup.add(channel);
    }

    /**
     * 表示xx断开连接了
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel(); // 触发该消息后，会自动从channelGroup移除
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "离开聊天" + sdf.format(new Date()) + "\n");
    }

    // 表示channel处于活跃状态，提示xx上线
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "上线了!");
    }

    // 表示channel 处于非活跃状态
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "离线了!");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // 获取到当前的channel
        Channel channel = ctx.channel();
        // 这时我们遍历channelGroup, 根据不同的情况，回送不同的消息
        channelGroup.forEach(channel1 -> {
            if (channel1 != channel) { // 不是当前的channel，转发消息
                channel1.writeAndFlush("[客户]" + channel.remoteAddress() + "说:" + msg + " " + sdf.format(new Date()));
            } else {
                channel1.writeAndFlush("[自己]" + channel.remoteAddress() + "说:" + msg + " " + sdf.format(new Date()));
            }
        });
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
