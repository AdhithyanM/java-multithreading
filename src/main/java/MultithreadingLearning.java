// 1. By implementing Runnable interface

public class MultithreadingLearning implements Runnable {

    @Override
    public void run() {
        System.out.println("Invoked run method of ThreadSampleClass from thread: "+ Thread.currentThread().getName());
    }
}

/**
 *
 *     public static void main(String[] args) {
 *         System.out.println("Inside main method: " + Thread.currentThread().getName());
 *         MultithreadingLearning runnableObj = new MultithreadingLearning();
 *         Thread thread = new Thread(runnableObj);
 *         thread.start();
 *         System.out.println("Finish main method: " + Thread.currentThread().getName());
 *     }
 *
 */


// 2. By extending Thread class

/**
 * public class MultithreadingLearning extends Thread {
 *
 *     @Override
 *     public void run() {
 *         System.out.println("Invoked run method of ThreadSampleClass from thread: "+ Thread.currentThread().getName());
 *     }
 * }
 */

/**
 *
 *     public static void main(String[] args) {
 *         System.out.println("Inside main method: " + Thread.currentThread().getName());
 *         MultithreadingLearning myThreadClass = new MultithreadingLearning();
 *         myThreadClass.start();
 *         System.out.println("Finish main method: " + Thread.currentThread().getName());
 *     }
 *
 */