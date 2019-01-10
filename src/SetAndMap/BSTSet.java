package SetAndMap;

import BinarySearchTree.BST;

import java.util.Random;

public class BSTSet<E extends Comparable<E>> implements Set<E> {
    private BST<E> bst;

    public BSTSet() {
        bst = new BST<>();
    }
    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i < getSize(); i ++) {
            res.append(bst.removeMax());
            if (i != getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }

    public static void main(String[] args) {
        Random random = new Random();
        BSTSet<Integer> bstSet = new BSTSet<>();
        for (int i = 0; i < 1000; i ++) {
            bstSet.add(random.nextInt(1000));
        }
        System.out.println(bstSet);
        System.out.println(bstSet.getSize());
    }
}
