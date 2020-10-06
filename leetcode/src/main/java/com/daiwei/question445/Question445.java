package com.daiwei.question445;


import java.util.Deque;
import java.util.LinkedList;

public class Question445 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(3);
        ListNode l2 = new ListNode(9);
        ListNode l3 = new ListNode(9);
        ListNode l4 = new ListNode(9);
        ListNode l5 = new ListNode(9);
        ListNode l6 = new ListNode(9);
        ListNode l7 = new ListNode(9);
        ListNode l8 = new ListNode(9);
        ListNode l9 = new ListNode(9);
        ListNode l10 = new ListNode(9);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        l7.next = l8;
        l8.next = l9;
        l9.next = l10;
        ListNode l20 = new ListNode(7);
        Question445 question445 = new Question445();
        ListNode l11 = question445.addTwoNumbers(l1, l20);
        System.out.println(l11);

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new LinkedList<>();
        Deque<Integer> stack2 = new LinkedList<>();
        // 获取数字
        while(l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }

        while(l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        // 从低位加上去
        ListNode l3 = null;
        int carryBit = 0;
        int num1 = 0;
        int num2 = 0;
        while(!stack1.isEmpty() || !stack2.isEmpty() || carryBit != 0) {
            num1 = stack1.peek() == null ? 0 : stack1.poll();
            num2 = stack2.peek() == null ? 0 : stack2.poll();
            int temp = (num1 + num2 + carryBit) % 10;
            carryBit = (num1 + num2 + carryBit) / 10;
            ListNode tempNode = new ListNode(temp);
            tempNode.next = l3;
            l3 = tempNode;
        }
        return l3;
    }


}

class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
