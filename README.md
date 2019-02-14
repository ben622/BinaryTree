# BinaryTree for Java
```
1���ڶ������ĵ�i����������2i-1����㣨i>=1����
2�����Ϊk�Ķ�����������2k-1����㣨k>=1����
3�����κ�һ�Ŷ�����T��������ն˽����Ϊn0,��Ϊ2��	���	��Ϊn2����n0 = n2+1.
4������n��������ȫ���������Ϊ[log2n]+1 ([x]��ʾ��	����	x���������)��
5�������һ����n��������ȫ�������������Ϊ[log2n]+1��	�Ľ�㰴�����ţ��ӵ�1�㵽��[log2n]+1�㣬ÿ�����	�ң���������һ�����i(1<=i<=n)�У�
1��.���i=1,����i�Ƕ������ĸ�����˫�ף����i>1,����˫���ǽ�	��[i/2]
2��.���2i>n,����i�����ӣ����iΪҶ�ӽ�㣩����������	���ǽ��2i��
3��.���2i+1>n,����i���Һ��ӣ��������Һ����ǽ��2i+1��
```
## BinaryTree
### ������������������ʽ��
* ǰ�����
* �������
* �������
### ����������
```
public int getHeight(Node<E> node) {
    if (node == null) {
        return 0;
    }
    int l = getHeight(node.leftNode);
    int r = getHeight(node.rightNode);
    return l > r ? l + 1 : r + 1;
}
```
### ����������ڵ���
```
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
```
### ǰ������������ɶ�����
```
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
```
## BinarySearchTree
����һ���ӽڵ���ֵС�ڸ��ڵ���Ϊ���������Ϊ�ҽ�㣬����һ�ڵ㲻С�����㣬����һ�ڵ㲻�����ҽڵ�.
```
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

```


## Test Code
```
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

System.out.println("height:"+binaryTree.getHeight());
System.out.println("maxnode:"+binaryTree.getMaxNodeSize());
binaryTree.preorder();
System.out.println("---------------------------");
binaryTree.midorder();
System.out.println("---------------------------");
binaryTree.laterorder();

System.out.println("------------ʹ��ǰ�����д���������---------------");
BinaryTree<String> createBinaryTree = new BinaryTree<>();
createBinaryTree.createBinaryTreePre("A","B","D","#","#","E","#","#","C","#","F","#","#");
createBinaryTree.preorder();

System.out.println("------------��������������---------------");
BinarySearchTree<Integer> searchTree = new BinarySearchTree<>();
int[] arrays = new int[]{20, 34, 24, 25, 45, 65, 1, 90, 200, 100};
for (int item : arrays) {
    searchTree.put(item);
}
searchTree.midorder();
```
## Test Log
```
height:3
maxnode:7
preorder:A
preorder:B
preorder:D
preorder:E
preorder:C
---------------------------
midorder:D
midorder:B
midorder:E
midorder:A
midorder:C
---------------------------
laterorder:D
laterorder:E
laterorder:B
laterorder:C
laterorder:A
------------ʹ��ǰ�����д���������---------------
preorder:A
preorder:B
preorder:D
preorder:E
preorder:C
preorder:F
------------��������������---------------
midorder:1
midorder:20
midorder:24
midorder:25
midorder:34
midorder:45
midorder:65
midorder:90
midorder:100
midorder:200
```