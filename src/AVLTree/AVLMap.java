package AVLTree;

import SetAndMap.Map;

public class AVLMap<K extends Comparable<K>, V> implements Map<K, V> {

    private AVLTree<K, V> data;


    @Override
    public void add(K key, V value) {
        data.add(key, value);
    }

    @Override
    public V remove(K key) {
        return data.remove(key);
    }

    @Override
    public boolean contains(K key) {
        return data.contains(key);
    }

    @Override
    public V get(K key) {
        return data.get(key);
    }

    @Override
    public void set(K key, V value) {
        data.set(key, value);
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }
}
