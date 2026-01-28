/** Main class for the dining philosophers problem simulation. */

public class Main {
    public static void main(String[] args) {
        int numberOfPhilosophers = 5;
        DiningPhilos diningPhilosophers = new DiningPhilos(numberOfPhilosophers);
        diningPhilosophers.startSimulation();
    }
}