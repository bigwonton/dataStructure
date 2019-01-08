package LinkedList;

import Stack.Stack;

public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> list;

    public LinkedListStack() {
        list = new LinkedList<E>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack : top [");
        for (int i = 0; i < getSize(); i ++) {
            res.append(list.get(i));
            if (i != getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack();
        for (int i = 0; i < 10; i ++) {
            stack.push(i);
            System.out.println(stack);

            if (i % 3 == 2) {
                stack.pop();
                System.out.println(stack);
            }
        }
    }
}
