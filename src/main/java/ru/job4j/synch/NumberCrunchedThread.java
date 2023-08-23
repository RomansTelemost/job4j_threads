package ru.job4j.synch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class NumberCrunchedThread implements Runnable {

    private CyclicBarrier cyclicBarrier;
    private List<List<Integer>> partialResults;
    private Random random;

    public NumberCrunchedThread(CyclicBarrier cyclicBarrier, List<List<Integer>> partialResults, Random random) {
        this.cyclicBarrier = cyclicBarrier;
        this.partialResults = partialResults;
        this.random = random;
    }

    @Override
    public void run() {
        String thisThreadName = Thread.currentThread().getName();
        List<Integer> partialResult = new ArrayList<>();

        for (int i = 0; i < CyclicBarrierExample2.numPartialResults; i++) {
            Integer num = random.nextInt(10);
            System.out.println(thisThreadName + ": Crunching some numbers! Final result - " + num);
            partialResult.add(num);
        }

        partialResults.add(partialResult);
        try {
            System.out.println(thisThreadName + " waiting for others to reach barrier.");
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException b) {
            b.printStackTrace();
        }
    }
}
