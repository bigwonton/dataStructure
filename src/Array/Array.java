package Array;

public class Array<E> {
    private E[] data;
    private int size;

    public Array(int capacity) {
        this.data = (E[]) new Object[capacity];
        this.size = 0;
    }

    public Array() {
        this(10);
    }

    public Array(E[] arr) {
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i ++) {
            data[i] = arr[i];
        }
        size = arr.length;
    }

    /**
     * 返回数组的大小
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 返回数组是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 返回数组的容量
     * @return
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 在数组的index位置处添加元素
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        if (size == getCapacity()) {
            resize(getCapacity() * 2);
        }

        for (int i = size; i > index; i --) {
            data[i] = data[i - 1];
        }
        data[index] = e;
        size ++;
    }

    /**
     * 在数组的首位置添加元素
     * @param e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 在数组的尾部添加元素
     * @param e
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 删除并返回数组index位置的元素
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Remove failed. Illegal index.");
        }

        E ret = data[index];
        for (int i = index; i < size - 1; i ++) {
            data[i] = data[i + 1];
        }
        size --;
        data[size] = null;

        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return ret;
    }

    /**
     * 删除并返回数组首位置的元素
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }
    /**
     * 删除并返回数组尾部的元素
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 返回数组index位置的元素
     * @return
     */
    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }

        return data[index];
    }

    /**
     * 修改数组index位置的元素
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Set failed. Illegal index.");
        }

        data[index] = e;
    }

    /**
     * 返回数组中是否存在元素
     * @param e
     * @return
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i ++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 返回数组中元素的位置，若不存在，返回-1
     * @param e
     * @return
     */
    public int find(E e) {
        for (int i = 0; i < size; i ++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 交换数组中两个位置的元素
     * @param i
     * @param j
     */
    public void swap(int i, int j) {
        E tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Array: " + String.format("size = %d, capacity = %d.\n", size, getCapacity()));
        res.append("[");
        for (int i = 0; i < size; i ++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(",");
            }
        }
        res.append("]");
        return res.toString();
    }

    /**
     * 动态调整数组大小
     * @param newCapacity
     */
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i ++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public static void main(String[] args) {
        Array<Integer> array = new Array<Integer>();
        for (int i = 0; i < 10; i ++) {
            array.addLast(i);
            System.out.println(array);

            if (i % 3 == 2) {
                array.removeLast();
                System.out.println(array);
            }
        }
        System.out.println(array);

        System.out.println("remove:" + array.remove(2));
        System.out.println(array);

        System.out.println("get:" + array.get(array.getSize() - 1));
        array.set((array.getSize() - 1),100);
        System.out.println("set:" + array.get(array.getSize() - 1));
    }




}
