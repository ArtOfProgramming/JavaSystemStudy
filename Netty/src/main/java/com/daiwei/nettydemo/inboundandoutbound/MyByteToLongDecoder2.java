package com.daiwei.nettydemo.inboundandoutbound;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import java.util.List;

/**
 * 不需要在对数据长度做管理 由ReplayingDecoder处理
 * 并不是所有的Bytebuf操作都支持，如果调用不支持，会抛出UnsupportedOperationException
 * ReplayingDecoder 在某些情况下可能稍慢于ByteToMessageDecoder,例如网络缓慢并且消息格式复杂时，消息会被拆分成多个碎片，速度变慢
 */
public class MyByteToLongDecoder2 extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyByteToLongDecoder2 被调用");

        out.add(in.readLong());
    }
}
