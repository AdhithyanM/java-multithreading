package future.withrunnable;

import java.util.List;

public class MyRunnable implements Runnable {
    List<Integer> list;

    public MyRunnable(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        list.add(300);
        // do some work
    }
}
