package Stack;

import Array.Array;

/**
 * 用数组实现栈
 * @param <E>
 */
public class ArrayStack<E> implements Stack<E> {

    private Array<E> array;

    public ArrayStack(int capacity) {
        array = new Array<E>(capacity);
    }

    public ArrayStack() {
        array = new Array<E>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.get(getSize() - 1);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: bottom [");
        for (int i = 0; i < getSize(); i ++) {
            res.append(array.get(i));
            if (i != getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println("---- test push ----");
        ArrayStack<Integer> stack = new ArrayStack<Integer>();
        for (int i = 0 ; i < 10; i ++) {
            stack.push(i);
            System.out.println(stack);
        }

        System.out.println("---- test pop ----");
        stack.pop();
        System.out.println(stack);

        System.out.println("---- test peek ----");
        System.out.println(stack.peek());
        System.out.println(stack);
    }
}
