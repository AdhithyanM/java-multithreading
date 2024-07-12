package scheduledexecutorservice;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Lesson2 {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        // executes task for every 5 seconds with a initial delay of 3 seconds.
        Future<?> futureObj = executorService.scheduleAtFixedRate(() -> {
            System.out.println("hello");
        }, 3, 5, TimeUnit.SECONDS);

        try {
            Thread.sleep(10000); // let main thread sleep for 10 sec to check cancel method working
            futureObj.cancel(true); // we can stop a scheduled repeating task using cancel method using its future reference obj.
        } catch (Exception e) {
            // handle exception here
        }

        Future<?> futureObj2 = executorService.scheduleAtFixedRate(() -> {
            System.out.println("Thread picked the task");
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread completed the task");
            // Although we specified that this task should run for every 3 seconds,
            // There is a timer of 6 seconds that we set for each task.
            // Until current task completes, next task will wait in the queue.
            // So this scheduling in general doesn't guarantee exact timings.
        }, 1, 3, TimeUnit.SECONDS);
    }
}
