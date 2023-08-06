package ru.job4j.synch;

import java.util.concurrent.Exchanger;

public class PutThread implements Runnable {

    Exchanger<String> exchanger;
    String message;

    public PutThread(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
        this.message = "Hello Java!";
    }

    @Override
    public void run() {
        try {
            System.out.println("Put1");
            Thread.sleep(2000);
            System.out.println("Put2");
            message = exchanger.exchange(message);
            System.out.println("PutThread has received: " + message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
