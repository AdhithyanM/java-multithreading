package reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        SharedResource obj1 = new SharedResource();
        SharedResource obj2 = new SharedResource();
        ReentrantLock reentrantLock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            obj1.produce(reentrantLock);
        }, "t1");
        Thread t2 = new Thread(() -> {
            obj2.produce(reentrantLock);
        }, "t2");
        t1.start();
        t2.start();
    }
}