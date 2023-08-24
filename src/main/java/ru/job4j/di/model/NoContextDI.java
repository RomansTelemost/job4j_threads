package ru.job4j.di.model;

public class NoContextDI {

    public static void main(String[] args) {
        Store store = new Store();
        StartUI ui = new StartUI(store);
        ui.add("Petr Arsentev");
        ui.add("Ivan ivanov");
        ui.print();
    }
}
