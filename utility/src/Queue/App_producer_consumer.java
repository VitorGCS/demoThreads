package Queue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App_producer_consumer {

    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> { producerHandled(); });
        Thread t2 = new Thread(() -> { consumerHandled(); });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }


    private static void producerHandled() {
        try {
            producer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void producer() throws InterruptedException {
        Random random = new Random();
        while (true)
            queue.put(random.nextInt(100));
    }

    private static void consumerHandled() {
        try {
            consumer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void consumer() throws InterruptedException {
        Random random = new Random();

        while (true) {
            Thread.sleep(100); //Some processing work
            if (random.nextInt(10) == 0) {
                Integer value = queue.take();
                System.out.println("Taken value: " + value + "; Queue size is: " + queue.size() + " -> queue list: "+queue.toString());
            }
        }
    }
}
