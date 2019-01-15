package AVLTree;

import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V> {

    private class Node {
        public K key;
        public V value;
        public Node left, right; // 左子树，右子树
        public int height; //高度

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 获取节点node的高度
    private int getHeight(Node node) {
        return node.height;
    }

    // 获取节点node的平衡因子
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 向AVL树中添加新元素
     * @param key
     * @param value
     */
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        // 终止条件
        if (node == null) {
            size ++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }

        // 更新height 递归，新节点往上的每一层都更新了height
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);

        // 维护平衡
        // LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }

        // RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }

        // LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }


        return node;

    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.equals(node.key)) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }
    }

    /**
     * 获取是AVL树中是否包含key
     * @param key
     * @return
     */
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    /**
     * 返回AVL树中key对应的value
     * @param key
     * @return
     */
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    /**
     * 更新key对应的值
     * @param key
     * @param value
     */
    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + "doesn't exists.");
        }
        node.value = value;
    }


    //获取以node为根结点的AVL树中的最小的节点
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    // 返回并删除以node为根结点的AVL中的最小节点
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // 返回并删除以node为根节点的AVL树中的键为key的节点
    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }

        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {
            // 找到了待删除的节点

            // 待删除节点的左子树为空
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size --;
                retNode = rightNode;
            }

            // 待删除节点的右子树为空
            else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size --;
                retNode = leftNode;
            }

            else {
                // 待删除节点的左右子树都不为空
                // 1. 找到右子树的最小节点 successor
                // 2. 删除右子树的最小节点
                // 3. 用successor替代待删除节点
                // 4. 处理待删除节点（游离节点）
                // 5. 返回 successor
                Node successor = minimum(node.right);
                successor.right = removeMin(node.right);
                successor.left = node.left;

                node.left = node.right = null;
                retNode =  successor;
            }
        }

        if (retNode == null) {
            return null;
        }

        // 更新height 递归，新节点往上的每一层都更新了height
        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        // 维护平衡
        // LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rightRotate(retNode);
        }

        // RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }

        // LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        // RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;

    }

    /**
     * 删除并返回AVL树中键为key的元素
     * @param key
     * @return
     */
    public V remove(K key) {
        Node node =getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }


    /**
     * 返回该二叉树是否是一棵二分搜索树
     * @return
     */
    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i ++) {
            if (keys.get(i -1).compareTo(keys.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null) {
            return;
        }

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    /**
     * 返回该二叉树是否是一棵平衡二叉树
     * @return
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }

        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    /**
     * 对节点 y 进行 右旋 操作，返回旋转后的新节点x
     *        y                       x
     *       / \                   /    \
     *      x  T4                 z      y
     *     / \                   / \    / \
     *    z  T3                 T1 T2  T3 T4
     *   / \
     * T1   T2
     * @param y
     * @return
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;

        // 右旋
        y.left = T3;
        x.right = y;

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;

    }

    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        y.right = T2;
        x.left = y;

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

}
