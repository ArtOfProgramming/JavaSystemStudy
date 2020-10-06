package com.daiwei;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTest {

    public static void main(String[] args) throws IOException {
//        show01();
        show02();
    }

    private static void show02() throws IOException {
        FileWriter fw = new FileWriter("D:\\学习\\a.txt");
        FileReader fr = new FileReader("D:\\学习\\a.txt");
        Properties properties = new Properties();
        properties.setProperty("代维", "180");
        properties.setProperty("王先生", "165");
        properties.setProperty("李先生", "175");
        properties.store(fw, "save data");
        Properties properties1 = new Properties();
        properties1.load(fr);
        for (String s : properties1.stringPropertyNames()) {
            System.out.println(s + " :" + properties1.getProperty(s));
        }

    }

    private static void show01() {
        Properties properties = new Properties();
        properties.setProperty("代维", "180");
        properties.setProperty("王先生", "165");
        properties.setProperty("李先生", "175");
        for (String s : properties.stringPropertyNames()) {
            System.out.println(s + ":" + properties.getProperty(s));
        }


    }
}
