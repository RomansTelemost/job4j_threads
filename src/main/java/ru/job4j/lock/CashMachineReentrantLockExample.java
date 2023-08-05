package ru.job4j.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CashMachineReentrantLockExample {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();

        new Employee("Me", lock);
        new Employee("Me2", lock);
        new Employee("Me3", lock);

        Thread.sleep(3000);
        new Employee("Me4", lock);
        new Employee("Me5", lock);
    }
}
