package dining;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Philosopher implements Runnable {
    private final int id;
    private final Fork left, right;
    private final Semaphore waiter;

    public Philosopher(int id, Fork left, Fork right, Semaphore waiter) {
        this.id = id;
        this.left = left;
        this.right = right;
        this.waiter = waiter;
    }

    public void run() {
        try {
            while (true) {
                System.out.println("P" + id + " thinking");
                Thread.sleep(rand(200, 700));

                waiter.acquire();

                // consistent order (extra safety / cleaner behavior)
                Fork first = left.id() < right.id() ? left : right;
                Fork second = left.id() < right.id() ? right : left;

                first.pickUp(id);
                second.pickUp(id);

                System.out.println("P" + id + " EATING");
                Thread.sleep(rand(200, 600));

                second.putDown(id);
                first.putDown(id);

                waiter.release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private int rand(int a, int b) {
        return ThreadLocalRandom.current().nextInt(a, b + 1);
    }
}
