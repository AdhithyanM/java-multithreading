package monitorlock;

public class SharedResource {
    public synchronized void task1() {
        // do something
        System.out.println(Thread.currentThread().getName() + " acquired lock for task1");
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            // Exception handling here
        }
        System.out.println(Thread.currentThread().getName() + " finishes task1");
    }

    public void task2() {
        System.out.println(Thread.currentThread().getName() + " inside task2 waiting to perform critical section");
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " inside critical section");
        }
    }

    public void task3() {
        System.out.println(Thread.currentThread().getName() + " performing task3");
    }
}

