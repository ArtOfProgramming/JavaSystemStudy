package com.daiwei.nio.MappedByteBuffer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/**
 * MappedByteBuffer 可让文件直接在内存修改（堆外内存），操作系统不需要拷贝一次
 */
public class MappedByteBufferTest {

    public static void main(String[] args) throws IOException {

        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\学习\\笔记\\Netty\\code\\file01.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        /**
         * 参数1：读写模式
         * 参数2：起始位置
         * 参数3：映射到内存的长度
         * 实际类型时DirectByteBuffer
         */
        MappedByteBuffer mappedByteBuffer =  fileChannel.map(MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte) 'h');
        mappedByteBuffer.put(1, (byte) 'h');

        fileChannel.close();
    }
}
