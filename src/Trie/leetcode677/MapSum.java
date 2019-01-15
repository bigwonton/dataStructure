package Trie.leetcode677;


import java.util.TreeMap;

/**
 * Trie中使用isWord的布尔类型来表示一个单词的结尾
 * 这里，使用value，来表示单词的结尾，同时表示单词的权重
 * 只要找到单词，然后将权重相加就行
 */
class MapSum {
    private class Node {
        public int value;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.value = 0;
            this.next = new TreeMap<>();
        }
        public Node() {
            this(false);
        }
    }

    private Node root; // 根节点


    /** Initialize your data structure here. */
    public MapSum() {
        this.root = new Node();
    }

    public void insert(String key, int val) {
        Node cur = root;
        for (int i = 0; i < key.length(); i ++) {
            char c = key.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }

//        if (cur.value == 0) {
            cur.value = val;
//        }
    }

    public int sum(String prefix) {

        Node cur = root;
        for (int i = 0; i < prefix.length(); i ++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return 0;
            }
            cur = cur.next.get(c);
        }

        return sum(cur);
    }

    public int sum(Node node) {
        int sum = node.value;
        for (Character ch : node.next.keySet()) {
            sum += sum(node.next.get(ch));
        }
        return sum;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
