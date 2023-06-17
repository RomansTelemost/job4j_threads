package ru.job4j.concurrent;

public class Wget {
    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                System.out.print("\rLoading : " + i + "%");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ie) {
                    System.out.println(ie.getMessage());
                }
            }
        });
        thread.start();
    }
}
