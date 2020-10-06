package com.daiwei.socket.bean;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    private ServerSocket serverSocket;

    public Server() {

    }

    public Server(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("服务器开启完毕");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
}
