package future.withcallable;

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

        Future<List<Integer>> futureObj = poolExecutor.submit(() -> {
            // do some task
            List<Integer> output = new ArrayList<>();
            output.add(300);
            return output;
        });

        try {
            List<Integer> result = futureObj.get();
            System.out.println(result.get(0));
        } catch (Exception e) {
            // handle exception here
        }
    }
}
