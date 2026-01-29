package dining;

import java.util.concurrent.Semaphore;

public class DiningPhilosophers {
    public static void main(String[] args) {
        int n = 5;

        Fork[] forks = new Fork[n];
        for (int i = 0; i < n; i++) forks[i] = new Fork(i);

        Semaphore waiter = new Semaphore(n - 1, true); // fair

        for (int i = 0; i < n; i++) {
            Fork left = forks[i];
            Fork right = forks[(i + 1) % n];
            new Thread(new Philosopher(i, left, right, waiter)).start();
        }
    }
}
