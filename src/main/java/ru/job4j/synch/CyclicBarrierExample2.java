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
    public static int NUM_PARTIAL_RESULTS;
    public static int NUM_WORKERS;

    public void runSimulation(int numWorkers, int numberOfPartialResults) {
        NUM_PARTIAL_RESULTS = numberOfPartialResults;
        NUM_WORKERS = numWorkers;

        cyclicBarrier = new CyclicBarrier(NUM_WORKERS, new AggregatorThread(partialResults));

        System.out.println("Spawning " + NUM_WORKERS
                + " worker threads to compute "
                + NUM_PARTIAL_RESULTS + " partial results each");

        for (int i = 0; i < NUM_WORKERS; i++) {
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
