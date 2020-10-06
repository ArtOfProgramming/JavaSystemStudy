package com.daiwei.socket.bean;

import java.io.IOException;
import java.net.Socket;

public class Client {

    private Socket clientSocket;

    public Client() {
    }

    public Client(String host, int port) {
        try {
            this.clientSocket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
}
