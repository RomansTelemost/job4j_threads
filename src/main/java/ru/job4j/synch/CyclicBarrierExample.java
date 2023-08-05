package ru.job4j.synch;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Thread(() -> {
            System.out.println("хехе");
        }));

        Thread t1 = new Thread(new TaskBarrier(cyclicBarrier), "Thread 1");
        Thread t2 = new Thread(new TaskBarrier(cyclicBarrier), "Thread 2");
        Thread t3 = new Thread(new TaskBarrier(cyclicBarrier), "Thread 3");

        t1.start();
        t2.start();
        t3.start();
    }
}
