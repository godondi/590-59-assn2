import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Fork {
    public String name;
    private ForkStatus occupied;
    private Philosopher reservedForPhilosopher;
    private Philosopher currentPhilosopher;

    private LinkedList<Philosopher> philosophersQueue;

    public Fork(String name) {
        occupied = ForkStatus.AVAILABLE;
        this.name = name;
        this.philosophersQueue = new LinkedList<Philosopher>();
    }

    public ForkPickUpAttemptResult pickUp(Philosopher philosopher) {
        if (occupied == ForkStatus.AVAILABLE) {
            occupied = ForkStatus.OCCUPIED;
            currentPhilosopher = philosopher;
            return ForkPickUpAttemptResult.SUCCEED;
        }
        else if (occupied == ForkStatus.NOTIFYING_PHILOSOPHER && reservedForPhilosopher == philosopher) {
            occupied = ForkStatus.OCCUPIED;
            currentPhilosopher = philosopher;
            reservedForPhilosopher = null;
            return ForkPickUpAttemptResult.SUCCEED;
        }
        else {
            philosophersQueue.add(philosopher);
            return ForkPickUpAttemptResult.FAIL;
        }
    }

    public void putDown(Philosopher philosopher) {
        if (occupied == ForkStatus.OCCUPIED && currentPhilosopher == philosopher) {
            currentPhilosopher = null;

            // Notify the next philosopher in line
            if (!philosophersQueue.isEmpty()) {
                occupied = ForkStatus.NOTIFYING_PHILOSOPHER;

                // Notify the philosopher waiting in line
                while (occupied == ForkStatus.NOTIFYING_PHILOSOPHER && !philosophersQueue.isEmpty()) {
                    Philosopher philosopherToInform = philosophersQueue.pop();

                    // Check if philosopher is dead and continue
                    if (philosopherToInform.getStatus() != PhilosopherStatus.STARVED) {
                        // Wake philosopher
                        philosopherToInform.interrupt();
                        reservedForPhilosopher = philosopherToInform;
                    }
                }

                if (reservedForPhilosopher == null) {
                    occupied = ForkStatus.AVAILABLE;
                }
            } else {
                occupied = ForkStatus.AVAILABLE;
            }
        }
    }
}
