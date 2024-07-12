package readwritelock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
    public static void main(String[] args) {
        SharedResource obj1 = new SharedResource();
        SharedResource obj2 = new SharedResource();

        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        Thread t1 = new Thread(() -> {
            obj1.produce(readWriteLock);
        }, "t1");
        Thread t2 = new Thread(() -> {
            obj2.produce(readWriteLock);
        }, "t2");
        Thread t3 = new Thread(() -> {
            obj1.consume(readWriteLock);
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
    }
}
