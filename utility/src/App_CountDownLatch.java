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
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }
}


public class App_CountDownLatch {
    public static void main(String [] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3); // CountDownLatch is Thread safe

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i =0; i<3; i++){
            executorService.submit(new Processor(latch));
        }
        latch.await();
        System.out.println("Completed.");
    }
}
