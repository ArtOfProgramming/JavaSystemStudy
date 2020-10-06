package com.daiwei.GroupChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 群聊客户端
 */
public class ChatClient {

    // 定义相关属性
    private final String HOST = "127.0.0.1"; // 服务器IP
    private final  int PORT = 6667; // 服务器端口
    private Selector selector;
    private SocketChannel socketChannel;
    private String username;

    // 初始化
    public ChatClient() {
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
            // 设置非阻塞
            socketChannel.configureBlocking(false);
            // 注册
            socketChannel.register(selector, SelectionKey.OP_READ);
            // 得到username
            username = socketChannel.getLocalAddress().toString().substring(1);
            System.out.println(username + " is ok ");

        } catch (Exception e) {

        }
    }

    // 向服务器发送消息
    public void sendInfo(String info) {
        info = username + " 说: " + info;

        try {
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 从服务器读取消息
    public void readInfo() {

        int channels = 0;
        try {
            channels = selector.select(2000);
            if (channels > 0) {

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        socketChannel.read(byteBuffer);
                        System.out.println(new String(byteBuffer.array()));
                    }
                    // 注意一定要清理 不然下次捕获不到变化!!!
                    iterator.remove();
                }
            } else {
//                System.out.println("没有可用通道...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ChatClient chatClient = new ChatClient();

        // 启动一个线程，每3秒读取一次数据
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    chatClient.readInfo();
                }
            }
        }.start();

        // 发数据给服务器端
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            chatClient.sendInfo(s);
        }
    }
}
