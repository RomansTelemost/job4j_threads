package ru.job4j.concurrent;

import javax.swing.plaf.nimbus.State;

public class ThreadState {

    private static final String NAME_FORMAT = "Thread name: %s, state %s";

    public static void main(String[] args) {
        Thread first = new Thread(
                () -> { },
                "firstThread"
        );
        Thread second = new Thread(
                () -> { },
                "secondThread");
        System.out.println(String.format(NAME_FORMAT, first.getName(), first.getState()));
        System.out.println(String.format(NAME_FORMAT, second.getName(), second.getState()));
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED
                || second.getState() != Thread.State.TERMINATED) {
            System.out.println(String.format(NAME_FORMAT, first.getName(), first.getState()));
            System.out.println(String.format(NAME_FORMAT, second.getName(), second.getState()));
        }
        System.out.println(String.format(NAME_FORMAT, first.getName(), first.getState()));
        System.out.println(String.format(NAME_FORMAT, second.getName(), second.getState()));
        System.out.println("Work has been completed");
    }
}
