import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class MinotaurVase {

    private static final Semaphore semaphore = new Semaphore(1);

    static class Guest implements Runnable {
        private final int id;

        public Guest(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("Guest " + id + " is viewing the vase.");
                Thread.sleep((long) (Math.random() * 1000));
                System.out.println("Guest " + id + " has finished viewing the vase.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                semaphore.release();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of guests: ");
        int numberOfGuests = scanner.nextInt();
        scanner.close();

        for (int i = 0; i < numberOfGuests; i++) {
            new Thread(new Guest(i + 1)).start();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}