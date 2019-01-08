package Queue;

public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int head, tail;
    private int size;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity];
        head = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public void enqueue(E e) {
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

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Dequeue failed. Queue is empty.");
        }

        E ret = data[head];
        data[head] = null;
        head = (head + 1) % data.length;
        size--;

        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

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
        LoopQueue<Integer> queue = new LoopQueue<>();
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
