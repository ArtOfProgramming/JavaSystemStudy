package com.daiwei.arr;

/**
 * 顺序二叉树
 */
public class ArrBinaryTreeTest {

    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder(0);
    }
}

class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 前序
     * @param index
     */
    public void preOrder(int index) {
        if (arr == null && arr.length == 0){
            System.out.println("数组为空");
            return;
        }
        System.out.println(arr[index]);
        if ((2 * index + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        if ((2 * index + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    /**
     * 中序
     * @param index
     */
    public void infixOrder(int index) {
        if (arr == null && arr.length == 0){
            System.out.println("数组为空");
            return;
        }
        if ((2 * index + 1) < arr.length) {
            infixOrder(2 * index + 1);
        }
        System.out.println(arr[index]);
        if ((2 * index + 2) < arr.length) {
            infixOrder(2 * index + 2);
        }
    }

    /**
     * 后序
     * @param index
     */
    public void postOrder(int index) {
        if (arr == null && arr.length == 0){
            System.out.println("数组为空");
            return;
        }
        if ((2 * index + 1) < arr.length) {
            postOrder(2 * index + 1);
        }
        if ((2 * index + 2) < arr.length) {
            postOrder(2 * index + 2);
        }
        System.out.println(arr[index]);
    }
}