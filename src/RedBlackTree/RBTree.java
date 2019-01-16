package RedBlackTree;

import java.math.BigDecimal;

public class RBTree<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 判断节点node的颜色
    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    // 左旋转
    private Node leftRotate(Node node) {
        Node x = node.right;

        node.left = x.left;
        x.left = node;

        // 维护节点的颜色颜色
        x.color = node.color;
        node.color = RED;

        return x;

    }

    // 右旋转
    private Node rightRotate(Node node) {
        Node x = node.left;

        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    // 颜色翻转
    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }



    /**
     * 向红黑树中插入新的元素(key,value)
     * @param key
     * @param value
     */
    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK; // 根节点为黑色
    }

    private Node add(Node node, K key, V value) {

        if (node == null) {
            size ++;
            return new Node(key, value); // 默认插入红色节点
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }

        if (isRed(node.right) && !isRed(node.left)) {
            leftRotate(node);
        }

        if (isRed(node.left) && isRed(node.left.left)) {
            rightRotate(node);
        }

        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }

    /**
     * 返回红黑树中是否包含key
     * @param key
     * @return
     */
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    // 返回以node为根节点的二分搜索树中，键为key所在的节点
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

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exists.");
        }

        node.value = value;
    }

    // 返回以node为根节点的二分搜索树的最小值所在的节点
    private Node minimun(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimun(node.left);
    }

    // 删除以node为根节点的二分搜索树的最小节点
    // 返回删除节点后的新的二分搜索树的根节点
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

    /**
     * 删除二分搜索树中键为key的节点
     * @param key
     * @return
     */
    public V remove(K key) {

        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    // Todo 
    private Node remove(Node node, K key) {

        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            // 待删除节点的左子树为空
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }
            // 待删除节点的右子树为空
            else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }
            // 待删除节点的左右子树都不为空
            else {

                Node successor = minimun(node.right);
                successor.right = removeMin(node.right);
                successor.left = node.left;

                node.left = node.right = null;
                return successor;
            }
        }
    }


}
