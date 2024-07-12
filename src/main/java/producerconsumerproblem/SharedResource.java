package producerconsumerproblem;

import java.util.LinkedList;
import java.util.Queue;

public class SharedResource {
    private Queue<Integer> sharedBuffer;
    private int bufferSize;

    public SharedResource(int bufferSize) {
        sharedBuffer = new LinkedList<>();
        this.bufferSize = bufferSize;
    }

    public synchronized void produce(int item) throws Exception {
        // If Buffer is full, wait for the consumer to consume items
        // while loop to avoid 'spurious wake-up'
        while(sharedBuffer.size() == bufferSize) {
            System.out.println("Buffer is full, producer thread is waiting for consumer thread to consume");
            wait();
        }
        sharedBuffer.add(item);
        System.out.println("Produced item: " + item);
        // Notify the consumer that there are items to consume now
        notify();
    }

    public synchronized int consume() throws Exception {
        // If Buffer is full, wait for the producer to produce items
        // while loop to avoid 'spurious wake-up'
        while(sharedBuffer.isEmpty()) {
            System.out.println("Buffer is empty, consumer thread is waiting for producer thread to produce");
            wait();
        }
        int item = sharedBuffer.poll();
        System.out.println("Consumed item: " + item);
        // Notify the producer thread that there is space in the buffer now
        notify();
        return item;
    }
}
