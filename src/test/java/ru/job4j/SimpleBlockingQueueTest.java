package ru.job4j;


import org.junit.jupiter.api.Test;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleBlockingQueueTest {

    @Test
    public void testQueueOperations() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        AtomicInteger producerSum = new AtomicInteger(0);
        AtomicInteger consumerSum = new AtomicInteger(0);
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    queue.offer(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                producerSum.addAndGet(i);
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    int value = queue.poll();
                    consumerSum.addAndGet(value);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(producerSum.get()).isEqualTo(55);
        assertThat(consumerSum.get()).isEqualTo(55);
    }

    @Test
    public void test2QueueOperations() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        try {
                            queue.offer(i);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();

        assertThat(buffer).containsExactly(0, 1, 2, 3, 4);
    }
}