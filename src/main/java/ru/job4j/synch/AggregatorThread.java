package ru.job4j.synch;

import java.util.List;

public class AggregatorThread implements Runnable {

    private List<List<Integer>> partialResults;

    public AggregatorThread(List<List<Integer>> partialResults) {
        this.partialResults = partialResults;
    }

    @Override
    public void run() {
        String thisThreadName = Thread.currentThread().getName();

        System.out.println(
                thisThreadName + ": Computing sum of " + CyclicBarrierExample2.NUM_WORKERS
                        + " workers, having " + CyclicBarrierExample2.NUM_PARTIAL_RESULTS + " results each.");
        int sum = 0;

        for (List<Integer> threadResult : partialResults) {
            System.out.print("Adding ");
            for (Integer partialResult : threadResult) {
                System.out.print(partialResult+" ");
                sum += partialResult;
            }
            System.out.println();
        }
        System.out.println(thisThreadName + ": Final result = " + sum);
    }
}
