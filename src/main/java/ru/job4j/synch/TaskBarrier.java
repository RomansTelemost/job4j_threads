package ru.job4j.synch;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TaskBarrier implements Runnable {

    private CyclicBarrier cyclicBarrier;

    public TaskBarrier(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is waiting on barrier");
            Thread.sleep(1000);
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName() + " has closed the barrier");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException b) {
            b.printStackTrace();
        }
    }
}
