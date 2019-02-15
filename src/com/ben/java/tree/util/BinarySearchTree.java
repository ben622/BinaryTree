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

    /**
     * ����value����node�ڵ�
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
     * ����ĳ���ڵ�ĸ��ڵ�
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
     * ��ȡvalue��С�Ľڵ�
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
     * ����value���Ľڵ�
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
     * ������ɾ��һ���ڵ�
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
        //���Ӻ��Һ���û�е������ֱ��ɾ��
        if (del.leftNode == null && del.rightNode == null) {
            if (((Integer) del.getData()) > ((Integer) parentNode.getData())) {
                parentNode.rightNode = null;
            } else {
                parentNode.leftNode = null;
            }
            return del;
        }

        //ֻ�������ӵ����
        if (del.leftNode != null && del.rightNode == null) {
            if (((Integer) del.getData()) > ((Integer) parentNode.getData())) {
                parentNode.rightNode = del.leftNode;
            } else {
                parentNode.leftNode = del.leftNode;
            }
            return del;
        }
        //ֻ�����Һ��ӵ����
        if (del.leftNode == null && del.rightNode != null) {
            if (((Integer) del.getData()) > ((Integer) parentNode.getData())) {
                parentNode.rightNode = del.rightNode;
            } else {
                parentNode.leftNode = del.rightNode;
            }
            return del;
        }
        //ͬʱ�������Ӻ��Һ���,���Һ������ֲ�����С�ڵ����
        if (del.leftNode != null && del.rightNode != null) {
            Node<E> replaceParentNode = getMinNode(del.rightNode);
            Node<E> replaceSuperParentNode = findParentNode(replaceParentNode);
            //ɾ������ڵ������ڵ㸸�ڵ�Ĺ�ϵ
            replaceSuperParentNode.leftNode = null;

            //ɾ��
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
