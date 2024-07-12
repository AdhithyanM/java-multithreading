package producerconsumerproblem;

public class Main {
    public static void main(String[] args) {
        SharedResource sharedResource2 = new SharedResource(3);
        Thread producerThread = new Thread(() -> {
            try {
                for(int i=1; i<=6; i++) {
                    sharedResource2.produce(i);
                }
            } catch (Exception e) {
                // handle exception here
            }
        });
        Thread consumerThread = new Thread(() -> {
            try {
                for(int i=1; i<=6; i++) {
                    sharedResource2.consume();
                }
            } catch (Exception e) {
                // handle exception here
            }
        });
        producerThread.start();
        consumerThread.start();
    }
}
