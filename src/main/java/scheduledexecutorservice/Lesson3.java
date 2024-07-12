package scheduledexecutorservice;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Lesson3 {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        Future<?> futureObj = executorService.scheduleWithFixedDelay(() -> {
            System.out.println("Thread picked the task");
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread completed the task");
        }, 1, 3, TimeUnit.SECONDS);

        // Although we specified that this task should run for every 3 seconds,
        // There is a timer of 6 seconds that we set for each task.
        // Until current task completes, next task delay counter will not even start.
        // So scheduling in general doesn't guarantee exact timings.
    }
}
