package Trie;

import java.util.TreeMap;

/**
 * Trie 字典树 前缀树
 */
public class Trie {
    private class Node {
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            this.next = new TreeMap<>();
        }
        public Node() {
            this(false);
        }
    }

    private Node root; // 根节点
    private int size; // 单词数量

    /**
     * 获取Trie中单词数量
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * Trie中添加一个单词
     * @param word
     */
    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i ++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }

        if (!cur.isWord) {
            cur.isWord = true;
            size ++;
        }
    }

    /**
     * 返回Trie中是否包含单词
     * @param word
     * @return
     */
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i ++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
               return false;
            }
            cur = cur.next.get(c);
        }

        return cur.isWord;
    }

    /**
     * 返回Trie中是否存在该前缀
     * @param prefix
     * @return
     */
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i ++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }

        return true;
    }


}
