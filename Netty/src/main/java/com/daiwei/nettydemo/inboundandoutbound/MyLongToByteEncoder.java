package com.daiwei.nettydemo.inboundandoutbound;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyLongToByteEncoder extends MessageToByteEncoder<Long> {

    /**
     * 编码
     * @param ctx
     * @param msg
     * @param out
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        System.out.printf("MyLongToByteEncoder 中的encoder被调用");
        System.out.println("msg=" + msg);
        out.writeLong(msg);

    }
}
