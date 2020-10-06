package com.daiwei.Channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import sun.nio.ch.FileChannelImpl;

public class FileChannelTest {

    public static void main(String[] args) throws IOException {
        // 1.本地文件写
        String str = "daiwei nihao";
        // 创建文件输出流获取文件通道
        String path = "D:\\学习\\笔记\\Netty\\code\\file01.txt";
        FileOutputStream fos = new FileOutputStream(path);
        FileChannel fileChannel = fos.getChannel();

        // 创建byteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        byteBuffer.put(str.getBytes());

        // 反转
        byteBuffer.flip();

        // 将byteBuffer写入到通道
        fileChannel.write(byteBuffer);
        fos.close();

        // 2.本地文件读
        File file= new File(path);
        FileInputStream fis = new FileInputStream(file);
        FileChannel fileChannel1 = fis.getChannel();

        ByteBuffer byteBuffer1 = ByteBuffer.allocate((int)file.length());
        // 将数据从通道读入到缓冲区
        fileChannel1.read(byteBuffer1);
        System.out.println(new String(byteBuffer1.array()));

        fis.close();

        // 3.文件拷贝
        FileInputStream fis3 = new FileInputStream(path);
        FileChannel fileChannel3 = fis3.getChannel();

        FileOutputStream fos3 = new FileOutputStream("D:\\学习\\笔记\\Netty\\code\\file01-copy.txt");
        FileChannel fileChannel4 = fos3.getChannel();


        // 通过buffer
//        ByteBuffer byteBuffer2 = ByteBuffer.allocate(1024);
//
//        while (true) {
//
//            byteBuffer2.clear();
//            int read = fileChannel3.read(byteBuffer2);
//            if (read == -1) {
//                break;
//            }
//            byteBuffer2.flip();
//            fileChannel4.write(byteBuffer2);
//        }

        // 通过调用方法 内部实现还是buffer
        fileChannel4.transferFrom(fileChannel3, 0, fileChannel3.size());

        fis3.close();
        fos3.close();
    }
}
