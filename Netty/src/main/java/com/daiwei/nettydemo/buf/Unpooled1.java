package com.daiwei.nettydemo.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class Unpooled1 {

    public static void main(String[] args) {

        // 创建一个ByteBuf
        // 1.创建对象，该对象包含一个数组arr,是一个byte[10]
        // 2.在netty的buffer中，不需要使用flip进行反转，底层维护了readerIndex和writerIndex
        // 3.通过readerIndex和writerIndex和capacity 分成三个区域
        // 0 - readerIndex 已读区域
        // readerIndex - writerIndex 可读区域
        // writerIndex - capacity 可写区域
        ByteBuf byteBuf = Unpooled.buffer(10);

        for (int i = 0; i < byteBuf.capacity(); i++) {
            byteBuf.writeByte(i);
        }

        // 输出
//        for (int i = 0; i < byteBuf.capacity(); i++) {
//            System.out.println(byteBuf.getByte(i));
//        }
        for (int i = 0; i < byteBuf.capacity(); i++) {
            System.out.println(byteBuf.readByte());
        }
    }
}
