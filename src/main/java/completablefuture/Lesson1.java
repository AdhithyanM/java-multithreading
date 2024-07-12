package completablefuture;

import java.util.concurrent.*;

// CompletableFuture with supplyAsync method
public class Lesson1 {
    public static void main(String[] args) {
        try {
            ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                    1,
                    1,
                    1,
                    TimeUnit.HOURS,
                    new ArrayBlockingQueue<>(10),
                    Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.AbortPolicy()
            );

            CompletableFuture<String> asyncTask1 = CompletableFuture.supplyAsync(() -> {
                // this is the task which need to be completed by thread in a async fashion
                return "task completed";
            }, poolExecutor);
            // passing poolExecuter is optional. If not passed it will take a random thread from fork thread pool

            System.out.println(asyncTask1.get());

        } catch (Exception e) {
            // handle exception here
        }
    }
}
