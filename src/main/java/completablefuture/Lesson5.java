package completablefuture;

import java.util.concurrent.*;

// thenCombine and thenCombineAsync
public class Lesson5 {
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

        CompletableFuture<String> completableFutureObj1 = CompletableFuture.supplyAsync(() -> "k", poolExecutor);
        CompletableFuture<Integer> completableFutureObj2 = CompletableFuture.supplyAsync(() -> 10, poolExecutor);

        // we can combine 2 CompletableFuture results and perform another synchronous or asynchronous task using thenCombine and thenCombineAsync methods
        CompletableFuture<String> completableFutureObj3 = completableFutureObj1.thenCombine(completableFutureObj2, (String result1, Integer result2) -> result1 + result2);

        try {
            System.out.println(completableFutureObj3.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
