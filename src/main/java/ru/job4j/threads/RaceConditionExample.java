package ru.job4j.threads;

public class RaceConditionExample {

    public static int num = 0;

    public synchronized void incr() {
        for (int i = 0; i < 99999; i++) {
            int current = num;
            int next = num + 1;
            if (current + 1 != next) {
                throw new IllegalStateException("Некорректное сравнение: " + current + " + 1 = " + next);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        RaceConditionExample rc = new RaceConditionExample();
        Thread t1 = new Thread(rc::incr);
        t1.start();
        Thread t2 = new Thread(rc::incr);
        t2.start();
        t1.join();
        t2.join();
    }

    public static void incr1() {
        Runnable task = () -> {
            for (int i = 0; i < 99999; i++) {
                int current = num;
                int next = ++num;
                if (current + 1 != next) {
                    throw new IllegalStateException("Некорректное сравнение: " + current + " + 1 = " + next);
                }
            }
        };
        new Thread(task).start();
        new Thread(task).start();
    }
}
