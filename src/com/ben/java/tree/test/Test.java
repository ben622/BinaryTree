package com.ben.java.tree.test;

import com.ben.java.tree.util.BinarySearchTree;
import com.ben.java.tree.util.BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String args[]) {
        BinaryTree.Node<String> root = new BinaryTree.Node<>("A");

        BinaryTree.Node<String> r1 = new BinaryTree.Node<>("B");
        BinaryTree.Node<String> r2 = new BinaryTree.Node<>("C");

        BinaryTree.Node<String> r3 = new BinaryTree.Node<>("D");
        BinaryTree.Node<String> r4 = new BinaryTree.Node<>("E");

        root.setLeftNode(r1);
        root.setRightNode(r2);

        r1.setLeftNode(r3);
        r1.setRightNode(r4);

        BinaryTree<String> binaryTree = new BinaryTree<>(root);

        System.out.println("height:" + binaryTree.getHeight());
        System.out.println("maxnode:" + binaryTree.getMaxNodeSize());
        binaryTree.preorder();
        System.out.println("---------------------------");
        binaryTree.midorder();
        System.out.println("---------------------------");
        binaryTree.laterorder();

        System.out.println("------------ʹ��ǰ�����д���������---------------");
        BinaryTree<String> createBinaryTree = new BinaryTree<>();
        createBinaryTree.createBinaryTreePre("A", "B", "D", "#", "#", "E", "#", "#", "C", "#", "F", "#", "#");
        createBinaryTree.preorder();

        System.out.println("------------��������������---------------");
        BinarySearchTree<Integer> searchTree = new BinarySearchTree<>();
        int[] arrays = new int[]{20, 34, 24, 25, 45, 65, 1, 90, 200, 100};
        for (int item : arrays) {
            searchTree.put(item);
        }
        searchTree.midorder();

        System.out.println("------------�ڶ����������в���---------------");
        int findValue = 34;
        System.out.println("find value " + findValue + ":" + (searchTree.find(findValue) != null));

        System.out.println("-----------�����л�ȡ��Сֵ�����ֵ---------------");
        System.out.println("��Сֵ��" + searchTree.getMinNode().getData() + "��Сֵ��" + searchTree.getMaxNode().getData());

        System.out.println("-----------ɾ���ڵ�---------------");
        System.out.println("ɾ���ڵ㣺" + (searchTree.deleteNode(34) != null));
        searchTree.midorder();
    }
}
