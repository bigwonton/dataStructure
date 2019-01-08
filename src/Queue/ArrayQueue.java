package Queue;

import Array.Array;

/**
 * 用数组实现队列
 * @param <E>
 */
public class ArrayQueue<E> implements Queue<E> {
    // 内部维护一个数组
    // 不需要维护size变量，直接使用array的size
    private Array<E> array;

    public ArrayQueue(int capacity) {
        array = new Array<E>(capacity);
    }

    public ArrayQueue() {
        array = new Array<E>();
    }

    /**
     * 获取队列的大小
     * @return
     */
    @Override
    public int getSize() {
        return array.getSize();
    }

    /**
     * 获取队列的容量
     * @return
     */
    public int getCapacity() {
        return array.getCapacity();
    }

    /**
     * 返回队列是否为空
     * @return
     */
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 队列中加入一个元素
     * @param e
     */
    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    /**
     * 队列中出队一个元素
     * @return
     */
    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    /**
     * 获取队列列首的元素
      * @return
     */
    @Override
    public E getFront() {
        return array.get(0);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: front [");
        for (int i = 0; i < getSize(); i ++) {
            res.append(array.get(i));
            if (i != getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<Integer>();
        for (int i = 0; i < 10; i ++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 1) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
