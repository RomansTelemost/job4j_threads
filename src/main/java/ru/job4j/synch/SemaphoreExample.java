package ru.job4j.synch;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    public static void main(String[] args) throws InterruptedException {
        Semaphore sem = new Semaphore(1);
        Runnable task = () -> {
            try {
                sem.acquire(2);
                System.out.println("Нить выполнила задачу");
                sem.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(task).start();
        Thread.sleep(3000);
        sem.release(1);
        System.out.println("sa");
        Thread.sleep(3000);
        sem.release(1);
    }
}
