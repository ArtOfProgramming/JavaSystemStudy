package com.daiwei.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import java.nio.charset.Charset;

public class Unpooled2 {

    public static void main(String[] args) {

        ByteBuf byteBuf = Unpooled.copiedBuffer("hello, daiwei", CharsetUtil.UTF_8);

        if (byteBuf.hasArray()) {

            byte[] bytes = byteBuf.array();
            System.out.println(new String(bytes, CharsetUtil.UTF_8));

            System.out.println("byteBuf=" + byteBuf);

            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.capacity());
            System.out.println(byteBuf.readableBytes());

            System.out.println(byteBuf.getCharSequence(0, 5, Charset.forName("utf-8")));
        }
    }
}
