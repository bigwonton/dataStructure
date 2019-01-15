package AVLTree;

import SetAndMap.Set;

public class AVLSet<E extends Comparable<E>> implements Set<E> {

    private AVLTree<E, Object> data;

    public AVLSet() {
        data = new AVLTree<E, Object>();
    }

    @Override
    public void add(E e) {
        data.add(e, null);
    }

    @Override
    public boolean contains(E e) {
        return data.contains(e);
    }

    @Override
    public void remove(E e) {
        data.remove(e);
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
