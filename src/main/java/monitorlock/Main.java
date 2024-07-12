package monitorlock;

public class Main {
    public static void main(String[] args) {
        SharedResource obj = new SharedResource();
        Thread t1 = new Thread(() -> { obj.task1(); });
        Thread t2 = new Thread(() -> { obj.task2(); });
        Thread t3 = new Thread(() -> { obj.task3(); });
        t1.start();
        t2.start();
        t3.start();
        // Monitor lock works only on same object.
        // For managing critical sections where multiple objects sharing same resource, use ReentrantLock.
    }
}
