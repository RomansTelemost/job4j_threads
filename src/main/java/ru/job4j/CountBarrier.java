package ru.job4j;

public class CountBarrier {

    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public synchronized void count() {
        count++;
        notifyAll();
    }

    public synchronized void await() {
        while (count < total) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        CountBarrier countBarrier = new CountBarrier(10);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                countBarrier.count();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            countBarrier.await();
            System.out.println("Работа завершена т.к. счетчик стал больше " + (countBarrier.getCount() - 1));
        });
        thread1.start();
        thread2.start();
    }
}
