package scheduledexecutorservice;

import java.util.concurrent.*;

public class Lesson1 {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        Future<String> futureObj = executorService.schedule(() -> {
            return "Something returnable";
        }, 5, TimeUnit.SECONDS);

        try {
            System.out.println(futureObj.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
