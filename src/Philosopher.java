public class Philosopher extends Thread {
    private PhilosopherStatus status;
    private int waited;         // Number of milliseconds waited
    public final String name;

    private final Fork rightFork;
    private final Fork leftFork;

    public Philosopher(String name, Fork rightFork, Fork leftFork) {
        this.status = PhilosopherStatus.ASLEEP;
        this.name = name;
        this.rightFork = rightFork;
        this.leftFork = leftFork;
        this.waited = 0;
    }

    @Override
    public void run() {
        think();
    }

    private void think() {
        System.out.println(name + " started thinking.");
        this.status = PhilosopherStatus.THINKING;

        // Get random length of time for how long the philosopher will think
        int max = 2000;     // Two seconds
        int min = 5000;     // Five seconds
        int thinkingTime = (int) (Math.random() * (max - min + 1)) + min;

        // Thread sleeps for a time
        try {
            Thread.sleep(thinkingTime);
        }
        catch (Exception e) {
            System.out.println("Error: " + name + " interrupted from thinking!!");
        }

        System.out.println(name + " is trying to eat.");
        eat();
    }

    private void eat() {
        ForkPickUpAttemptResult leftForkPickUpAttemptResult = pickUpLeftFork();
        ForkPickUpAttemptResult rightForkPickUpAttemptResult = pickUpRightFork();

        if (leftForkPickUpAttemptResult == ForkPickUpAttemptResult.SUCCEED && rightForkPickUpAttemptResult == ForkPickUpAttemptResult.SUCCEED) {
            System.out.println(name + " is eating.");
            status = PhilosopherStatus.EATING;

            // Get random length of time for how long the philosopher will eat
            int max = 2000;     // Two seconds
            int min = 5000;     // Five seconds
            int eatingTime = (int) (Math.random() * (max - min + 1)) + min;

            try {
                Thread.sleep(eatingTime);
            }
            catch (Exception e) {
                System.out.println("Error: " + e);
            }

            putDownForks();

            // Reset waited time and back to thinking
            waited = 0;
            System.out.println(name + " finished eating.");
            think();
        }
        else {
            if (leftForkPickUpAttemptResult == ForkPickUpAttemptResult.SUCCEED) {
                leftFork.putDown(this);
                System.out.println(name + " put down " + leftFork.name + ".");
            }
            if (rightForkPickUpAttemptResult == ForkPickUpAttemptResult.SUCCEED) {
                rightFork.putDown(this);
                System.out.println(name + " put down " + rightFork.name + ".");
            }

            status = PhilosopherStatus.WAITING_TO_EAT;
            System.out.println(name + " is waiting to eat.");

            // Record start time
            long startTime = System.nanoTime();
            try {
                // Waits for 15 seconds before dying
                Thread.sleep((int) (15000-waited));
                System.out.println(name + " died from starvation.");
                status = PhilosopherStatus.STARVED;
            }
            catch (InterruptedException e) {
                // Record how long we've already waited to eat
                long endTime = System.nanoTime();
                long duration = endTime - startTime;
                waited += (int) (duration / 1000000);

                System.out.println(name + " was told to try to eat again (waited " +
                        (int) (duration / 1000000) + " milliseconds, " +
                        waited + " milliseconds total).");

                // Attempt again to eat
                eat();
            }
        }

    }

    private ForkPickUpAttemptResult pickUpRightFork() {
        ForkPickUpAttemptResult rightStatus = rightFork.pickUp(this);

        if (rightStatus == ForkPickUpAttemptResult.SUCCEED) {
            System.out.println(name + " picked up " + rightFork.name + ".");
        }
        else {
            System.out.println(name + " couldn't pick up " + rightFork.name + ".");
        }

        return rightStatus;
    }

    private ForkPickUpAttemptResult pickUpLeftFork() {
        ForkPickUpAttemptResult leftStatus = leftFork.pickUp(this);

        if (leftStatus == ForkPickUpAttemptResult.SUCCEED) {
            System.out.println(name + " picked up " + leftFork.name + ".");
        }
        else {
            System.out.println(name + " couldn't pick up " + leftFork.name + ".");
        }

        return leftStatus;
    }

    private void putDownForks() {
        rightFork.putDown(this);
        leftFork.putDown(this);
        System.out.println(name + " put down " + rightFork.name + " and " + leftFork.name + ".");
    }

    public PhilosopherStatus getStatus() {
        return status;
    }
}
