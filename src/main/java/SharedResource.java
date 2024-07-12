public class SharedResource {

    boolean isItemPresent = false;

    public synchronized void addItem() {
        System.out.println("Producer thread adding the item");
        isItemPresent = true;
        System.out.println("Producer thread calling the notify method");
        notifyAll();
    }

    public synchronized void consumeItem() {
        System.out.println("Consumer thread inside consumeItem method");
        // while loop to avoid 'spurious wake-up'
        while(!isItemPresent) {
            try {
                System.out.println("Consumer thread is waiting as no item is present");
                wait(); // release all the monitor lock
            } catch (Exception e) {
                // exception handling goes here
            }
        }
        System.out.println("Consumer thread consumed the item");
        isItemPresent = false;
    }
}

/**
 *     public static void main(String[] args) {
 *         SharedResource sharedResourceObj = new SharedResource();
 *         Thread producerThread = new Thread(() -> {
 *             try {
 *                 Thread.sleep(5000);
 *             } catch (Exception e) {
 *                 // exception handling here
 *             }
 *             sharedResourceObj.addItem();
 *         });
 *         Thread consumerThread = new Thread(() -> {
 *             sharedResourceObj.consumeItem();
 *         });
 *         producerThread.start();
 *         consumerThread.start();
 *     }
 */