package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class CASCount implements Runnable {

    private final AtomicInteger count = new AtomicInteger();
    public void increment() {
        int currValue;
        int newValue;
        do {
            currValue = count.get();
            newValue = currValue + 1;
        } while (!count.compareAndSet(currValue, newValue));
    }

    public int get() {
        return count.get();
    }

    @Override
    public void run() {
        increment();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        increment();
        increment();
        increment();
        increment();
        increment();
        increment();
        increment();
        increment();
        increment();
    }

    public static void main(String[] args) throws InterruptedException {
        CASCount casCount = new CASCount();

        Thread thread1 = new Thread(casCount);
        Thread thread2 = new Thread(casCount);
        Thread thread3 = new Thread(casCount);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println(casCount.get());
    }
}
