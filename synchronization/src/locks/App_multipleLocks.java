package locks;

public class App_multipleLocks {
    public static void main(String [] args) throws InterruptedException {
        new Worker().main();
    }
}
