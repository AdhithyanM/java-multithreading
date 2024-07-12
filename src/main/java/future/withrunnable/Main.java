package future.withrunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
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

        List<Integer> output = new ArrayList<>();

        /**
         * There are 3 overloaded methods of submit.
         *   1. sumbit(Runnable)
         *   2. submit(Runnable, T)
         *   3. submit(Callable)
         *
         * 1. takes Runnable as argument. By default Runnable won't return anything. its void. so output will be Future<?>
         * 2. takes Runnable as argument, Also can specify the resource that runnable would modify. so output will be Future<T> (Workaround for return)
         * 3. takes Callable as argument which can directly return some output. Cleaner way
         */
//        Future<?> futureObj = poolExecutor.submit(new MyRunnable(output));

        Future<List<Integer>> futureObj = poolExecutor.submit(new MyRunnable(output), output);

        try {
            futureObj.get(); // wait for task to get complete

            // 1st way - if we have access to output in caller thread
            System.out.println(output.get(0));

            // 2nd way
            List<Integer> result = futureObj.get();
            System.out.println(result.get(0));

        } catch (Exception e) {
            // handle exception here
        }
    }
}
