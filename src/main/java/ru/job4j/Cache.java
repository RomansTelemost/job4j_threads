package ru.job4j;

public class Cache {

    private static volatile Cache cache;

    public static Cache instOf() {
        /**
         * Проверка без монитора. Чтобы не делать узкое место. Для тех кто будет обращаться после первой инициализации к cache. - Многопоточное условие
         */
        if (cache == null) {
            /**
             * Выставляем монитор на класс так как статик метод. Раз был определён cache как null, то блокируем монитор - Однопоточное условие
             */
            synchronized (Cache.class) {
                /**
                 * Проверяем еще раз. Так как два потока могли считать на строке 11 что cache null. Первый поток мог уже присвоить значение.
                 * Второй не переприсвоит(увидит уже присвоенное первым потоком значение, так как установил так же volatile модификатор)
                 * и так как идёт проверка на null на строке 20 - Однопоточное условие
                 */
                if (cache == null) {
                    cache = new Cache();
                }
            }
        }
        return cache;
    }
}