package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private int size;

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    public synchronized void offer(T value) throws InterruptedException, IllegalStateException {
        while (queue.size() == size) {
            queue.wait();
        }
        queue.offer(value);
        queue.notify();
    }

    public synchronized T poll() throws InterruptedException {
        if (queue.size() == 0) {
            queue.wait();
        }
        T value = queue.poll();
        queue.notify();
        return value;
    }
}
