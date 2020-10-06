package com.daiwei;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServerSocket {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8081);
        while (true) {
            Socket socket = serverSocket.accept();
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            BufferedReader bs = new BufferedReader(new InputStreamReader(is));

//        int len = 0;
//        byte[] bytes = new byte[1024];
//        while ((len = is.read(bytes)) != -1) {
//            System.out.println(new String(bytes, 0, len));
//        }
            os.write("HTTP/1.1 200 OK\r\n".getBytes());
            os.write("Content-Type:text/html\r\n".getBytes());
            os.write("\r\n".getBytes());
            String path = bs.readLine().split(" ")[1];
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + path);
            int len1 = 0;
            byte[] bytes1 = new byte[1024];
            while ((len1 = fis.read(bytes1)) != -1) {
                os.write(bytes1);
            }
            fis.close();
            socket.close();
        }

    }
}
