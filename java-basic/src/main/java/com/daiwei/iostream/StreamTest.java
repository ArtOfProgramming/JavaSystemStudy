package com.daiwei.iostream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.openjdk.jol.info.ClassLayout;

public class StreamTest {

    public static void main(String[] args) throws IOException {
//        FileOutputStream fos = new FileOutputStream("D:\\学习\\a.txt");
//        fos.write(97);
//        fos.write("\r\n".getBytes());
//        fos.write(new byte[] {98, 99, 100});
//        fos.close();
//
//        FileInputStream fis = new FileInputStream("D:\\学习\\a.txt");
//
//        int len = 0;
//        while ((len = fis.read()) != -1) {
//            System.out.println(len);
//        }

//        int len = 0;
//        byte[] bytes = new byte[2];
//        while ((len = fis.read(bytes)) != -1) {
//            System.out.println(Arrays.toString(bytes));
//        }
//
//        fis.close();

//        FileWriter fw = new FileWriter("D:\\学习\\a.txt");
//        fw.write(new char[]{'a', 'b', 'c', 'd'});
//        fw.write(new String("代维你好"));
//        fw.close();
//
//        FileReader fr = new FileReader("D:\\学习\\a.txt");
////        int len = 0;
////        while ((len = fr.read()) != -1) {
////            System.out.println((char)len);
////        }
//        int len = 0;
//        char[] chars = new char[1024];
//        while ((len = fr.read(chars)) != -1) {
//            System.out.println(new String(chars, 0, len));
//        }
//        fr.close();

//        // 读GBK文件转换成utf-8文件
////        FileInputStream fis = new FileInputStream("D://学习//a.txt");
////        InputStreamReader inputStreamReader = new InputStreamReader(fis, "GBK");
////        FileOutputStream fos = new FileOutputStream("D://学习//b.txt");
////        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos, "UTF-8");
////        int len = 0;
////        while ((len = inputStreamReader.read()) != -1) {
////            outputStreamWriter.write(len);
////        }
////        outputStreamWriter.close();
////        inputStreamReader.close();

        // 序列化与反序列化

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D://学习//object.txt"));
        oos.writeObject(new Person("代维", 18));
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D://学习//object.txt"));
//        Person person;
//        while (true) {
//            try {
//                if (((person = (Person) ois.readObject()) != null)) {
//                    System.out.println(person.toString());
//                    break;
//                }
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//
//        }
        oos.close();
//        ois.close();
//        Person person = new Person("代维", 18);
//        System.out.println(ClassLayout.parseInstance(person).toPrintable());
    }
}
