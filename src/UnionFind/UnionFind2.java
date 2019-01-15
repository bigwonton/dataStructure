package UnionFind;

/**
 * 构建一棵指向父节点的树
 */
public class UnionFind2 implements UF {
    private int[] parent; // 指向父节点

    // 初始，元素都指向自己
    public UnionFind2(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i ++) {
            parent[i] = i;
        }
    }

    /**
     * 返回元素p的根节点的位置
     * @param p
     * @return
     */
    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("Illegal index.");
        }

        while (parent[p] != p) {
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

        parent[p] = qRoot;
    }
}
