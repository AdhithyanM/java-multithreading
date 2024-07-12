package StampedLock;

public class Main {
    public static void main(String[] args) {
        SharedResource obj = new SharedResource();

        Thread t1 = new Thread(() -> {
            obj.produce();
        }, "t1");

        Thread t2 = new Thread(() -> {
            obj.consume();
        }, "t2");

        t1.start();
//        t2.start();  // uncomment this and try
    }
}
