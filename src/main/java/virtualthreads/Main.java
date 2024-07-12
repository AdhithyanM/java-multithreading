package virtualthreads;

public class Main {
    public static void main(String[] args) {
        // Virtual threads - introduced as preview in java 19 and rolled out in java 21
        Thread thread = Thread.ofVirtual().start(() -> {
            System.out.println("Some async runnable task just like how you deal with normal/platform threads");
            System.out.println();
        });
    }
}
