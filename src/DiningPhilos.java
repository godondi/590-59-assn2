public class DiningPhilos {
    Philo[] philosophers;

    public DiningPhilos(int numPhilosophers) {
        philosophers = new Philo[numPhilosophers];
        for (int i = 0; i < numPhilosophers; i++) {
            philosophers[i] = new Philo(i + 1);
        }
    }

    public Philo getPhilosopher(int num) {
        return philosophers[num - 1];
    }
    
    class Eater extends Thread {
        private int eaterIndex;

        public Eater(int eaterIndex) {
            this.eaterIndex = eaterIndex;
        }

        @Override
        public void run() {
            philosophers[eaterIndex].pickUpFork();
            // Simulate eating
            System.out.println("Philosopher " + philosophers[eaterIndex].getNum() + " picked up forks " + philosophers[eaterIndex].getLeftFork() + " and " + philosophers[eaterIndex].getRightFork() + " and is eating.");
            try {
                Thread.sleep(2000); // Simulate time taken to eat
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            philosophers[eaterIndex].putDownFork();
            System.out.println("Philosopher " + philosophers[eaterIndex].getNum() + " is thinking.");
        }
    }

    public void startSimulation() {
        for (int i = 0; i < philosophers.length; i++) {
            System.out.println("Philosopher " + philosophers[i].getNum() + " is thinking.");
        }
        for (int i = 0; i < philosophers.length; i++) {
            Eater eaterOne = new Eater(i);
            Eater eaterTwo = new Eater((i + 2) % philosophers.length);
            eaterOne.start();
            eaterTwo.start();
            try {
                eaterOne.join();
                eaterTwo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}