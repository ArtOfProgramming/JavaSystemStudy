package com.daiwei.file;

import java.io.File;

public class FileTest {

    public static void main(String[] args) {
        File file = new File("D:\\学习");
        File[] files = file.listFiles();
        for (File file1 : files) {
            System.out.println(file1.getPath());
        }
    }
}
