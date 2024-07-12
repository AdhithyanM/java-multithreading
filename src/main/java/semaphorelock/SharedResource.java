package semaphorelock;

import java.util.concurrent.Semaphore;

public class SharedResource {
    boolean isAvailable = false;
    Semaphore lock = new Semaphore(2);

    public void produce() {
        try {
            lock.acquire();
            System.out.println("Lock acquired by: " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(4000);
        } catch (Exception e) {
            // handle exception here
        } finally {
            System.out.println("Lock releasing by: " + Thread.currentThread().getName());
            lock.release();
        }
    }
}
