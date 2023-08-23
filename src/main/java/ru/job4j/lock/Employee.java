package ru.job4j.lock;

import java.util.concurrent.locks.Lock;

public class Employee extends Thread {
    private String name;
    private Lock lock;

    public Employee(String name, Lock lock) {
        this.name = name;
        this.lock = lock;
        this.start();
    }

    public void run() {
        /**
         * TryLock
        */
        if (lock.tryLock()) {
            try {
                System.out.println(name + " ждёт");
                /**
                 * lock.lock();
                 */
                System.out.println(name + " пользуется");
                Thread.sleep(2000);
                System.out.println(name + " закончил");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println(name + " не собирается ждать");
        }

        /**
         * Lock
         */
        try {
            System.out.println(name + " ждёт");
            lock.lock();
            System.out.println(name + " пользуется");
            Thread.sleep(2000);
            System.out.println(name + " закончил");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
