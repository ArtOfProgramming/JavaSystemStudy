package com.daiwei.nettydemo.inboundandoutbound;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

/**
 * ByteToMessageDecoder
 * 其他解码器
 * ReplayingDecoder
 * LinedBasedFrameDecoder 使用(\n或\r\n作为分隔符解析数据)
 * DelimiterBasedFrameDecoder 使用自定义特殊符号作为分隔符解析数据
 * HttpObjectDecoder 一个Http数据的解码器
 * LengthFieldBasedFrameDecoder 通过指定长度来标识整包消息，这样就可以自动处理粘包和半包问题
 * ZlibDecoder 压缩传输
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {

    /**
     *
     * decode 会根据接收的数据，被调用多次, 直到确定没有新的元素被添加到list
     * , 或者是ByteBuf 没有更多的可读字节为止
     * 如果list out 不为空，就会将list的内容传递给下一个 channelinboundhandler处理, 该处理器的方法也会被调用多次
     *
     * @param ctx 上下文对象
     * @param in 入站的 ByteBuf
     * @param out List 集合，将解码后的数据传给下一个handler
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // long类型8个字节,需要判断有8个字节，才能读取一个long
        if (in.readableBytes() >= 8) {
            out.add(in.readLong());
        }
    }
}
