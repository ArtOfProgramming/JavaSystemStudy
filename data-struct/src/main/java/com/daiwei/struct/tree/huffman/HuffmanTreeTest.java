package com.daiwei.struct.tree.huffman;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HuffmanTreeTest {

    public static void main(String[] args) {
//        int[] arr = {13, 7, 8, 3, 29, 6, 1};
//        int[] arr = {1, 1, 1, 2, 2, 2, 4, 4, 4, 5, 5, 9};
//        createHuffmanTree(arr);
//        String temp = "i like like like java do you like a java";
//        byte[] bytes = temp.getBytes();
//        HashMap<Byte, String> pathMap = new HashMap<>();
//        byte[] bytes1 = huffmanZip(bytes, pathMap);
//        System.out.println(bytes1.length);
//        System.out.println(Arrays.toString(bytes1));
//        byte[] bytes2 = decode(bytes1, pathMap);
//        System.out.println(bytes.length);
//        System.out.println(new String(bytes2));

//        zipFile("C:\\Users\\daiwei\\Pictures\\QQ浏览器截图\\src.bmp", "C:\\Users\\daiwei\\Pictures\\QQ浏览器截图\\src.zip");
        unZipFile("C:\\Users\\daiwei\\Pictures\\QQ浏览器截图\\src.zip", "C:\\Users\\daiwei\\Pictures\\QQ浏览器截图\\src1.bmp");

//        String pressStr = temp;
//        for (Entry<Byte, String> entry : pathMap.entrySet()) {
//            pressStr = pressStr.replaceAll(entry.getKey().toString(), entry.getValue());
//        }
//        System.out.println(pressStr);
//        System.out.println(pressStr.length());
    }

    /**
     * 压缩文件
     * @param srcFile
     * @param dstFile
     */
    public static void zipFile(String srcFile, String dstFile) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            // 创建输入流
            fis = new FileInputStream(srcFile);
            // 创建一个和源文件一样大小的byte数组
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            HashMap<Byte, String> filePathMap = new HashMap<>();
            byte[] bytes1 = huffmanZip(bytes, filePathMap);
            fos = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(bytes1);
            oos.writeObject(filePathMap);
            oos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void unZipFile(String srcFile, String dstFile) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        FileOutputStream fos = null;
        try {
            // 创建输入流
            fis = new FileInputStream(srcFile);
            ois = new ObjectInputStream(fis);
            // 创建一个和源文件一样大小的byte数组
            byte[] bytes = new byte[fis.available()];
            bytes = (byte[]) ois.readObject();
            HashMap<Byte, String> filePathMap = new HashMap<>();
            filePathMap = (HashMap<Byte, String>)ois.readObject();
            byte[] bytes1 = decode(bytes, filePathMap);
            fos = new FileOutputStream(dstFile);
            fos.write(bytes1);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static byte[] huffmanZip(byte[] bytes, HashMap<Byte, String> pathMap) {
        // 获取各字符权值
        HashMap<Byte, Integer> hashMap = new HashMap<>();
//        for (int i = 0; i < temp.length(); i++) {
//            Byte Byte = temp.charAt(i);
//            if (hashMap.containsKey(Byte)) {
//                hashMap.put(Byte, hashMap.get(Byte) + 1);
//            } else {
//                hashMap.put(Byte, 1);
//            }
//        }
        for (byte aByte : bytes) {
            if (hashMap.containsKey(aByte)) {
                hashMap.put(aByte, hashMap.get(aByte) + 1);
            } else {
                hashMap.put(aByte, 1);
            }
        }
        createHuffmanTree1(hashMap, pathMap);
        System.out.println(pathMap);
        byte[] bytes1 = zip(bytes, pathMap);
        return bytes1;
    }

    /**
     * 解压
     * @param bytes
     * @param pathMap
     * @return
     */
    public static byte[] decode(byte[] bytes, HashMap<Byte, String> pathMap) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            boolean flag = (i != bytes.length - 1);
            stringBuilder.append(byteToBinaryString(flag, bytes[i]));
        }
        HashMap<String, Byte> byteHashMap = new HashMap<>();
        for (Entry<Byte, String> entry : pathMap.entrySet()) {
            byteHashMap.put(entry.getValue(), entry.getKey());
        }
        List<Byte> list = new ArrayList<>();
        for (int i = 0, k = 0; i < stringBuilder.length(); i++) {
            if (byteHashMap.containsKey(stringBuilder.substring(k, i + 1))) {
                list.add(byteHashMap.get(stringBuilder.substring(k, i + 1)));
                k = i + 1;
            }
        }
        byte[] bytes1 = new byte[list.size()];
        int j = 0;
        for (Byte aByte : list) {
            bytes1[j] = aByte;
            j++;
        }
        return bytes1;
    }

    public static String byteToBinaryString(boolean flag, byte b) {
        int temp = b;
        if (flag) {
            temp |= 256; //按位与256
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * 获取路径map
     * @param root
     * @param pathMap
     */
    public static void pathedTree(Node root, HashMap<Byte, String> pathMap) {
        if (root == null) {
            System.out.println("空树");
            return;
        }
        root.pathedTree(pathMap);
    }

    /**
     * 获取路径map
     * @param node
     * @param code
     * @param stringBuilder
     * @param pathMap
     */
    public static void getCodes(Node node, String code, StringBuilder stringBuilder, HashMap<Byte, String> pathMap) {
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        stringBuilder1.append(code);
        if (node != null) {
            if (node.Byte == null) {
                getCodes(node.left, "0", stringBuilder1, pathMap);
                getCodes(node.right, "1", stringBuilder1, pathMap);
            } else {
                pathMap.put(node.Byte, stringBuilder1.toString());
            }
        }
    }

    /**
     * 前序遍历
     * @param root
     */
    public static void preOrder(Node root) {
        if (root == null) {
            System.out.println("空树");
            return;
        }
        root.preOrder();
    }

    /**
     * 构建赫夫曼树并获得路径map
     * @param hashMap
     * @param pathMap
     */
    public static void createHuffmanTree1(HashMap<Byte, Integer> hashMap, HashMap<Byte, String> pathMap) {
        List<Node> nodes = new ArrayList<>();
        for (Entry<Byte,Integer> entry : hashMap.entrySet()) {
            nodes.add(new Node(entry.getValue(), entry.getKey()));
        }
        while (nodes.size() > 1) {
            // 1. 排序
            Collections.sort(nodes);
            // 取最小和次小
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            // 删除处理过的节点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 增加新节点
            nodes.add(parent);
        }
//        pathedTree(nodes.get(0), pathMap);
        getCodes(nodes.get(0), "", new StringBuilder(), pathMap);
    }

    /**
     * 创建赫夫曼树
     * @param arr
     */
    public static void createHuffmanTree(int[] arr) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            nodes.add(new Node(arr[i]));
        }
        while (nodes.size() > 1) {
            // 1.排序
            Collections.sort(nodes);
            // 2.取出最小和次小节点，构成新的节点
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            // 3.移除最小节点和次小节点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 4.新构建的节点加入到合适位置
            nodes.add(parent);
        }
        preOrder(nodes.get(0));
    }

    /**
     * 压缩
     * @param bytes
     * @param pathMap
     * @return
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> pathMap) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte aByte : bytes) {
            stringBuilder.append(pathMap.get(aByte));
        }
        System.out.println(stringBuilder.toString());
        int len = stringBuilder.length() % 8 == 0 ? stringBuilder.length() / 8 : stringBuilder.length() / 8 + 1;
        byte[] bytes1 = new byte[len];
        for (int i = 0, k = 0; i < stringBuilder.length(); i += 8, k++) {
            bytes1[k] = (byte) Integer.parseInt(stringBuilder.substring(i, i + 8 < stringBuilder.length() ? i + 8 : stringBuilder.length()), 2);
        }
        return bytes1;
    }
}

class Node implements Comparable<Node> {
    int value;
    Byte Byte;
    String path = "";
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Byte Byte) {
        this.value = value;
        this.Byte = Byte;
    }

    public Node(int value, Byte Byte, String path) {
        this.value = value;
        this.Byte = Byte;
        this.path = path;
    }

    @Override
    public String toString() {
        return "Node{" +
            "value=" + value +
            ", Byte=" + Byte +
            ", path='" + path + '\'' +
            '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }

    }

    public void pathedTree(HashMap<Byte, String> pathMap) {
        System.out.println(this);
        if (this.left != null) {
            this.left.path = this.path + 0;
            if (this.left.Byte != null) {
                pathMap.put(this.left.Byte, this.left.path);
            }
            this.left.pathedTree(pathMap);
        }
        if (this.right != null) {
            this.right.path = this.path + 1;
            if (this.right.Byte != null) {
                pathMap.put(this.right.Byte, this.right.path);
            }
            this.right.pathedTree(pathMap);
        }
    }

}