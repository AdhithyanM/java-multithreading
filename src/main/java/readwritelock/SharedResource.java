package readwritelock;

import java.util.concurrent.locks.ReadWriteLock;

// ReadWriteLock Example
public class SharedResource {
    boolean isAvailable = false;

    public void produce(ReadWriteLock lock) {
        try {
            lock.readLock().lock();
            System.out.println("Read Lock acquired by: " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(8000);
        } catch (Exception e) {
            // handle exception here
        } finally {
            System.out.println("Read Lock releasing by: " + Thread.currentThread().getName());
            lock.readLock().unlock();
        }
    }

    public void consume(ReadWriteLock lock) {
        try {
            lock.writeLock().lock();
            System.out.println("Write Lock acquired by: " + Thread.currentThread().getName());
            isAvailable = false;
        } catch (Exception e) {
            // handle exception here
        } finally {
            System.out.println("Write Lock releasing by: " + Thread.currentThread().getName());
            lock.writeLock().unlock();
        }
    }
}
