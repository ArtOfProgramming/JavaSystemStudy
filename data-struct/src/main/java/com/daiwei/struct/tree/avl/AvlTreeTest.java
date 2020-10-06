package com.daiwei.struct.tree.avl;

public class AvlTreeTest {

    public static void main(String[] args) {
        int[] arr = new int[] {10, 11, 7, 6, 8, 9};
        AvlTree avlTree = new AvlTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        avlTree.infixOrder();
        System.out.println(avlTree.calHeightDifference());
    }
}

class AvlTree {

    Node root;
    int leftHeight;
    int rightHeight;

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
            if (calHeightDifference() < -1) {
                if (root.right.left.height() > root.right.right.height()) {
                    rightRotate(root.right);
                }
                leftRotate(root);
            } else if (calHeightDifference() > 1){
                if (root.left.right.height() > root.left.left.height()) {
                    leftRotate(root.left);
                }
                rightRotate(root);
            }
        }
    }

    public int calHeightDifference() {
        return root.leftChildHeight() - root.rightChildHeight();
    }

    /**
     * 左旋
     */
    public void leftRotate(Node node) {
        Node newRootLeft = new Node(node.value);
        newRootLeft.left = node.left;
        newRootLeft.right = node.right.left;

        node.value = node.right.value;
        node.left = newRootLeft;
        node.right = node.right.right;

    }

    /**
     * 右旋
     */
    public void rightRotate(Node node) {
        Node newRootRight = new Node(node.value);
        newRootRight.right = node.right;
        newRootRight.left = node.left.right;

        node.value = node.left.value;
        node.right = newRootRight;
        node.left = node.left.left;
    }

    public void infixOrder() {
        if (root == null) {
            System.out.println("空树");
            return;
        }
        root.infixOrder();
    }

    public void delete(Node node) {
        if (root == null) {
            System.out.println("空树");
            return;
        }
        // 1.寻找到当前节点
        Node pre = null;
        Node cur = root;
        while (true) {
            if (cur == null) {
                return;
            }
            if (node.value < cur.value) {
                pre = cur;
                cur = cur.left;
            } else if (node.value > cur.value) {
                pre = cur;
                cur = cur.right;
            } else {
                break;
            }
        }
        if (cur == root && cur.left == null && cur.right == null) {
            root = null;
            return;
        }
        // 第一种情况,待删除节点是叶子节点
        if (cur.left == null && cur.right == null) {
            if (pre != null) {
                if (cur.value < pre.value) {
                    pre.left = null;
                } else {
                    pre.right = null;
                }
            }
            // 第二种情况，待删除节点有一个子节点
        } else if (cur.left == null || cur.right == null) {
            if (cur.left != null) {
                if (pre != null) {
                    if (cur.value == pre.left.value) {
                        pre.left = cur.left;
                    } else {
                        pre.right = cur.left;
                    }
                } else {
                    root = cur.left;
                }
            } else {
                if (pre != null) {
                    if (cur.value == pre.left.value) {
                        pre.left = cur.right;
                    } else {
                        pre.right = cur.right;
                    }
                } else {
                    root = cur.right;
                }
            }
            // 第三种情况，待删除节点有两个节点
        } else {
            Node temp = cur.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            cur.value = temp.value;
            this.delete(temp);
        }
    }
}

class Node {

    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
            "value=" + value +
            ", height=" + this.height() +
            '}';
    }

    public int leftChildHeight() {
        if (this.left == null) {
            return 0;
        }
        return this.left.height();
    }

    public int rightChildHeight() {
        if (this.right == null) {
            return 0;
        }
        return this.right.height();
    }

    public int height() {
        return Math.max(this.left == null ? 0 : this.left.height(), this.right == null ? 0 : this.right.height()) + 1;
    }

    /**
     * 添加node
     *
     * @param node
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {
            // 挂到左节点
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            // 挂到右节点
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}
