package Wait_Notify;

import java.util.LinkedList;
import java.util.Random;

public class LowLevelSynchronization {

    public static void main(String[] args) throws InterruptedException {


        final Processor_LowLevelSynchronization processor = new Processor_LowLevelSynchronization();

        Thread t1 = new Thread(() -> {
            producerHandled(processor);
        });

        Thread t2 = new Thread(() -> {
            consumerHandled(processor);
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    private static void consumerHandled(Processor_LowLevelSynchronization processor) {
        try {
            processor.consumer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void producerHandled(Processor_LowLevelSynchronization processor) {
        try {
            processor.producer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class Processor_LowLevelSynchronization {

    private final int LIMIT = 10;
    private LinkedList<Integer> list = new LinkedList<>();
    private Object lock = new Object();

    public void producer() throws InterruptedException {
        int value = 0;

        while (true) {
            synchronized (lock) {
                while (list.size() == LIMIT) {
                    lock.wait(); // frees the lock
                }
                list.add(value++);
                lock.notify();
            }

        }
    }

    public void consumer() throws InterruptedException {

        while (true) {
            synchronized (lock) {
                while (list.size() == 0) {
                    lock.wait();
                }
                System.out.print("List size is: " + list.size());
                int value = list.removeFirst();
                System.out.println("; value is: " + value);
                lock.notify();
            }
            Thread.sleep(new Random().nextInt(1000));
        }
    }
}