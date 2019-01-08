package Queue;

/**
 * 用循环队列实现队列
 * 循环队列的 head 和 tail 的位置+1或-1时，都需要进行 %data.length（取余）操作
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;   // 数组
    private int head, tail; // 队列首位置、尾位置
    private int size;   // 队列的大小

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity];
        head = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    /**
     * 获取队列的大小
     * @return
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * 返回队列是否为空
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取队列的容量
     * @return
     */
    public int getCapacity() {
        return data.length - 1;
    }

    /**
     * 队列中加入一个元素
     * @param e
     */
    @Override
    public void enqueue(E e) {
        // 如果容量已满，扩容
        if (size == getCapacity()) {
            resize(2 * getCapacity());
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;
    }

    // resize后，head和tail的位置也要相应的调整
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i ++) {
          newData[i] = data[(i + head) % data.length];
        }
        data = newData;
        head = 0;
        tail = size;
    }

    /**
     * 队列出队一个元素，并将其返回
     * @return
     */
    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Dequeue failed. Queue is empty.");
        }

        E ret = data[head];
        data[head] = null; // 游离元素
        head = (head + 1) % data.length;
        size--;

        // 缩小容量
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    /**
     * 返回队列队首的元素
     * @return
     */
    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Get failed. Queue is empty.");
        }
        return data[head];
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue:"+String.format("size = %d, capacity = %d", size, getCapacity())+"\n head [");
        for (int i = 0; i < size; i ++) {
            res.append(data[(i + head) % data.length]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<Integer>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
