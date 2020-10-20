package com.daiwei.server;

import com.daiwei.mymapping.MyMapping;
import com.daiwei.request.MyRequest;
import com.daiwei.response.MyResponse;
import com.daiwei.servlet.MyServlet;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyServer {

    /**
     * 定义服务端请求
     * @param port
     */
    public static void startServer(int port) {

        ServerSocket serverSocket = null;
        ExecutorService executorService = new ThreadPoolExecutor(20, 50, 60,
            TimeUnit.SECONDS, new LinkedBlockingDeque<>(100));
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.submit(() -> {
                    try {
                        // 获取输入流和输出流
                        InputStream inputStream = socket.getInputStream();
                        OutputStream outputStream = socket.getOutputStream();

                        // 定义请求对象
                        MyRequest request = new MyRequest(inputStream);
                        MyResponse response = new MyResponse(outputStream);

                        String clazz = new MyMapping().getMapping().get(request.getRequestUrl());
                        if (clazz != null) {
                            Class<MyServlet> myServletClass = (Class<MyServlet>) Class.forName(clazz);
                            // 根据myServletClass创建对象
                            MyServlet myServlet =  myServletClass.newInstance();
                            myServlet.service(request, response);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                });
            }
        } catch (Exception e) {

        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        startServer(8077);
    }
}
