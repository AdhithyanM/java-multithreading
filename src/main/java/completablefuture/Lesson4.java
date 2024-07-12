package completablefuture;

import java.util.concurrent.*;

// thenAccept and thenAcceptAsync
public class Lesson4 {
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

        CompletableFuture<String> completableFutureObj = CompletableFuture.supplyAsync(() -> {
            // some async operation here.
            return "Hello";
        }, poolExecutor);

        // both thenAccept and thenAcceptAsync method will not return anything and should be used only at end of the completableFuture operations sequence.

        // thenAccept is synchronous.
        completableFutureObj.thenAccept((String lastResult) -> {
            // do some synchronous operation finally
        });

        // thenAcceptAsync is asynchronous
        completableFutureObj.thenAcceptAsync((String lastResult) -> {
            // do some asynchronous operation finally
        }, poolExecutor);

        try {
            System.out.println(completableFutureObj.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
