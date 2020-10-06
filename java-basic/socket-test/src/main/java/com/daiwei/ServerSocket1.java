package com.daiwei;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocket1 {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8012);
        while (true) {

            Socket socket = serverSocket.accept();
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            FileOutputStream fos = new FileOutputStream("D:\\学习\\笔记\\网络编程\\2.1.2-upload.png");
            BufferedOutputStream bof = new BufferedOutputStream(fos);

            int len = 0;
            byte[] bytes = new byte[1024];
            long startTime = System.currentTimeMillis();
            while ((len = is.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
            long endTime = System.currentTimeMillis();
            System.out.println((endTime - startTime));
            os.write("上传成功".getBytes());
            socket.shutdownOutput();

//            fos.close();
            bof.close();
            socket.close();
        }

//        serverSocket.close();
    }
}
