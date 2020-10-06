package com.daiwei.nio.Selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * selector客户端
 */
public class Client {

    public static void main(String[] args) throws IOException {
        // 创建SocketChannel
        SocketChannel socketChannel = SocketChannel.open();
        // 设置非阻塞
        socketChannel.configureBlocking(false);

        if (!socketChannel.connect(new InetSocketAddress("127.0.0.1", 7001))) {
            while (!socketChannel.finishConnect()) {
                System.out.println("客户端连接需要时间，客户端不会阻塞，可以继续处理其他事情");
            }
        }

        String str = "daiwei nihao";
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
        socketChannel.write(byteBuffer);

        System.in.read();
    }
}
