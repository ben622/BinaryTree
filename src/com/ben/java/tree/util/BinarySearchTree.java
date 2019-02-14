package com.ben.java.tree.util;

import java.util.List;

/**
 * @author zhangchuan622@gmail.com
 * @version 1.0
 * @create 2019/2/14
 * ����������
 */
public class BinarySearchTree<E> extends BinaryTree<E> {

    /**
     * ��ӽڵ�
     * ���壺����һ���ӽڵ���ֵС�ڸ��ڵ���Ϊ���������Ϊ�ҽ�㣬����һ�ڵ㲻С�����㣬����һ�ڵ㲻�����ҽڵ�.
     * @param v
     */
    public void put(E v) {
        Node<E> parent = null;
        Node<E> node = new Node<E>(v);
        if (root == null) {
            root = node;
            return;
        }
        //�Ӹ��ڵ㿪ʼ
        node = root;

        //ȷ������ӽڵ������е�λ��
        while (node != null) {
            //��¼���ڵ�
            parent = node;
            Integer parentVal = (Integer) node.data;
            if (((Integer) v) < parentVal) {
                //left
                node = node.leftNode;
            } else if (((Integer) v) > parentVal) {
                //right
                node = node.rightNode;
            } else {
                //����ͬ�������ݣ���������.
                return;
            }
        }

        //add newnode to binarysearchtree.
        Integer parentVal = (Integer) parent.data;
        if (((Integer) v) < parentVal) {
            //left
            parent.leftNode = new Node<E>(v);
        } else if (((Integer) v) > parentVal) {
            //right
            parent.rightNode = new Node<E>(v);
        }

    }



}
