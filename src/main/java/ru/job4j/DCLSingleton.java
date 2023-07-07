package ru.job4j;

public class DCLSingleton {

    /**
     * inst переменная может читаться двумя потоками. Т.е. Первый изменил, а второй должен увидеть эти изменения.
     * Т.е. читать каждый раз значение из регистра, не ссылаясь на значение загруженного в cpu cache.
     * Для этого укажем модификатор volatile
     */
    private static volatile DCLSingleton inst;

    private DCLSingleton() {
    }

    public static DCLSingleton instOf() {
        if (inst == null) {
            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                }
            }
        }
        return inst;
    }
}
