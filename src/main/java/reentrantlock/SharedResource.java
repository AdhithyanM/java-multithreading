package reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {

    boolean isAvailable = false;

    public void produce(ReentrantLock reentrantLock) {
        try {
            reentrantLock.lock();
            System.out.println("Lock acquired by: " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(4000);
        } catch (Exception e) {
            // handle exception here
        } finally {
            reentrantLock.unlock();
            System.out.println("Lock released by: " + Thread.currentThread().getName());
        }
    }
}