package com.daiwei.tcp.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

/**
 * 自定义协议解析器
 */
public class MyMessageDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyMessageDecoder decode 方法 被调用");
        int len = in.readInt();
        byte[] content = new byte[len];
        in.readBytes(content);
        out.add(new MessageProtocol(len, content));
    }
}
