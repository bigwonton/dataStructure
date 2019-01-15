package UnionFind;

public class UnionFind1 implements UF {
    private int[] id; // 内部使用一个数组，存储id值，id相同，则表示connected

    public UnionFind1(int size) {
        id = new int[size];
        for (int i = 0; i < size; i ++) {
            id[i] = i; //初识，每个id都指向自己，没有互相连接的元素
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    private int find(int p) {
        if (p < 0 || p >= id.length) {
            throw new IllegalArgumentException("Index illegal.");
        }
        return id[p];
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
        int pID = find(p);
        int qID = find(q);

        if (pID == qID) {
            return;
        }

        // 将所有与p相连的元素，都与p相连
        for (int i = 0; i < id.length; i ++) {
            if (id[i] == pID) {
                id[i] = qID;
            }
        }
    }
}
