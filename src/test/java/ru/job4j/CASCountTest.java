package ru.job4j;

import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CASCountTest {

    @RepeatedTest(100)
    public void whenIncrementMultithreadingThen() throws InterruptedException {
        CASCount casCount = new CASCount();

        Thread thread1 = new Thread(() -> {
            casCount.increment();
            casCount.increment();
            casCount.increment();
        });
        Thread thread2 = new Thread(() -> {
            casCount.increment();
            casCount.increment();
            casCount.increment();
        });
        Thread thread3 = new Thread(() -> {
            casCount.increment();
            casCount.increment();
            casCount.increment();
        });

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
        assertEquals(9, casCount.get());
    }
}