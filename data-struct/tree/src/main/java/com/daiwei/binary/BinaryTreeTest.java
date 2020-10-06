package com.daiwei.binary;

/**
 * 二叉树
 */
public class BinaryTreeTest {

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode heroNode1 = new HeroNode(1, "宋江");
        HeroNode heroNode2 = new HeroNode(2, "吴用");
        HeroNode heroNode3 = new HeroNode(3, "卢俊义");
        HeroNode heroNode4 = new HeroNode(4, "林冲");
        HeroNode heroNode5 = new HeroNode(5, "关胜");

        binaryTree.setRoot(heroNode1);
        heroNode1.setLeft(heroNode2);
        heroNode1.setRight(heroNode3);
        heroNode3.setLeft(heroNode5);
        heroNode3.setRight(heroNode4);
//        binaryTree.preOrder();
//        binaryTree.infixOrder();
//        binaryTree.postOrder();
        System.out.println(binaryTree.preSearch(5));
        System.out.println(binaryTree.infixSearch(5));
        System.out.println(binaryTree.postSearch(5));
    }
}

/**
 * 二叉树
 */
class BinaryTree {
    private HeroNode root;

    public BinaryTree() {
    }

    public BinaryTree(HeroNode root) {
        this.root = root;
    }

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /**
     * 前序排列
     */
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    /**
     * 中序排列
     */
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    /**
     * 后续排列
     */
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    /**
     * 前序查找
     * @param id
     * @return
     */
    public HeroNode preSearch(int id) {
        if (this.root == null) {
            System.out.println("二叉树为空");
            return null;
        }
        return this.root.preSearch(id);
    }

    /**
     * 中序查找
     * @param id
     * @return
     */
    public HeroNode infixSearch(int id) {
        if (this.root == null) {
            System.out.println("二叉树为空");
            return null;
        }
        return this.root.infixSearch(id);
    }

    /**
     * 后序查找
     * @param id
     * @return
     */
    public HeroNode postSearch(int id) {
        if (this.root == null) {
            System.out.println("二叉树为空");
            return null;
        }
        return this.root.postSearch(id);
    }

    /**
     * 删除节点
     */
    public void delete(int id) {
        if (this.root == null) {
            System.out.println("空树");
            return;
        }
        if (this.root.getNo() == id) {
            this.root = null;
            return;
        }
        this.root.delete(id);
    }

    public void add(HeroNode heroNode) {

    }
}

/**
 * 节点
 */
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public HeroNode() {
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
            "no=" + no +
            ", name='" + name + '\'' +
            '}';
    }

    /**
     * 前序排列
     */
    public void preOrder() {
        System.out.println(this); //先输出父节点
        // 向左递归前序
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 中序排列
     */
    public void infixOrder() {
        // 向左中序排列
        if(this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 后续排列
     */
    public void postOrder() {
        if(this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序查找
     * @return
     */
    public HeroNode preSearch(int id) {
        if (this.getNo() == id) {
            return this;
        }
        // 向左递归前序
        HeroNode tempNode = null;
        if (this.left != null) {
            tempNode = this.left.preSearch(id);
        }
        if (tempNode != null) {
            return tempNode;
        }
        if (this.right != null) {
            tempNode = this.right.preSearch(id);
        }
        return tempNode;
    }

    /**
     * 中序排列
     * @return
     */
    public HeroNode infixSearch(int id) {
        // 向左中序排列
        HeroNode tempNode = null;
        if(this.left != null) {
            tempNode = this.left.infixSearch(id);
        }
        if (tempNode != null) {
            return tempNode;
        }
        if (this.getNo() == id) {
            return this;
        }
        if (this.right != null) {
            tempNode = this.right.infixSearch(id);
        }
        return tempNode;
    }

    /**
     * 后续查找
     * @return
     */
    public HeroNode postSearch(int id) {
        HeroNode tempNode = null;
        if(this.left != null) {
            tempNode = this.left.postSearch(id);
        }
        if (tempNode != null) {
            return tempNode;
        }
        if (this.right != null) {
            tempNode = this.right.postSearch(id);
        }
        if (this.getNo() == id) {
            return this;
        }
        return tempNode;
    }

    /**
     * 删除节点
     */
    public void delete(int id) {
        if (this.left != null && this.left.getNo() == id) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.getNo() == id) {
            this.right = null;
            return;
        }
        this.left.delete(id);
        this.right.delete(id);
    }
}
