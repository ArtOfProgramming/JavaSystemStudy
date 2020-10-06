package com.daiwei;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BIOServer {

    public static void main(String[] args) throws IOException {
        // 线程池机制
        // 1.创建线程池
        // 2. 如果有客户端连接，就创建一个线程
        Executor executor = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器启动了");

        while (true) {
            final Socket socket = serverSocket.accept();
            // 连接到一个客户端
            executor.execute(() -> {
                handler(socket);
            });
        }
    }

    public static void handler(Socket socket) {
        byte[] bytes = new byte[1024];
        try {
            InputStream is = socket.getInputStream();
            System.out.println("连接一个客户端");
            while (true) {
                int read = is.read(bytes);
                if (read != -1) {
                    System.out.println(Thread.currentThread().getName() + ":" + new String(bytes, 0, bytes.length));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
