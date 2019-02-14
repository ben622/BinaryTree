package com.ben.java.tree.util;

import java.util.List;

/**
 * @author zhangchuan622@gmail.com
 * @version 1.0
 * @create 2019/2/14
 * 二叉搜索树
 */
public class BinarySearchTree<E> extends BinaryTree<E> {

    /**
     * 添加节点
     * 定义：对于一个子节点其值小于父节点视为左结点否则视为右结点，任意一节点不小于左结点，任意一节点不大于右节点.
     * @param v
     */
    public void put(E v) {
        Node<E> parent = null;
        Node<E> node = new Node<E>(v);
        if (root == null) {
            root = node;
            return;
        }
        //从根节点开始
        node = root;

        //确定新添加节点在树中的位置
        while (node != null) {
            //记录父节点
            parent = node;
            Integer parentVal = (Integer) node.data;
            if (((Integer) v) < parentVal) {
                //left
                node = node.leftNode;
            } else if (((Integer) v) > parentVal) {
                //right
                node = node.rightNode;
            } else {
                //存在同样的数据，不作处理.
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
