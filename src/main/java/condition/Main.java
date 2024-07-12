package condition;

public class Main {
    public static void main(String[] args) {
        SharedResource obj = new SharedResource();

        Thread t1 = new Thread(() -> {
            for(int i=1; i<=2; i++) {
                obj.produce();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for(int i=1; i<=2; i++) {
                obj.consume();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
