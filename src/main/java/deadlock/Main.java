package deadlock;

public class Main {
    public static void main(String[] args) {
        System.out.println("main method started");
        SharedResource obj = new SharedResource();
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("t1 calling produce method");
                obj.produce();
            } catch (Exception e) {
                // handle exception here
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1000); // put t2 in sleep for a second to let t1 take the lock
                System.out.println("t2 calling produce method");
                obj.produce();  // now this will wait for t1 to release the lock on produce method.
            } catch (Exception e) {
                // handle exception here
            }
        }, "t2");

        t1.start();
        t2.start();

        try {
            Thread.sleep(3000); // put main thread in sleep
        } catch (Exception e) {
            // handle exception here
        }

        t1.suspend(); // suspend the t1 thread
        System.out.println("t1 is suspended");

        // If t1 not resumed, then t2 will forever wait for the monitor lock to be released - deadlock.
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            // handle exception here
        }
        t1.resume();

        System.out.println("main method ended");
    }
}
