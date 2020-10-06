package com.daiwei.GroupChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 群聊服务端程序
 * 1.服务端启动并监听6667端口
 * 2.服务端接收客户端消息，并实现转发（处理上线和离线）
 */
public class ChatServer {

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private static final int PORT = 6667;

    // 初始化
    public ChatServer() {

        try {
            // 开启selecor
            selector = Selector.open();
            // 绑定channel
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
            // 设置非阻塞
            serverSocketChannel.configureBlocking(false);

            // 注册通道到selector
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 监听
    public void listen() {
        try {
            while (true) {
                int count = selector.select();
                // 说明有事件处理
                if (count > 0) {

                    // 遍历得到selectionkey
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        if (selectionKey.isAcceptable()) {
                            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                            // 获取到连接的客户端的socket
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            // 注册
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            System.out.println(socketChannel.getRemoteAddress() + "上线");
                        } else if (selectionKey.isReadable()){ // 通道发送read事件，即通道可读的状态
                            // 处理读消息
                            readData(selectionKey);
                        }

                        // 移除已处理的
                        iterator.remove();
                    }
                } else {
                    System.out.println("等待...");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public void readData(SelectionKey selectionKey) {

        SocketChannel socketChannel = null;
        try {
            socketChannel = (SocketChannel) selectionKey.channel();
            // 创建Buffer
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int count = socketChannel.read(byteBuffer);
            if (count > 0) {

                String msg = new String(byteBuffer.array());
                // 输出消息
                System.out.println("from 客户端" + msg);

                // 向其他客户端转发消息
                sendInfoToOtherClients(msg, socketChannel);
            }
        } catch (Exception e) {
//            e.printStackTrace();
            try {
                System.out.println(socketChannel.getRemoteAddress() + " 离线了... ");
                // 取消注册
                selectionKey.cancel();
                // 关闭通道
                socketChannel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void sendInfoToOtherClients(String msg, SocketChannel self) {
        System.out.println("服务器转发消息");
        // 遍历所有注册到selector上的SocketChannel,排除自己
        for (SelectionKey key : selector.keys()) {
            Channel channel =  key.channel();

            //排除自己
            if (channel instanceof SocketChannel && channel != self) {
                // 将msg存储到buffer
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                // 将消息写入其他channel
                try {
                    ((SocketChannel) channel).write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        ChatServer chatServer = new ChatServer();
        chatServer.listen();
    }
}
