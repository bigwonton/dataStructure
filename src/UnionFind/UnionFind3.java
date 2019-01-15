package UnionFind;

/**
 * 构建一棵指向父节点的树
 * 基于size的优化
 *      增加sz[i]参数，表示以i为根的集合中的元素个数
 *      合并时，将size小的集合挂到size大的集合上
 */
public class UnionFind3 implements UF {
    private int[] parent;
    private int[] sz;

    public UnionFind3(int size) {
        parent = new int[size];
        sz = new int[size];
        for (int i = 0; i < size; i ++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }


    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("Illegal index.");
        }

        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    /**
     * 返回两个元素是否相连
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 连接两个元素
     * @param p
     * @param q
     */
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        // 将sz小的集合挂到sz大的集合上
        if (sz[p] < sz[q]) {
            parent[p] = qRoot;
            sz[q] += sz[p];
        } else {
            parent[q] = pRoot;
            sz[p] += sz[q];
        }
    }
}
