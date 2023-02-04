import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable {
    private CountDownLatch latch;

    public Processor(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Started.");
        someProcessing();
        latch.countDown();
    }

    private void someProcessing() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


public class App_CountDownLatch {
    public static void main(String [] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3); // CountDownLatch is Thread safe and will wait until achieve 0

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i =0; i<3; i++){
            executorService.submit(new Processor(latch));
        }
        latch.await();// Blocked operation to wait until all be executed
/*
        executorService.shutdown();// when all the tasks are finishing, the executor must be shutdown, only when all the threads with tasks are complete
        executorService.awaitTermination(1, TimeUnit.DAYS);
*/
        System.out.println("Completed.");
    }
}
