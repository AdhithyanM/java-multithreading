package completablefuture;

import java.util.concurrent.*;

// thenApply and thenApplyAsync methods
public class Lesson2 {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                3,
                3,
                1,
                TimeUnit.HOURS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        try {
            CompletableFuture<String> completableFutureObj = CompletableFuture.supplyAsync(() -> {
                        try {
                            System.out.println("Thread name of supplyAsync method: " + Thread.currentThread().getName());
                            Thread.sleep(5000);
                        } catch (Exception e) {
                            // handle exception here
                        }
                        return "Supply Async Result";
                    }, poolExecutor)
                    // do some additional tasks with the obtained result
                    // thenApply method is synchronous and works on the same thread which performed the previous task
                    .thenApply((String previousResult) -> {
                        System.out.println("Thread name of thenApply method: " + Thread.currentThread().getName());
                        return previousResult + " passed to thenApply";
                    })
                    // we can also perform asynchronous tasks further with the obtained result of previous CompletableFuture
                    // thenApplyAsync methods are performed by different threads.
                    // multiple thenApplyAsync methods chaining doesn't guarantee the order of execution
                    .thenApplyAsync((String previousResult) -> {
                        System.out.println("Thread name of thenApplyAsync method: " + Thread.currentThread().getName());
                        return previousResult + " and passed to thenApplyAsync1";
                    }, poolExecutor)
                    .thenApplyAsync((String previousResult) -> {
                        System.out.println("Thread name of thenApplyAsync method: " + Thread.currentThread().getName());
                        return previousResult + " and passed to thenApplyAsync2";
                    }, poolExecutor)
                    .thenApplyAsync((String previousResult) -> {
                        System.out.println("Thread name of thenApplyAsync method: " + Thread.currentThread().getName());
                        return previousResult + " and passed to thenApplyAsync3";
                    }, poolExecutor)
                    .thenApplyAsync((String previousResult) -> {
                        System.out.println("Thread name of thenApplyAsync method: " + Thread.currentThread().getName());
                        return previousResult + " and passed to thenApplyAsync4";
                    }, poolExecutor);

            System.out.println(completableFutureObj.get());
        } catch (Exception e) {
            // handle exception here
        }
    }
}
