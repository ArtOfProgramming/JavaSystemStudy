package com.daiwei.struct.queue.array;

import java.util.Scanner;

public class ArrayQueue {

    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public boolean isFull() {
        return rear == maxSize;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    public boolean add(int i) {
        if (!isFull()) {
            rear++;
            arr[rear] = i;
            return true;
        } else {
            // 通过抛出异常
            throw new RuntimeException("队列满，不能加入数据");
        }
    }

    public int poll() {
        if (!isEmpty()) {
            front++;
            return arr[front];
        } else {
            // 通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
    }

    public int peek() {
        if (!isEmpty()) {
            return arr[front + 1];
        } else {
            // 通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
    }

    // 显示队列的所有数据
    public void showQueue() {
        // 遍历
        if (isEmpty()) {
            System.out.println("队列空的，没有数据~~");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }


    public static void main(String[] args) {
//        ArrayQueue arrayQueue = new ArrayQueue(10);
//        arrayQueue.add(10);
//        arrayQueue.add(9);
//        int temp;
//        while (true) {
//            temp = arrayQueue.poll();
//            if (temp == -1) {
//                break;
//            } else {
//                System.out.println(temp);
//            }
//        }
        //测试一把
        System.out.println("测试数组模拟环形队列的案例~~~");

        // 创建一个环形队列
        ArrayQueue queue = new ArrayQueue(4); //说明设置4, 其队列的有效数据最大是3
        char key = ' '; // 接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        // 输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);// 接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.add(value);
                    break;
                case 'g': // 取出数据
                    try {
                        int res = queue.poll();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': // 查看队列头的数据
                    try {
                        int res = queue.peek();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': // 退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }
}
