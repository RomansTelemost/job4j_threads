package ru.job4j.di.model;

public class StartUI {

    private Store store;

    public StartUI(Store store) {
        this.store = store;
    }

    public void add(String value) {
        store.add(value);
    }

    public void print() {
        store.getAll().forEach(System.out::println);
    }
}
