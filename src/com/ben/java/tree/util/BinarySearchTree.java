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
     *
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

    /**
     * 根据value查找node节点
     *
     * @param v
     * @return
     */
    public Node<E> find(int v) {
        if (root == null) {
            return null;
        }
        Node<E> node = root;
        while (node != null) {
            if (v > (Integer) node.data) {
                node = node.rightNode;
            } else if (v < (Integer) node.data) {
                node = node.leftNode;
            } else {
                return node;
            }
        }
        return node;
    }

    /**
     * 查找某个节点的父节点
     *
     * @param child
     * @return
     */
    public Node<E> findParentNode(Node<E> child) {
        if (root == null) {
            return null;
        }
        if (child == root) {
            return child;
        }
        int v = (Integer) child.getData();
        Node<E> node = root;
        Node<E> parent = null;
        while (node != null) {
            if (v > (Integer) node.data) {
                parent = node;
                node = node.rightNode;
            } else if (v < (Integer) node.data) {
                parent = node;
                node = node.leftNode;
            } else {
                return parent;
            }

        }
        return parent;
    }

    /**
     * 获取value最小的节点
     *
     * @return
     */
    public Node<E> getMinNode() {
        return getMinNode(root);
    }

    private Node<E> getMinNode(Node<E> node) {
        if (node == null) {
            return null;
        }
        if (node.leftNode == null) {
            return node;
        } else {
            return getMinNode(node.leftNode);
        }
    }

    public Node<E> getMaxNode() {
        return getMaxNode(root);
    }

    /**
     * 查找value最大的节点
     *
     * @param node
     * @return
     */
    private Node<E> getMaxNode(Node<E> node) {
        if (node == null) {
            return null;
        }
        if (node.rightNode == null) {
            return node;
        } else {
            return getMaxNode(node.rightNode);
        }
    }

    /**
     * 从树中删除一个节点
     *
     * @param v
     * @return
     */
    public Node<E> deleteNode(int v) {
        return deleteNode(find(v));
    }

    public Node<E> deleteNode(Node<E> del) {
        if (del == null) {
            return null;
        }
        if (del == root) {
            root = null;
            return del;
        }
        Node<E> parentNode = findParentNode(del);
        //左孩子和右孩子没有的情况，直接删除
        if (del.leftNode == null && del.rightNode == null) {
            if (((Integer) del.getData()) > ((Integer) parentNode.getData())) {
                parentNode.rightNode = null;
            } else {
                parentNode.leftNode = null;
            }
            return del;
        }

        //只存在左孩子的情况
        if (del.leftNode != null && del.rightNode == null) {
            if (((Integer) del.getData()) > ((Integer) parentNode.getData())) {
                parentNode.rightNode = del.leftNode;
            } else {
                parentNode.leftNode = del.leftNode;
            }
            return del;
        }
        //只存在右孩子的情况
        if (del.leftNode == null && del.rightNode != null) {
            if (((Integer) del.getData()) > ((Integer) parentNode.getData())) {
                parentNode.rightNode = del.rightNode;
            } else {
                parentNode.leftNode = del.rightNode;
            }
            return del;
        }
        //同时存在左孩子和右孩子,从右孩子树种查找最小节点替代
        if (del.leftNode != null && del.rightNode != null) {
            Node<E> replaceParentNode = getMinNode(del.rightNode);
            Node<E> replaceSuperParentNode = findParentNode(replaceParentNode);
            //删除替代节点和替代节点父节点的关系
            replaceSuperParentNode.leftNode = null;

            //删除
            if (((Integer) del.getData()) > ((Integer) parentNode.getData())) {
                parentNode.rightNode = replaceParentNode;
            } else {
                parentNode.leftNode = replaceParentNode;
            }
            replaceParentNode.leftNode = del.leftNode;
            replaceParentNode.rightNode = del.rightNode;
            return del;
        }
        return null;
    }


}
