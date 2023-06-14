package ru.job4j.concurrent;

import javax.swing.plaf.nimbus.State;

public class ThreadState {

    private static final String NAMEFORMAT = "Thread name: %s, state %s";

    public static void main(String[] args) {
        Thread first = new Thread(
                () -> { },
                "firstThread"
        );
        Thread second = new Thread(
                () -> { },
                "secondThread");
        System.out.println(String.format(NAMEFORMAT, first.getName(), first.getState()));
        System.out.println(String.format(NAMEFORMAT, second.getName(), second.getState()));
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED
                || second.getState() != Thread.State.TERMINATED) {
            System.out.println(String.format(NAMEFORMAT, first.getName(), first.getState()));
            System.out.println(String.format(NAMEFORMAT, second.getName(), second.getState()));
        }
        System.out.println(String.format(NAMEFORMAT, first.getName(), first.getState()));
        System.out.println(String.format(NAMEFORMAT, second.getName(), second.getState()));
        System.out.println("Work has been completed");
    }
}
