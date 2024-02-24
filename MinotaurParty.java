import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class MinotaurParty {

    static class Labyrinth {
        private final AtomicBoolean cupcakePresent = new AtomicBoolean(true);
        private final AtomicInteger guestsWhoAteCupcake = new AtomicInteger(0);
        private final boolean[] hasEatenCupcake;
        private final int totalGuests;

        public Labyrinth(int totalGuests) {
            this.totalGuests = totalGuests;
            this.hasEatenCupcake = new boolean[totalGuests];
        }

        public synchronized void attemptEnterLabyrinth(int guestId) {
            if (!hasEatenCupcake[guestId]) {
                if (cupcakePresent.getAndSet(false)) {
                    System.out.println("Guest " + (guestId + 1) + " enters and eats a cupcake.");
                    hasEatenCupcake[guestId] = true;
                    guestsWhoAteCupcake.incrementAndGet();
                } else {
                    System.out.println("Guest " + (guestId + 1) + " enters but finds no cupcake.");
                }
                cupcakePresent.set(true);
            } else {
                System.out.println("Guest " + (guestId + 1) + " enters and leaves the Labyrinth.");
            }
        }

        public boolean allGuestsHaveEaten() {
            return guestsWhoAteCupcake.get() == totalGuests;
        }
    }

    static class PartyHost implements Runnable {
        private final Labyrinth labyrinth;

        public PartyHost(Labyrinth labyrinth) {
            this.labyrinth = labyrinth;
        }

        @Override
        public void run() {
            while (!labyrinth.allGuestsHaveEaten()) {
                int guestId = (int) (Math.random() * labyrinth.totalGuests);
                labyrinth.attemptEnterLabyrinth(guestId);

                try {
                    Thread.sleep((long) (Math.random() * 200));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of guests: ");
        int numberOfGuests = scanner.nextInt();
        scanner.close();

        Labyrinth labyrinth = new Labyrinth(numberOfGuests);
        Thread partyHost = new Thread(new PartyHost(labyrinth));
        partyHost.start();

        try {
            partyHost.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All guests have visited the labyrinth and had a chance to eat the cupcake.");
    }
}