package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Аннотация говорит пользователям данного класса, что класс можно использовать в многопоточном режиме и он будет работать правильно.
 */
@ThreadSafe
public class Count {

    /**
     * Аннотация выставляется над общим ресурсом. Аннотация имеет входящий параметр. Он указывает на монитор, по которому мы будем синхронизироваться.
     */
    @GuardedBy("this")
    private int value;

    public synchronized void increment() {
        this.value++;
    }

    public synchronized int get() {
        return this.value;
    }
}
