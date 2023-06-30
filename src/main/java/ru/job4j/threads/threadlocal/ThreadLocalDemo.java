package ru.job4j.threads.threadlocal;

public class ThreadLocalDemo {

    public static ThreadLocal<String> tl = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        Thread first = new FirstThread();
        Thread second = new SecondThread();
        tl.set("Это поток main.");

        first.start();
        second.start();
        first.join();
        second.join();
        System.out.println(tl.get());
    }
}
