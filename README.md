# BinaryTree for Java
```
1：在二叉树的第i层上至多有2i-1个结点（i>=1）。
2：深度为k的二叉树至多有2k-1个结点（k>=1）。
3：对任何一颗二叉树T，如果其终端结点数为n0,度为2的	结点	数为n2，则n0 = n2+1.
4：具有n个结点的完全二叉树深度为[log2n]+1 ([x]表示不	大于	x的最大整数)。
5：如果对一颗有n个结点的完全二叉树（其深度为[log2n]+1）	的结点按层序编号（从第1层到第[log2n]+1层，每层从左到	右），对任意一个结点i(1<=i<=n)有：
1）.如果i=1,则结点i是二叉树的根，无双亲；如果i>1,则其双亲是结	点[i/2]
2）.如果2i>n,则结点i无左孩子（结点i为叶子结点）；否则其左孩	子是结点2i。
3）.如果2i+1>n,则结点i无右孩子；否则其右孩子是结点2i+1。
```
## BinaryTree
### 二叉树遍历（迭代方式）
* 前序遍历
* 中序遍历
* 后序遍历
### 求二叉树深度
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
### 求二叉树最大节点数
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
### 前序遍历逆向生成二叉树
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
        //删除空节点
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
对于一个子节点其值小于父节点视为左结点否则视为右结点，任意一节点不小于左结点，任意一节点不大于右节点.
```
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

System.out.println("------------使用前序序列创建二叉树---------------");
BinaryTree<String> createBinaryTree = new BinaryTree<>();
createBinaryTree.createBinaryTreePre("A","B","D","#","#","E","#","#","C","#","F","#","#");
createBinaryTree.preorder();

System.out.println("------------创建二叉搜索树---------------");
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
------------使用前序序列创建二叉树---------------
preorder:A
preorder:B
preorder:D
preorder:E
preorder:C
preorder:F
------------创建二叉搜索树---------------
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