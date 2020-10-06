package com.daiwei.struct.linkedlist.double_;

public class DoubleLinkedList {

    private Node head;
    private Node last;
    private int size;

    public void add(int index, String name) {
        if (head == null) {
            head = new Node(index, name, null, null);
            last = head;
            return;
        }
        Node n = new Node(index, name, last, null);
        last.next = n;
        last = last.next;
    }

    public void list() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }

    public void reserveList() {
        Node temp = last;
        while (temp != null) {
            System.out.println(temp.toString());
            temp = temp.pre;
        }
    }

    /**
     * 删除时，注意删除是第一个或最后一个节点时，pre或next为空问题
     * @param index
     */
    public void delete(int index) {
        Node temp = head;
        while (temp != null) {
            if (temp.index == index) {
                if (temp.pre == null) {
                    head = temp.next;
                    head.pre = null;
                } else if (temp.next == null) {
                    last = last.pre;
                    last.next = null;
                } else {
                    temp.pre.next = temp.next;
                    temp.next.pre = temp.pre;
                }
            }
            temp = temp.next;
        }
    }


    private class Node {

        int index;
        String name;
        Node pre;
        Node next;

        public Node(int index, String name, Node pre, Node next) {
            this.index = index;
            this.name = name;
            this.pre = pre;
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
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        for (int i = 0; i < 5; i++) {
            doubleLinkedList.add(i, "nihao" + i);
        }
        doubleLinkedList.delete(4);
        doubleLinkedList.list();
        doubleLinkedList.reserveList();
    }
}
