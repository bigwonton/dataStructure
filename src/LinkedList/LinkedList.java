package LinkedList;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

/**
 * 链表
 * 依托于"节点" Node
 */
public class LinkedList<E> {


    private Node dummyHead; //虚拟头节点，它的next才是真正头节点
    private int size; // 链表大小

    public LinkedList() {
        dummyHead = new Node();
        size = 0;
    }

    /**
     * 获取链表大小
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 返回链表是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 链表index位置添加元素
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        // 找到index位置的prev节点
        Node prev = dummyHead;
        for (int i = 0; i < index; i ++) {
            prev = prev.next;
        }

        prev.next = new Node(e, prev.next);
        size ++;
    }

    /**
     * 链表头部添加元素
     * @param e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 链表尾部添加元素
     * @param e
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 删除并返回链表指定位置的元素
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Illegal index.");
        }

        // 找到index位置的prev节点
        Node prev = dummyHead;
        for (int i = 0; i < index; i ++) {
            prev = prev.next;
        }

        Node ret = prev.next;
        prev.next = ret.next;
        size --;
        ret.next = null;

        return ret.e;
    }

    /**
     * 删除并返回链表第一个元素
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除并返回链表最后一个元素
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 删除链表指定元素
     * @param e
     */
    public void removeElement(E e) {
        // 找到删除元素的前一个元素
        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.e.equals(e)) {
                break;
            }
            prev = prev.next;
        }

        // 判断是否找到，为空说明没有该元素
        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
        }
    }

    /**
     * 返回链表是否包含指定元素
     * @param e
     * @return
     */
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    /**
     * 获取链表index位置的元素
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }

        Node cur = dummyHead.next;
        for (int i = 0 ; i < index; i ++) {
            cur = cur.next;
        }
        return cur.e;
    }

    /**
     * 获取链表头部元素
     * @return
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 获取链表尾部元素
     * @return
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 更新链表index位置元素
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Illegal index.");
        }

        Node cur = dummyHead.next;
        for (int i = 0 ; i < index; i ++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < size; i ++) {
            res.append(get(i) + "->");
        }
        res.append("NULL");
        return res.toString();
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

    public static void main(String[] args) {

        LinkedList<Integer> list = new LinkedList<Integer>();
        System.out.println("---- test add ----");
        for (int i= 0; i < 10; i ++) {
            list.addFirst(i);
            System.out.println(list);
        }
        System.out.println("---- test remove ----");
        list.removeFirst();
        System.out.println(list);
        list.remove(2);
        System.out.println(list);
        list.removeElement(9);
        System.out.println(list);
        list.removeElement(0);
        System.out.println(list);
    }

}
