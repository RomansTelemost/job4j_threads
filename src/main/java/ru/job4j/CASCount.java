package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class CASCount {

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
}
