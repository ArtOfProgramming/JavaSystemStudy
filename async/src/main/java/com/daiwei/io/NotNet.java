package com.daiwei.io;

import com.daiwei.bean.Person;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NotNet {

    File file;
    @Before
    public void initFile() throws IOException {
        file = new File("D:\\学习\\笔记\\async");
        if(file.exists()) {
            file.delete();
        }
        file.createNewFile();
    }

    @After
    public void close() {
        System.out.println(file.toString());
    }

    @Test
    public void fileIO() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        FileWriter fileWriter = new FileWriter(file);

        fileWriter.write("hello ");
        fileWriter.write("world");
        fileWriter.write("\nhello world");
        // 没有使用BufferedOutputStream,此时数据直接在kernel缓冲区了
        fileWriter.flush();
        // flush之后，就到了硬盘
        fileWriter.close();
        byte[] buffer = new byte[1024];
        int lines = fileInputStream.read(buffer);
        fileInputStream.close();
        System.out.println(new String(buffer));
    }

    @Test
    public void memIO() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        byteArrayOutputStream.write("hello world".getBytes());

        byte[] inData = byteArrayOutputStream.toByteArray();
        ByteArrayInputStream in = new ByteArrayInputStream(inData);

        byte[] data = new byte[1024];
        in.read(data);
        System.out.println(new String(data));
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        in.close();
    }

    @Test
    public void BufferIO() throws IOException {
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
        BufferedReader reader = new BufferedReader(new FileReader(file));

        outputStream.write("hello world".getBytes());
        outputStream.flush();
        outputStream.close();
        String line = reader.readLine();
        System.out.println(line);
    }

    @Test
    public void PrintIO() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        PrintStream printStream = new PrintStream(out);
        printStream.println("hello world");
        printStream.println(false);
        printStream.flush();

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(out.toByteArray()));
        byte[] inData = new byte[1024];
        int read = in.read(inData);
        System.out.println(new String(inData));
        in.close();
        printStream.close();
    }

    @Test
    public void pipedIO() throws IOException {
        PipedOutputStream out = new PipedOutputStream();
        PipedInputStream in = new PipedInputStream(out);

        out.write("hello world".getBytes());
        out.flush();
        byte[] inData = new byte[1024];
        in.read(inData);
        System.out.println(new String(inData));
        in.close();
        out.close();
    }

    @Test
    public void objectIO() throws IOException, ClassNotFoundException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(new Person());
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        Person p = (Person)in.readObject();
        System.out.println(p.toString());
    }

    @Test
    public void dataIO() throws IOException {

    }

    @Test
    public void randomIO() throws IOException, InterruptedException {
        RandomAccessFile rf = new RandomAccessFile(file, "rw");
        rf.write("1hello world\nhello ls\nhello nihao".getBytes());
        FileChannel cl = rf.getChannel();
        // mmap系统内核调用
        // 堆外没有对象，只有Byte[]或者Buffer
        MappedByteBuffer map = cl.map(FileChannel.MapMode.READ_WRITE, 0, 2048); // 直接共享内核空间，写入到磁盘
//        ByteBuffer bf = ByteBuffer.allocate(1024); // 堆上分配 先拷贝到堆外，在复制到内核
//        ByteBuffer bfo = ByteBuffer.allocateDirect(1024); // 对外内存 复制到内核
        map.put("nihao a a a a ".getBytes());
        map.put("hah ".getBytes());
        rf.seek(12);
        rf.write("xxxx".getBytes());
        Thread.sleep(1000);
    }
}
