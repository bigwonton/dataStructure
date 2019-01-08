package Queue.leetcode102;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private class Array<E> {
        private E[] data;
        private int size;

        public Array(int capacity) {
            this.data = (E[]) new Object[capacity];
            this.size = 0;
        }

        public Array() {
            this(10);
        }

        public int getSize() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int getCapacity() {
            return data.length;
        }

        public void add(int index, E e) {
            if (index < 0 || index > size) {
                throw new IllegalArgumentException("Add failed. Illegal index.");
            }

            if (size == getCapacity()) {
                resize(getCapacity() * 2);
            }

            for (int i = size; i > index; i --) {
                data[i] = data[i - 1];
            }
            data[index] = e;
            size ++;
        }

        public void addFirst(E e) {
            add(0, e);
        }

        public void addLast(E e) {
            add(size, e);
        }

        public E remove(int index) {
            if (index < 0 || index > size) {
                throw new IllegalArgumentException("Remove failed. Illegal index.");
            }

            E ret = data[index];
            for (int i = index; i < size - 1; i ++) {
                data[i] = data[i + 1];
            }
            size --;
            data[size] = null;

            if (size == data.length / 4 && data.length / 2 != 0) {
                resize(data.length / 2);
            }
            return ret;
        }

        public E removeFirst() {
            return remove(0);
        }

        public E removeLast() {
            return remove(size - 1);
        }

        public E get(int index) {
            if (index < 0 || index > size) {
                throw new IllegalArgumentException("Get failed. Illegal index.");
            }

            return data[index];
        }

        public void set(int index, E e) {
            if (index < 0 || index > size) {
                throw new IllegalArgumentException("Set failed. Illegal index.");
            }

            data[index] = e;
        }

        public boolean contains(E e) {
            for (int i = 0; i < size; i ++) {
                if (data[i].equals(e)) {
                    return true;
                }
            }
            return false;
        }

        public int find(E e) {
            for (int i = 0; i < size; i ++) {
                if (data[i].equals(e)) {
                    return i;
                }
            }
            return -1;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append("Array: " + String.format("size = %d, capacity = %d.\n", size, getCapacity()));
            res.append("[");
            for (int i = 0; i < size; i ++) {
                res.append(data[i]);
                if (i != size - 1) {
                    res.append(",");
                }
            }
            res.append("]");
            return res.toString();
        }

        private void resize(int newCapacity) {
            E[] newData = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i ++) {
                newData[i] = data[i];
            }
            data = newData;
        }
    }

    private interface Queue<E> {
        int getSize();
        boolean isEmpty();
        void enqueue(E e);
        E dequeue();
        E getFront();
    }

    private class ArrayQueue<E> implements Queue<E> {
        private Array<E> array;

        public ArrayQueue(int capacity) {
            array = new Array<E>(capacity);
        }

        public ArrayQueue() {
            array = new Array<E>();
        }

        @Override
        public int getSize() {
            return array.getSize();
        }

        public int getCapacity() {
            return array.getCapacity();
        }

        @Override
        public boolean isEmpty() {
            return array.isEmpty();
        }

        @Override
        public void enqueue(E e) {
            array.addLast(e);
        }

        @Override
        public E dequeue() {
            return array.removeFirst();
        }

        @Override
        public E getFront() {
            return array.get(0);
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append("Queue: front [");
            for (int i = 0; i < getSize(); i ++) {
                res.append(array.get(i));
                if (i != getSize() - 1) {
                    res.append(", ");
                }
            }
            res.append("] tail");
            return res.toString();
        }

    }


    // 二叉树的层序遍历，这里没有使用递归方式
    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        ArrayQueue<Pair<TreeNode, Integer>> queue = new ArrayQueue<>();
        int level = 0;
        queue.enqueue(new Pair<>(root, level));

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> curPair = queue.dequeue();
            TreeNode curNode = curPair.getKey();
            level = curPair.getValue();

            if (level == res.size()) {
                res.add(new ArrayList<Integer>());
            }
            res.get(level).add(curNode.val);

            if (curNode.left != null) {
                queue.enqueue(new Pair<>(curNode.left, level + 1));
            }
            if (curNode.right != null) {
                queue.enqueue(new Pair<>(curNode.right, level + 1));
            }
        }
        return res;

    }

    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);

        node3.left = node9;
        node3.right = node20;
        node20.left = node15;
        node20.right = node7;

        System.out.println(new Solution().levelOrder(node3));
    }

}