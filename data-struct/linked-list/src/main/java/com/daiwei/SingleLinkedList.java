package com.daiwei;

import com.sun.corba.se.impl.orbutil.StackImpl;

public class SingleLinkedList {

    private Node head;
    private Node last;
    private int size;

    public SingleLinkedList() {
    }

    public void add(int index) {
        if (head == null) {
            head = new Node(index,null);
            last = head;
            return;
        }
        Node n = new Node(index, null);
        last.next = n;
        last = n;
    }

    public void addByOrder(int index) {
        if (head == null) {
            head = new Node(index,null);
            last = head;
            return;
        }
        Node temp = head;
        if (temp.index > index) {
            Node n = new Node(index, head);
            head = n;
            return;
        }
        while (true) {
            if (temp.next == null || temp.next.index > index) {
                break;
            }
            if (temp.next.index == index) {
                System.out.println("存在重复，无法添加");
                return;
            }
            temp = temp.next;
        }
        Node n = new Node(index, temp.next);
        temp.next = n;
    }

    public void delete() {

    }

    public void update(int index) {

    }

    public int query(int index) {
        return index;
    }

    public void list() {
        if (head == null) {
            throw new RuntimeException("链表为空");
        }
        Node n = head;
        while (true) {
            System.out.println(n.toString());
            n = n.next;
            if (n == null) {
                break;
            }
        }
    }

    /**
     * 获取倒数第几个元素
     * @param index
     * @return
     */
    public Node getBottom(int index) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }

        Node temp = head;
        int len = 1;
        while (temp.next != null) {
            len++;
            temp = temp.next;
        }
        int i = len - index;
        if (i < 0) {
            System.out.println("链表长度不够");
            return null;
        }
        temp = head;
        for (int i1 = 0; i1 < i; i1++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 单向链表反转
     */
    public void reserve() {
        Node newHead = null;
        while (head != null) {
            Node temp = head;
            head = head.next;
            temp.next = newHead;
            newHead = temp;
        }
        head = newHead;
    }

    /**
     * 从尾部到头打印链表
     */
    public void reserveList() {
        StackImpl stack = new StackImpl();
        Node temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }

    /**
     *
     * @param s1
     * @param s2
     * @return
     */
    public static SingleLinkedList concat(SingleLinkedList s1, SingleLinkedList s2) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        if (s1.head == null && s2.head == null) {
            return null;
        } else if (s1.head == null) {
            return s2;
        } else if (s2.head == null) {
            return s1;
        } else {
            Node head1 = s1.head;
            Node head2 = s2.head;
            while (head1 != null || head2 != null) {
                if (head1 == null) {
                    singleLinkedList.add(head2.index);
                    head2 = head2.next;
                    continue;
                } else if (head2 == null) {
                    singleLinkedList.add(head1.index);
                    head1 = head1.next;
                    continue;
                }
                if (head1.index < head2.index) {
                    singleLinkedList.add(head1.index);
                    head1 = head1.next;
                } else {
                    singleLinkedList.add(head2.index);
                    head2 = head2.next;
                }
            }
        }
        return singleLinkedList;
    }

    private class Node {
        private int index;
        private Node next;

        public Node(int index, Node next) {
            this.index = index;
            this.next = next;
        }

        public boolean hasNext(Node n) {
            return n.next != null;
        }

        public Node next(Node n) {
            if (n == null) {
                return null;
            }
            return n.next;
        }

        @Override
        public String toString() {
            return "Node{" +
                "index=" + index +
                '}';
        }
    }

    public static void main(String[] args) {
//        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.addByOrder(10);
//        singleLinkedList.addByOrder(8);
//        singleLinkedList.addByOrder(9);
//        singleLinkedList.addByOrder(7);
//        singleLinkedList.addByOrder(15);
//        singleLinkedList.addByOrder(14);
//        singleLinkedList.list();
////        System.out.println(singleLinkedList.getBottom(3).toString());
////        singleLinkedList.reserve();
////        singleLinkedList.list();
//        singleLinkedList.reserveList();
        SingleLinkedList s1 = new SingleLinkedList();
        SingleLinkedList s2 = new SingleLinkedList();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                s1.add(i);
            } else {
                s2.add(i);
            }
        }
        SingleLinkedList s3 = SingleLinkedList.concat(s1, s2);
        s3.list();
    }
}
