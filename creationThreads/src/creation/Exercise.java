package creation;

public class Exercise {

    public static void main(String[] args) {

        int a = 1; // variable is not final

        Thread anonymousThread1 = new Thread(() -> { System.out.println(a); });

        anonymousThread1.start();
    }

}
