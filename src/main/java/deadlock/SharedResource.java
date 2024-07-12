package deadlock;

public class SharedResource {
    public synchronized void produce() {
        System.out.println(Thread.currentThread().getName()+ " acquired the lock");
        try {
            Thread.sleep(8000);
        } catch (Exception e) {
            // handle exception here
        }
        System.out.println(Thread.currentThread().getName()+ " released the lock");
    }
}
