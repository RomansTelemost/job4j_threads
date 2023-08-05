package ru.job4j.lock;

import java.util.concurrent.locks.Lock;

public class MobileCall implements Call {

    private String name;
    private final Lock lock;

    public MobileCall(String name, Lock lock) {
        this.name = name;
        this.lock = lock;
    }

    @Override
    public void call() {
        lock.lock();
        try {
            System.out.println(name + " call starts");
            Thread.sleep(3000);
            System.out.println(name + " call ends");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
