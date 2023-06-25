package ru.job4j.concurrent;

public class ThreadStop {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    int count = 0;
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println(count++);
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException i) {
                            System.out.println(i.getMessage());
                            System.out.println(Thread.currentThread().isInterrupted());
                            System.out.println(Thread.currentThread().getState());
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
        thread.join();
    }
}
