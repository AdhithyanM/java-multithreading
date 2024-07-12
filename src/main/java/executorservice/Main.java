package executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        /*
            Fixed Thread Pool Executor
            - Creates a thread pool with fixed no of threads.
            - Threads will be kept alive when they are idle
            - Uses Unbounded Queue
            When to use:
                - If we know exact info of how many async tasks are needed
            Disadvantage:
                - Not good when workload is heavy. Leads to limited concurrency.
         */
        ExecutorService fixedThreadPoolExecutor = Executors.newFixedThreadPool(5);
        fixedThreadPoolExecutor.submit(() -> System.out.println("a runnable task"));
        /*
            Single Thread Pool Executor
            - Creates a thread pool with just a single thread
            - Thread will be kept alive when it is idle
            - Uses Unbounded Queue
            When to use:
                - If the requirement is to process tasks sequentially
            Disadvantage:
                - No concurrency at all.
         */
        ExecutorService singleThreadPoolExecutor = Executors.newSingleThreadExecutor();
        /*
            Cached Thread Pool Executor
            - Creates a thread pool with no threads in it. However max threads that can be spawned is Integer.MAX_VALUE
            - Thread won't be kept alive after 60 seconds if they are idle
            - Uses Blocking Queue with size 0
            When to use:
                - Good for handling burst of short lived tasks.
            Disadvantage:
                - Increased memory usage if there are so many threads submitted rapidly that are long lived.
         */
        ExecutorService cachedThreadPoolExecutor = Executors.newCachedThreadPool();
        /*
            Work Stealing Thread Pool Executor
            - Uses fork join pool
            - No.of threads depends on the processor
            - Each threads has its own work stealing queue
            - Task is submitted to the submission queue of the executor
            - Task can be divided into sub task that multiple threads resolves
            - Extremely fast - refer forkjoinpool package
         */
        ExecutorService workStealingPoolExecutor = Executors.newWorkStealingPool();
    }
}
