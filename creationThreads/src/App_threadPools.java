import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable{
    private int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Starting: "+id);
        // Simulation some operation....
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Completed: "+id);
    }
}

public class App_threadPools {

    public static void main(String [] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for(int i=0; i<5; i++){
            executorService.submit(new Processor(i));
        }

        executorService.shutdown();

        System.out.println("All tasks submitted.");

        executorService.awaitTermination(1, TimeUnit.DAYS);

        System.out.println("All tasks completed.");

    }
}
