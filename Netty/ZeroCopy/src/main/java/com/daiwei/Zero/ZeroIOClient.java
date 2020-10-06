package com.daiwei.Zero;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class ZeroIOClient {

    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 7002));
        String filename = "D:\\学习\\笔记\\Netty\\note\\Netty相关资料.zip";

        //得到一个文件channel
        FileChannel fileChannel = new FileInputStream(filename).getChannel();

        //准备发送
        long startTime = System.currentTimeMillis();

        //在linux下一个transferTo 方法就可以完成传输
        //在windows 下 一次调用 transferTo 只能发送8m , 就需要分段传输文件, 而且要主要
        //传输时的位置 =》 课后思考...
        //transferTo 底层使用到零拷贝
        long len = fileChannel.size();
        final long MAX_TRANSFER = 8 * 1024 * 1024;
        long num  = len % MAX_TRANSFER == 0 ? len / MAX_TRANSFER : len / MAX_TRANSFER + 1;
        long transferCount = 0;
        for (long i = 0; i < num; i++) {
            transferCount += fileChannel.transferTo(i * MAX_TRANSFER, MAX_TRANSFER, socketChannel);
        }

        System.out.println("发送的总的字节数 =" + transferCount + " 耗时:" + (System.currentTimeMillis() - startTime));

        //关闭
        fileChannel.close();
    }
}
