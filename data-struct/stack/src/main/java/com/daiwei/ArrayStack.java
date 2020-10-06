package com.daiwei;

public class ArrayStack {

    private int top;
    private int size;
    private int[] arr;

    public ArrayStack(int size) {
        this.size = size;
        this.top = -1;
        this.arr = new int[size];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == size;
    }

    public void push(int e) {
        if (isFull()) {
            return;
        }
        top++;
        arr[top] = e;
    }

    public int pop() {
        if (isEmpty()) {
            return -1;
        }
        int temp = arr[top];
        top--;
        return temp;
    }

    public void list() {
        int temp = top;
        while (temp != -1) {
            System.out.println(arr[temp]);
            temp--;
        }
    }

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(5);
        for (int i = 0; i < 5; i++) {
            arrayStack.push(i);
        }
        arrayStack.list();
    }
}
