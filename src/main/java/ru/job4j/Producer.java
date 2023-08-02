package ru.job4j;

public class Producer implements Runnable {

    private final SimpleBlockingQueue<Integer> simpleBlockingQueue;
    private int value;

    public Producer(SimpleBlockingQueue<Integer> simpleBlockingQueue, int value) {
        this.simpleBlockingQueue = simpleBlockingQueue;
        this.value = value;
    }

    @Override
    public void run() {
        System.out.println("Попытка поместить значение " + value);
        simpleBlockingQueue.offer(value);
    }
}
