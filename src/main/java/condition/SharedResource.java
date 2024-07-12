package condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {
    boolean isAvailable = false;
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void produce() {
        try {
            lock.lock();
            System.out.println("Produce lock acquired by: " + Thread.currentThread().getName());
            while(isAvailable) {
                // already available. thread has to wait for other thread to consume.
                System.out.println("Producer thread is waiting: " + Thread.currentThread().getName());
                condition.await();
            }
            isAvailable = true;
            condition.signalAll(); // let the consumers consume.
        } catch (Exception e) {
            // handle exception here
        } finally {
            System.out.println("Produce lock releasing by: " + Thread.currentThread().getName());
            lock.unlock();
        }
    }

    public void consume() {
        try {
            Thread.sleep(1000);
            lock.lock();
            System.out.println("Consume lock acquired by: " + Thread.currentThread().getName());

            if(!isAvailable) {
                // already not available. Thread has to wait for the producer to produce.
                System.out.println("Consumer thread is waiting: " + Thread.currentThread().getName());
                condition.await();
            }
            isAvailable = false;
            condition.signalAll();
        } catch (Exception e) {
            // handle exception here
        } finally {
            System.out.println("Consume lock is releasing by: " + Thread.currentThread().getName());
            lock.unlock();
        }
    }
}
