package ru.job4j.synch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Runnable task1 = () -> {
            try {
                System.out.println("Умываемся");
                Thread.sleep(2000);
                countDownLatch.countDown();
                System.out.println("Умылись");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable task2 = () -> {
            try {
                System.out.println("Собираесмя");
                Thread.sleep(4000);
                countDownLatch.countDown();
                System.out.println("Собрались");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        System.out.println("Готовимся к походу в магазин");
        new Thread(task1).start();
        new Thread(task2).start();
        countDownLatch.await();
        System.out.println("Идём в магазин");
    }
}
