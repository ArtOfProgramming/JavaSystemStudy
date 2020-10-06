package com.daiwei.recursion;

import java.io.File;

public class RecursionTest {

    public static void main(String[] args) {
        System.out.println(sum(100));
        System.out.println(factorial(3));
        traverseFile(new File("D:\\学习\\笔记"));
    }

    public static int sum(int n) {
        if (n > 0) {
            return sum(n - 1) + n;
        } else {
            return 0;
        }
    }

    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }


    public static void traverseFile(File file) {
        if (file.isFile()) {
            System.out.println(file.getPath());
            return;
        } else {
            for (File listFile : file.listFiles((file1) -> {
                return file1.isDirectory() || file1.getName().toLowerCase().endsWith(".md");
            })) {
                traverseFile(listFile);
            }
        }
    }
}
