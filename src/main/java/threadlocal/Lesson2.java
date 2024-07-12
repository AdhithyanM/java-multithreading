package threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// importance of cleaning up the thread local when using same thread
public class Lesson2 {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocalObj = new ThreadLocal<>();

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.submit(() -> {
            threadLocalObj.set(Thread.currentThread().getName());
            // my work completed, now clean up
            threadLocalObj.remove();
        });

        for (int i = 1; i <= 15; i++) {
            executorService.submit(() -> {
                System.out.println(threadLocalObj.get());
            });
        }
    }
}
