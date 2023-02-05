package Wait_Notify;

import java.util.Scanner;

public class Processor {
    public void producer() throws InterruptedException{
        synchronized (this){
            System.out.println("Producer thread running ....");
            wait(); // frees the lock
            System.out.println("Resumed.");
        }
    }

    public void consumer() throws InterruptedException{
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);

        synchronized (this){
            System.out.println("Waiting for return key.");
            scanner.nextLine();
            System.out.println("Return key pressed.");
            notify(); // notify the threads waiting but not frees the lock
            Thread.sleep(5000);
            //only here the lock is frees
        }
    }
}

class Demo {
    public static void main(String[] args) throws InterruptedException {
        final Processor processor = new Processor();

        Thread t1 = new Thread( () -> {
            producerHandled(processor);
        });

        Thread t2 = new Thread( () -> {
            consumerHandled(processor);
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    private static void consumerHandled(Processor processor) {
        try {
            processor.consumer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void producerHandled(Processor processor) {
        try {
            processor.producer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}