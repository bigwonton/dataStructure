package PriorityQueueAndHeap;

import Array.Array;

public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<E>(capacity);
    }

    public MaxHeap() {
        data = new Array<E>();
    }

    public MaxHeap(E[] arr) {
        data = new Array<E>(arr);
        heapify();
    }

    public int getSize() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index 0 doesn't has parent");
        }
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * 向堆中添加一个元素
     * @param e
     */
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1, e);
    }

    private void siftUp(int index, E e) {
        while (index > 0 && data.get(parent(index)).compareTo(data.get(index)) < 0) {
            data.swap(index, parent(index));
            index = parent(index);
        }
    }

    /**
     * 返回堆中的最大值
     * @return
     */
    public E findMax() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Heap is empty.");
        }
        return data.get(0);
    }

    /**
     * 删除堆中的最大元素
     * @return
     */
    public E extractMax() {
        E ret = findMax();

        // 1. 交换最大元素与最后一个位置的元素
        // 2. 删除最后一个元素
        // 3. Sift Down
        data.swap(0, getSize() - 1);
        data.removeLast();
        siftDown(0);

        return ret;
    }

    private void siftDown(int index) {
        while (leftChild(index) < getSize()) {
            int j = leftChild(index);
//            左孩子<右孩子
            if (j + 1 < getSize() && data.get(j).compareTo(data.get(j + 1)) < 0) {
                j ++;
            }
            if (data.get(j).compareTo(data.get(index)) <= 0) {
                break;
            }
            data.swap(j, index);
            index = j;
        }
    }

    /**
     * 整理成堆的形状
     */
    private void heapify() {
        for (int i = parent(getSize() - 1); i >= 0; i --) {
            siftDown(i);
        }


    }

    /**
     * 替换最大元素
     * @param e
     * @return
     */
    public E replace(E e) {
        E ret = findMax();
        data.set(0 ,e);
        siftDown(0);
        return ret;
    }


}
