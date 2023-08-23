package ru.job4j.synch;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class GetThread implements Runnable {

    Exchanger<String> exchanger;
    String message;

    public GetThread(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
        this.message = "Hello World!";
    }

    @Override
    public void run() {
        try {
            System.out.println("Get1");
            System.out.println("Get2");
            message = exchanger.exchange(message, 1000, TimeUnit.MILLISECONDS);
            System.out.println("GetThread has received: " + message);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (TimeoutException to) {
            to.printStackTrace();
        }
    }
}
