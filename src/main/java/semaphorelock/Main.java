package semaphorelock;

public class Main {
    public static void main(String[] args) {
        SharedResource obj = new SharedResource();

        Thread t1 = new Thread(() -> {
            obj.produce();
        }, "t1");
        Thread t2 = new Thread(() -> {
            obj.produce();
        }, "t2");
        Thread t3 = new Thread(() -> {
            obj.produce();
        }, "t3");
        Thread t4 = new Thread(() -> {
            obj.produce();
        }, "t4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
