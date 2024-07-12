package future;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                1,
                1,
                1,
                TimeUnit.HOURS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        // new thread will be created and it will perform the task
        // futureObj holds reference of the task submitted
        Future<?> futureObj = poolExecutor.submit(() -> {
            try {
                Thread.sleep(7000);
                System.out.println("This is the task, which thread will execute");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // caller is checking the status of the thread it created
        System.out.println(futureObj.isDone());

        try {
            futureObj.get(2, TimeUnit.SECONDS);  // waits 2 seconds for the task to complete else throws TimeoutException.
        } catch (TimeoutException e) {
            System.out.println("TimeoutException happened");
        } catch (Exception e) {
            // handle other exception here.
        }

        try {
            futureObj.get();  // continuously waits till the task completes by any means.
        } catch (Exception e) {
            // handle exception here
        }

        System.out.println("is Done: " + futureObj.isDone());
        System.out.println("is Cancelled: " + futureObj.isCancelled());
    }
}
