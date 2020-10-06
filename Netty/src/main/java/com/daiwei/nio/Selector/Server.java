package com.daiwei.nio.Selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Selector测试
 */
public class Server {

    public static void main(String[] args) throws IOException {
        // 创建服务器网路通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7001);

        // 绑定socket
        serverSocketChannel.socket().bind(inetSocketAddress);
        serverSocketChannel.configureBlocking(false);

        // 连接上将socketChannel注册到Selector
        Selector selector =  Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            if (0 == selector.select(1000)) {
                System.out.println("服务器等待1s，无连接");
                continue;
            }
            System.out.println("总共通道数：" + selector.keys().size());
            // 如果返回大于0，则获取相关key
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                // 获取到SelectionKey
                SelectionKey selectionKey = iterator.next();
                // 如果发生事件时客户端连接
                if (selectionKey.isAcceptable()) {
                    // 给该客户端生成一个SocketChannel
                    SocketChannel socketChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
                    // 将SocketChannel注册到selector中
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    // 发生read事件
                } else if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    // 获取该channel的buffer
                    ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
                    socketChannel.read(byteBuffer);
                    System.out.println("from 客户端" + new String(byteBuffer.array()));
                    byteBuffer.clear();
                }
                // 手动删除掉key
                iterator.remove();
            }
        }
    }
}