package SetAndMap;

import LinkedList.LinkedList;

public class LinkedListSet<E extends Comparable<E>> implements Set<E> {
    private LinkedList<E> linkedList;
    @Override
    public void add(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public boolean contains(E e) {
        return linkedList.contains(e);
    }

    @Override
    public void remove(E e) {
        linkedList.removeElement(e);
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i < getSize(); i ++) {
            res.append(linkedList.removeFirst());
            if (i != getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }
}
