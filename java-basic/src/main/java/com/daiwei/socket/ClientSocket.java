package com.daiwei.socket;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientSocket {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 8012);
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();
        FileInputStream fis = new FileInputStream("D:\\学习\\笔记\\网络编程\\2.1-2.png");
        BufferedInputStream bis = new BufferedInputStream(fis);

        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = fis.read(bytes)) != -1) {
            os.write(bytes, 0, len);
        }
        socket.shutdownOutput();

        int len2 = 0;
        byte[] bytes1 = new byte[1024];
        while ((len2 = is.read(bytes1)) != -1) {
            System.out.println(new String(bytes1, 0, len2));
        }

//        fis.close();
        bis.close();
        socket.close();
    }
}
