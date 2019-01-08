package LinkedList;

import Queue.Queue;

public class LinkedListQueue<E> implements Queue<E> {

    private Node head, tail; // 自行维护头节点和尾节点 ， head 端删除，tail 端添加
    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 队列添加元素
     * @param e
     */
    @Override
    public void enqueue(E e) {
        // 如果队列为空，需要同时维护head和tail
        if (isEmpty()) {
            tail = new Node(e);
            head = tail;
        } else {
            // tail 端 添加
            tail.next = new Node(e);
            tail = tail.next;
        }

        size ++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Dequeue failed. Queue is empty.");
        }

        // head 端 删除
        Node ret = head;
        head = head.next;
        ret.next = null; // 游离元素
        // 如果删除后队列为空，需要维护 tail
        if (isEmpty()) {
            tail = null;
        }
        size --;
        return ret.e;
    }

    @Override
    public E getFront() {
        return null;
    }

    /**
     * 内部类
     */
    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

}
