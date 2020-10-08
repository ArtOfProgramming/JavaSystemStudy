package com.daiwei.nettydemo.inboundandoutbound;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();

        // 入栈的handler进行解码 MyByteToLongDecoder
        channelPipeline.addLast(new MyByteToLongDecoder2());
        channelPipeline.addLast(new MyLongToByteEncoder());
        channelPipeline.addLast(new MyServerHandler());
    }
}
