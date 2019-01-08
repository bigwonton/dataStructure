package BinarySearchTree;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {

    public Node root; // 根节点
    private int size; // 树的大小


    public BST() {
        root = null;
        size = 0;
    }

    /**
     * 获取树的大小
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 返回树是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向树中添加元素
     * @param e
     */
    public void add(E e) {
        if (isEmpty()) {
            root = new Node(e);
            size ++;
            return;
        }

//        add(root, e);
        root = add(root, e);
    }

    // 每次递归，将处理完成后的子树的根结点返回
    private Node add(Node node, E e) {
        // 终止条件 插入新节点
        if (node == null) {
            size ++;
            return new Node(e);
        }

        // 递归
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right =add(node.right, e);
        }

        return node;
    }

    /**
     * 返回树中是否包含该元素
     * @param e
     * @return
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            return contains(node.right, e);
        } else {
            return true;
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    /**
     * 前序遍历（非递归）
     * 借助 stack
     */
    public void preOrderNR() {
        Stack<Node> stack = new Stack<>();

        if (root != null) {
            stack.push(root);
        }

        while (!stack.isEmpty()) {
            Node popNode = stack.pop();
            System.out.println(popNode.e);
            if (popNode.right != null) {
                stack.push(popNode.right);
            }
            if (popNode.left != null) {
                stack.push(popNode.left);
            }
        }
    }

    /**
     * 层序遍历（通常采用非递归）
     * 借助 queue
     */
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();

        if (root != null) {
            queue.add(root);
        }

        while (!queue.isEmpty()) {
            Node delNode = queue.remove();
            System.out.println(delNode.e);
            if (delNode.left != null) {
                queue.add(delNode.left);
            }
            if (delNode.right != null) {
                queue.add(delNode.right);
            }
        }
    }

    /**
     * 删除最小元素
     * @return
     */
    public E removeMin() {
        root = removeMin(root);
        return getMin();
    }

    // 递归
    private Node removeMin(Node node) {
        // 终止条件 没有左子树
        // 将右子树返回，作为父节点的左子树
        if (node.left == null) {
            Node rightNode = node.right;
            size --;
            node.right = null;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 删除最大元素
     * @return
     */
    public E removeMax() {
        root = removeMax(root);
        return getMax();
    }

    // 递归
    private Node removeMax(Node node) {
        // 终止条件 没有右子树
        // 将左子树返回，作为父节点的右子树
        if (node.right == null) {
            Node leftNode = node.left;
            size --;
            node.left = null;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 删除指定元素
     * @param e
     * @return
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.e) < 0) {
            return remove(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            return remove(node.right, e);
        }

        // 找到需要删除的元素
        // case 1 左子树为空
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }
        // case 2 右子树为空
        else if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }
        // case 3 左右子树均不为空
        else {
            // 找到右子树中最小的元素，替代它
            Node rightNode = node.right; // 右子树
            Node rightMinNode = getMin(rightNode); // 右子树最小节点
            Node rightNewRoot = removeMin(rightNode); // 右子树删除最小节点后的根节点
            rightMinNode.right = rightNewRoot;
            rightMinNode.left = node.left;

            return rightMinNode;
        }
    }

    /**
     * 获取最小元素
     * @return
     */
    public E getMin() {
        if (isEmpty()) {
            return null;
        }
        return getMin(root).e;
    }

    // 递归
    private Node getMin(Node node) {
        // 终止条件 没有左子树
        if (node.left == null) {
            return node;
        }

         return getMin(node.left);
    }

    /**
     * 获取最大元素
     * @return
     */
    public E getMax() {
        if (isEmpty()) {
            return null;
        }
        return getMax(root).e;
    }

    // 递归
    private Node getMax(Node node) {
        // 终止条件 没有右子树
        if (node.right == null) {
            return node;
        }

        return getMax(node.right);
    }




//    // 内部使用递归添加元素
//    private void add(Node node, E e) {
//        // 终止条件
//        if (node.e.equals(e)) {
//            return;
//        } else if (e.compareTo(node.e) < 0 && node.left == null) {
//            node.left = new Node(e);
//            size ++;
//            return;
//        } else if (e.compareTo(node.e) > 0 && node.right == null){
//            node.right = new Node(e);
//            size ++;
//            return;
//        }
//
//        // 递归
//        if (e.compareTo(node.e) < 0) {
//            add(node.left, e);
//        } else {
//            add(node.right, e);
//        }
//    }





    /**
     * 内部类
     */
    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }


    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 8, 7, 4, 1};
        for (int i = 0; i < nums.length; i ++) {
            bst.add(nums[i]);
        }

        System.out.println("--- test transverse ---");

        bst.preOrder();
        System.out.println();
//        bst.inOrder();
//        System.out.println();
//        bst.postOrder();
//        bst.preOrderNR();
        bst.levelOrder();

        System.out.println("--- test getMin() ---");
        System.out.println(bst.getMin());

        System.out.println("--- test getMax() ---");
        System.out.println(bst.getMax());

        System.out.println("--- test removeMin() ---");
        bst.removeMin();
        bst.inOrder();

        System.out.println("--- test removeMax() ---");
        bst.removeMax();
        bst.inOrder();

        System.out.println("--- test remove() ---");
        bst.remove(5);
        bst.inOrder();

    }

}
