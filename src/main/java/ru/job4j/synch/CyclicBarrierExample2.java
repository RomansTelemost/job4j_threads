package ru.job4j.synch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample2 {

    private CyclicBarrier cyclicBarrier;
    private List<List<Integer>> partialResults = Collections.synchronizedList(new ArrayList<>());
    private Random random = new Random();
    public static int numPartialResults;
    public static int numWorkers;

    public void runSimulation(int numWorkers, int numberOfPartialResults) {
        numPartialResults = numberOfPartialResults;
        numWorkers = numWorkers;

        cyclicBarrier = new CyclicBarrier(numWorkers, new AggregatorThread(partialResults));

        System.out.println("Spawning " + numWorkers
                + " worker threads to compute "
                + numPartialResults + " partial results each");

        for (int i = 0; i < numWorkers; i++) {
            Thread worker = new Thread(new NumberCrunchedThread(cyclicBarrier, partialResults, random));
            worker.setName("Thread " + i);
            worker.start();
        }
    }
    public static void main(String[] args) {
        CyclicBarrierExample2 demo = new CyclicBarrierExample2();
        demo.runSimulation(5, 3);
    }
}
