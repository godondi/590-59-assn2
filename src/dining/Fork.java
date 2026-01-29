package dining;

import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    private final int id;
    private final ReentrantLock lock = new ReentrantLock(true); // fair

    public Fork(int id) { this.id = id; }
    public int id() { return id; }

    public void pickUp(int p) {
        lock.lock();
        System.out.println("P" + p + " picked fork " + id);
    }

    public void putDown(int p) {
        lock.unlock();
        System.out.println("P" + p + " put fork " + id);
    }
}
