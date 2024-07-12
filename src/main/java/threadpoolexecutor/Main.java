package threadpoolexecutor;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        // Creating our own custom thread pool executor
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                5,
                10,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(10),
                new CustomThreadFactory(),
                new CustomRejectHandler()
        );

        /*
          ThreadPool min and max size depends on various factors like:
            - CPU Cores
            - JVM Memory
            - Task Nature (CPU Intensive - less or I/O Intensive - more)
            - Concurrency Requirement
            - Memory Requirement to process a request
            - Throughput, etc.

          It's an iterative process to update the min and max values based on monitoring.

          Formula to find max no of threads (not fool-proof):
               Max no.of threads = No of cpu cores * (1 + Request waiting time / processing time)
         */

        for(int i=1; i<=25; i++) {
            executor.submit(() -> {
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    // handle exception here
                }
                System.out.println("Task processed by: " + Thread.currentThread().getName());
            });
        }
    }
}

class CustomThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setPriority(Thread.NORM_PRIORITY);
        thread.setDaemon(false);
        return thread;
    }
}

class CustomRejectHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Task rejected: " + r.toString());
    }
}