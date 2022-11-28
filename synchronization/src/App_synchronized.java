public class App_synchronized {

    private int count = 0;

    // "synchronized" - apply the intrinsic lock that every object has in java
    public synchronized void increment(){
        count++;
    }
    public static void main(String [] args){
        App_synchronized app = new App_synchronized();
        app.doWork();
    }

    public void doWork(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0; i < 10000; i++){
                    increment();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0; i < 10000; i++){
                    increment();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Count is: "+count);
    }
}
