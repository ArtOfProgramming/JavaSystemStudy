package com.daiwei.nettydemo.inboundandoutbound;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class MyClientHandler extends SimpleChannelInboundHandler<Long> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClientHandler channelActive被调用");
        ctx.writeAndFlush(125L);

        /* 分析
            1."abcdefgh12345678“ 16个字节
            2.该处理器的前一个handler是MyLongToByteEncoder
            3.MyLongToByteEncoder 父类是 MessageToByteEncoder
            4.父类MessageToByteEncoder中会判断上一步处理的内容的格式是能够encode的类型

           if (acceptOutboundMessage(msg)) {
                @SuppressWarnings("unchecked")
                I cast = (I) msg;
                buf = allocateBuffer(ctx, cast, preferDirect);
                try {
                    encode(ctx, cast, buf);
                } finally {
                    ReferenceCountUtil.release(cast);
                }

                if (buf.isReadable()) {
                    ctx.write(buf, promise);
                } else {
                    buf.release();
                    ctx.write(Unpooled.EMPTY_BUFFER, promise);
                }
                buf = null;
            } else {
                ctx.write(msg, promise);
            }
         */
//        ctx.writeAndFlush(Unpooled.copiedBuffer("abcdefgh12345678", CharsetUtil.UTF_8));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {

        System.out.println("收到服务器" + ctx.channel().remoteAddress() + "的消息：" + msg);
    }

}
