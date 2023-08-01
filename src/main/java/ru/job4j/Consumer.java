package ru.job4j;

public class Consumer implements Runnable {

    private final SimpleBlockingQueue<Integer> simpleBlockingQueue;

    public Consumer(SimpleBlockingQueue<Integer> simpleBlockingQueue) {
        this.simpleBlockingQueue = simpleBlockingQueue;
    }

    @Override
    public void run() {
        try {
            System.out.println("Получили значение " + simpleBlockingQueue.poll());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
