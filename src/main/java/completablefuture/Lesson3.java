package completablefuture;

import java.util.concurrent.*;

// thenCompose and thenComposeAsync methods
public class Lesson3 {
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
                    .thenCompose((String prevResult) -> {
                        // Unlike thenApply and thenApplyAsync methods, we need to return a new CompletableFuture ourself here.
                        return CompletableFuture.supplyAsync(() -> prevResult + " passed to thenCompose1");
                    })
                    // Ordering is maintained with thenComposeAsync
                    // Depended actions that has to be executed asynchronously can be done with thenComposeAsync[[]
                    .thenComposeAsync((String prevResult) -> {
                        // Unlike thenApply and thenApplyAsync methods, we need to return a new CompletableFuture ourself here.
                        return CompletableFuture.supplyAsync(() -> prevResult + " passed to thenComposeAsync1");
                    }, poolExecutor)
                    .thenComposeAsync((String prevResult) -> {
                        // Unlike thenApply and thenApplyAsync methods, we need to return a new CompletableFuture ourself here.
                        return CompletableFuture.supplyAsync(() -> prevResult + " passed to thenComposeAsync2");
                    }, poolExecutor)
                    .thenComposeAsync((String prevResult) -> {
                        // Unlike thenApply and thenApplyAsync methods, we need to return a new CompletableFuture ourself here.
                        return CompletableFuture.supplyAsync(() -> prevResult + " passed to thenComposeAsync3");
                    }, poolExecutor)
                    .thenComposeAsync((String prevResult) -> {
                        // Unlike thenApply and thenApplyAsync methods, we need to return a new CompletableFuture ourself here.
                        return CompletableFuture.supplyAsync(() -> prevResult + " passed to thenComposeAsync4");
                    }, poolExecutor);

            System.out.println(completableFutureObj.get());
        } catch (Exception e) {
            // handle exception here
        }
    }
}
