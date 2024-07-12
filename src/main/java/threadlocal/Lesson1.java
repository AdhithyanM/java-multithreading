package threadlocal;

public class Lesson1 {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocalObj = new ThreadLocal<>();

        threadLocalObj.set(Thread.currentThread().getName());

        Thread t1 = new Thread(() -> {
           threadLocalObj.set(Thread.currentThread().getName());
        });
        t1.start();

        System.out.println(threadLocalObj.get()); // prints main
    }
}
