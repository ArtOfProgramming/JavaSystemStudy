package com.daiwei;

import com.daiwei.bean.Client;
import com.daiwei.bean.Server;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SocketTest {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Boolean startedFlag = true;
        synchronized (startedFlag) {
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    Server server = new Server(8011);
                    try {
                        Socket socket  = server.getServerSocket().accept();
                        InputStream is = socket.getInputStream();
                        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("D:\\学习\\笔记\\网络编程\\2.1-1-upload.png"));
                        byte[] bytes = new byte[1024];
                        while (true) {
                            int len = is.read(bytes);
                            if (len == 1) {
                                bos.flush();
                            } else {
                                bos.write(bytes, 0, len);
                            }
                        }
//                        System.out.println(new String(bytes, 0, len));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        try {
            Thread.sleep(2000);

            System.out.println(startedFlag);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            synchronized (startedFlag) {
                if (startedFlag) {
                    pool.submit(new Runnable() {
                        @Override
                        public void run() {
                            Client client = new Client("127.0.0.1", 8011);
                            try {
                                OutputStream os =  client.getClientSocket().getOutputStream();
                                System.out.println("连接上了服务器");
//                                try {
//                                    Thread.sleep(10000);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
                                BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:\\学习\\笔记\\网络编程\\2.1-1.png"));
                                int len = 0;
                                byte[] bytes = new byte[1024];
                                while ((len = bis.read(bytes)) != -1) {
                                    os.write(bytes, 0, len);
                                }
                                os.write(-1);
                                System.out.println("文件上传完毕");
//                                os.write("代维你好".getBytes());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                    break;
                } else {
                    System.out.println("服务器还未启动，请等待");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
