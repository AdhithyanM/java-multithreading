package StampedLock;

import java.util.concurrent.locks.StampedLock;

public class SharedResource {
    int a = 10;
    StampedLock lock = new StampedLock();

    public void produce() {
        long stamp = lock.tryOptimisticRead();
        try {
            System.out.println("Optimistic read performing by: " + Thread.currentThread().getName());
            a = 11;
            Thread.sleep(6000);
            if(lock.validate(stamp)) {
                System.out.println("Stamp validation successful. " + Thread.currentThread().getName() + " updated the value");
            } else {
                System.out.println("Stamp validation unsuccessful. " + Thread.currentThread().getName() + " rolling back the work");
                a = 10; // rollback
            }
        } catch (Exception e) {
            // handle exception here
        }
    }

    public void consume() {
        long stamp = lock.writeLock();
        System.out.println("Write lock acquired by: " + Thread.currentThread().getName());
        try {
            System.out.println("performing work");
            a = 9;
        } finally {
            lock.unlockWrite(stamp);
            System.out.println("Write lock released by: " + Thread.currentThread().getName());
        }
    }
}
