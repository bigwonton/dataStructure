package UnionFind;

/**
 * 构建一棵指向父节点的树
 * 基于rank的优化
 *      增加rank[i]参数，表示以i为根的集合所表示的树的层数
 *      将rank低的集合合并到rank高的集合上
 */
public class UnionFind4 implements UF {
    private int[] parent;
    private int[] rank;

    public UnionFind4(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i ++) {
            parent[i] = i;
            rank[i] = 1;
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

        // 将rank低的集合合并到rank高的集合上
        if (rank[p] < rank[q]) {
            parent[p] = qRoot;
        } else if (rank[p] > rank[q]) {
            parent[q] = pRoot;
        } else {
            parent[p] = qRoot;
            rank[qRoot] += 1;
        }
    }
}
