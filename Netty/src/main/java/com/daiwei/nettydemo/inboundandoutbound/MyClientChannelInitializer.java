package com.daiwei.nettydemo.inboundandoutbound;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();

        channelPipeline.addLast(new MyLongToByteEncoder());
        channelPipeline.addLast(new MyByteToLongDecoder2());
        channelPipeline.addLast(new MyClientHandler());
    }
}
