package com.ben.java.tree.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangchuan622@gmail.com
 * @version 1.0
 * @create 2019/2/13
 */
public class BinaryTree<E> {

    //���ڵ�
    protected Node<E> root;

    public BinaryTree() {

    }

    public BinaryTree(Node<E> root) {
        this.root = root;
    }

    /**
     * ǰ�����д���������
     * A
     * B           C
     * D        E    ?         F
     * #       #   #   #   #    #      #
     * ���У�ABDECF
     * �������У�ABD##E##C#F##
     *
     * @param nodes
     * @return
     */
    public void createBinaryTreePre(E... nodes) {
        if (nodes == null) {
            return;
        }
        createBinaryTreePre(constructorList(nodes));
    }

    public void createBinaryTreePre(List<E> nodes) {
        if (nodes == null) {
            return;
        }
        createBinaryTreePre(0, nodes);
    }

    private Node<E> createBinaryTreePre(int index, List<E> nodes) {
        E v = nodes.get(0);
        if ("#".equals(v) || "null".equals(v) || v == null) {
            //ɾ���սڵ�
            nodes.remove(0);
            return null;
        }
        Node<E> node = new Node<E>(index, v);
        //remove
        nodes.remove(0);
        //create root node.
        if (root == null || index == 0) {
            root = node;
        }
        //set leftchild and rightchild.
        node.leftNode = createBinaryTreePre(++index, nodes);
        node.rightNode = createBinaryTreePre(++index, nodes);
        return node;
    }

    /**
     * ��ȡ���ĸ߶�
     *
     * @return
     */
    public int getHeight() {
        return getHeight(root);
    }

    /**
     * ��ȡָ�����ĸ߶�
     *
     * @param node
     * @return
     */
    public int getHeight(Node<E> node) {
        if (node == null) {
            return 0;
        }
        int l = getHeight(node.leftNode);
        int r = getHeight(node.rightNode);
        return l > r ? l + 1 : r + 1;
    }

    /**
     * ��ȡ��ǰ��������node�����
     *
     * @return
     */
    public int getMaxNodeSize() {
        return getMaxNodeSize(root);
    }

    /**
     * ��ȡ��ǰ�����������<br>
     * ���壺���Ϊk�Ķ�����������2��k����-1����㣨k>=1��
     *
     * @param node
     * @return
     */
    public int getMaxNodeSize(Node<E> node) {
        if (node == null) {
            return 0;
        }
        int k = getHeight(node);
        if (k == 0) {
            return 0;
        }
        return (int) Math.pow(2, k) - 1;
    }

    public void preorder() {
        preorder(root);
    }

    /**
     * ����ǰ�����
     *
     * @param node
     */
    public void preorder(Node<E> node) {
        if (node == null) {
            return;
        }
        System.out.println("preorder:" + node.data);
        preorder(node.leftNode);
        preorder(node.rightNode);

    }

    public void midorder() {
        midorder(root);
    }

    /**
     * �����������
     *
     * @param node
     */
    public void midorder(Node<E> node) {
        if (node == null) {
            return;
        }
        midorder(node.leftNode);
        System.out.println("midorder:" + node.data);
        midorder(node.rightNode);
    }

    public void laterorder() {
        laterorder(root);

    }

    /**
     * �����������
     *
     * @param node
     */
    public void laterorder(Node<E> node) {
        if (node == null) {
            return;
        }
        laterorder(node.leftNode);
        laterorder(node.rightNode);
        System.out.println("laterorder:" + node.data);
    }

    public List<E>  constructorList(E ... o){
        List<E> lists = new ArrayList<>();
        for (int i = 0; i < o.length; i++) {
            lists.add(o[i]);
        }
        return lists;
    }


    /**
     * �������ڵ�
     */
    public static class Node<E> {
        int index;
        E data;
        Node<E> leftNode;
        Node<E> rightNode;

        public Node(int index, E data) {
            this.index = index;
            this.data = data;
        }

        public Node(E data) {
            this.data = data;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node<E> getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(Node<E> leftNode) {
            this.leftNode = leftNode;
        }

        public Node<E> getRightNode() {
            return rightNode;
        }

        public void setRightNode(Node<E> rightNode) {
            this.rightNode = rightNode;
        }
    }

}
