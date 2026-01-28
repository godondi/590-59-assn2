public class Philo {
    private int num;
    private boolean eating;

    public Philo(int num) {
        this.num = num;
        this.eating = false;
    }  
    public int getNum() {
        return num;
    }
    public boolean isEating() {
        return eating;
    }
    public void pickUpFork() {
        this.eating = true;
    }
    public void putDownFork() {
        this.eating = false;
    }
    public int getLeftFork() {
        return num;
    }
    public int getRightFork() {
        return (num + 1) % 5;
    }

    public int[] forksInUse() {
        if (eating) {
            return new int[] {num, (num + 1) % 5};
        } else {
            return new int[] {};
        }
    }
}