package ru.job4j.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Call mobileCall = new MobileCall("мobile", lock);
        Call skypeCall = new MobileCall("skype", lock);
        Call whatsappCall = new MobileCall("whatsapp", lock);

        Thread thread1 = new Thread(mobileCall::call);
        Thread thread2 = new Thread(skypeCall::call);
        Thread thread3 = new Thread(whatsappCall::call);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
