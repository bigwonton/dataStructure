package SegmentTree;

public class SegmentTree<E> {
    private E[] data;
    private E[] tree;
    private Merge<E> merger;

    public SegmentTree(E[] arr) {
        this.data = (E[]) new Object[arr.length];

        for (int i = 0; i < arr.length; i ++) {
            data[i] = arr[i];
        }

        this.tree = (E[]) new Object[arr.length * 4];
        buildSegmentTree(0, 0, arr.length - 1);
    }

    // 在treeIndex位置创建表示区间[l,r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        // 记得merge左右子树
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }
        return data[index];
    }

    // 返回区间[queryL, queryR]的值
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length ||
                queryR < 0 || queryR >= data.length ||
                queryL > queryR) {
            throw new IllegalArgumentException("Query failed. Index is illegal.");
        }
        return query(0, 0, data.length, queryL, queryR);
    }

    // 在treeIndex位置，[l,r]范围内，查询[queryL,queryR]区间的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);

        int mid = l + (r - l) / 2;
        if (mid + 1 <= queryL) {
            return query(rightChildIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftChildIndex, l, mid, queryL, queryR);
        }
        E leftResult = query(leftChildIndex, l, mid, queryL, mid);
        E rightResult = query(rightChildIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    public void set(int index, E e) {
        set(0, 0, data.length ,index, e);
    }

    private void set(int treeIndex, int l, int r, int index, E e) {
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        int mid = l + (r - l) / 2;
        int leftChildIndex = leftChild(index);
        int rightChildIndex = rightChild(index);

        if (mid + 1 <= index) {
            set(rightChildIndex, mid + 1, r, index, e);
        } else {
            set(leftChildIndex, r, mid, index, e);
        }

        tree[treeIndex] = merger.merge(tree[leftChildIndex], tree[rightChildIndex]);
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i < tree.length; i ++) {
            if (tree[i] != null) {
                res.append(tree[i]);
            } else {
                res.append("null");
            }
            if (i != tree.length - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }
}
