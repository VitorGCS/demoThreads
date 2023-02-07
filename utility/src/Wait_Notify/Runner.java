package Wait_Notify;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    private void increment(){
        for(int i =0; i<10000; i++){
            count++;
        }
    }
    public void firstThread() throws InterruptedException {
        lock.lock();
        System.out.println("Waiting.... ");
        cond.await(); //unlock the lock
        System.out.println("Woken up! ");
        try{
            increment();
        }
        finally {
            lock.unlock();
        }
    }

    public void secondThread() throws InterruptedException {
        Thread.sleep(1000);
        lock.lock();
        System.out.println("Press the return key! ");
        new Scanner(System.in).nextLine();
        System.out.println("Got return key! ");

        cond.signal();//Notify all the waiting threads
        try{
            increment();
        }
        finally {
            System.out.println("Unlock the lock....");
            lock.unlock();
        }
    }

    public void finished(){
        System.out.println("Count is :"+count);
    }
}

class DemoRunner{
    public static void main(String[] args) throws InterruptedException {

    final Runner runner = new Runner();

    Thread t1 = new Thread(() -> {
        try {
            runner.firstThread();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });

    Thread t2 = new Thread(() -> {
        try {
            runner.secondThread();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });

    t1.start();
    t2.start();
    t1.join();
    t2.join();

    runner.finished();
    }
}


