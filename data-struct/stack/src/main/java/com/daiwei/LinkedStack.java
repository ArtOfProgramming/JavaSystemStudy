package com.daiwei;

public class LinkedStack {

    private Node head;
    private Node last;
    private int size;

    public void push(int index, String name) {
        if (head == null) {
            head = new Node(index, name, null);
            last = head;
            return;
        }
        Node n = new Node(index, name, null);
        last.next = n;
        last = n;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Node pop() {
        Node help = head;
        while (help != null) {
            if (help.next == last) {
                Node temp = last;
                help.next = null;
                last = help;
                return temp;
            }
            help = help.next;
        }
        return null;
    }

    public void list() {
        Node help = head;
        Node cur = last;
        while (cur != head) {
            if (help.next == cur) {
                System.out.println(cur.name);
                cur = help;
                help = head;
                continue;
            }
            help = help.next;
        }
        System.out.println(cur.name);
    }


    private class Node {
        private int index;
        private String name;
        private Node next;

        public Node(int index, String name, Node next) {
            this.index = index;
            this.name = name;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                "index=" + index +
                ", name='" + name + '\'' +
                '}';
        }
    }

    public static void main(String[] args) {
        LinkedStack linkedStack = new LinkedStack();
        for (int i = 0; i < 5; i++) {
            linkedStack.push(i, "nihao" + i);
        }
        linkedStack.list();
    }
}
