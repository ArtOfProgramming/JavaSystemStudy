package com.daiwei.struct.linkedlist.ring;

public class RingLinkedList {

    private Node head;
    private Node last;
    private int size;

    public void add(int index, String name) {
        if (head == null) {
            head = new Node(index, name, null);
            head.next = head;
            last = head;
            return;
        }
        Node n = new Node(index, name, head);
        last.next = n;
        last = n;
    }

    public void Joseph(int n) {
        int count = 0;
        // Node temp1 = last;
        // 如果没有记last 就需要先遍历找到
        Node temp1 = head;
        while (temp1.next != head) {
            temp1 = temp1.next;
        }
        Node temp2 = head;
        while (true) {
            count++;
            if (count == n) {
                System.out.println(temp2.index);
                if (temp1 == temp2) {
                    break;
                }
                temp1.next = temp2.next;
                temp2 = temp2.next;
                count = 0;
                continue;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
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
        RingLinkedList ringLinkedList = new RingLinkedList();
        for (int i = 0; i < 5; i++) {
            ringLinkedList.add(i, "nihao" + i);
        }
        ringLinkedList.Joseph(2);
    }
}
