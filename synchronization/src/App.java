import java.util.Scanner;

class Processor extends Thread {
    private volatile boolean running = true; // "volatile": prevent threads caching variables

    public void run() {
        while(running){
            System.out.println("Hello");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown(){
        running = false;
    }
}

public class App {
    /*
    * Problems:
    *  - data being cached - solution: "volatile": to prevent threads caching variables when they're not changed from within that thread;
    *  - threads interleaving;
    * */
    public static void main(String [] args){
        Processor proc1 = new Processor();
        proc1.start();

        System.out.println("Press return to stop ...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("Entered STOP");
        proc1.shutdown();

    }
}

