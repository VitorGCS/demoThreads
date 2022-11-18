import creation.RunnerExtendsThread;
import creation.RunnerImplementsRunnable;

public class App {
    public static void main(String [] args){
        RunnerExtendsThread runner1 = new RunnerExtendsThread();
        runner1.start();
        RunnerExtendsThread runner2 = new RunnerExtendsThread();
        runner2.start();

        Thread t1 = new Thread( new RunnerImplementsRunnable());
        t1.start();
        Thread t2 = new Thread( new RunnerImplementsRunnable());
        t2.start();

        //Anonymous Thread
        Thread anonymousThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0; i<10; i++){
                    System.out.println("Anonymous Thread : "+i+ " - "+Thread.currentThread().getName() );
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        anonymousThread.start();
    }
}
