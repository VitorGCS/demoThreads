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
        SimulationOfOperations();
        System.out.println("Completed: "+id);
    }

    private void SimulationOfOperations() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class App_threadPools {

    public static void main(String [] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for(int i=0; i<5; i++)
            executorService.submit(new Processor(i));

        executorService.shutdown();// when all the tasks are finishing, the executor must be shutdown, only when all the threads with tasks are complete

        System.out.println("All tasks submitted.");

        executorService.awaitTermination(1, TimeUnit.DAYS);

        System.out.println("All tasks completed.");
    }
}
