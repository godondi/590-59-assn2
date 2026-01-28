//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
// Philosopher
void main() {
    Fork forkOne = new Fork("Fork #1");
    Fork forkTwo = new Fork("Fork #2");
    Fork forkThree = new Fork("Fork #3");
    Fork forkFour = new Fork("Fork #4");
    Fork forkFive = new Fork("Fork #5");

    // Begin threads for each philosopher
    Philosopher philoOne = new Philosopher("Philosopher #1", forkOne, forkTwo);
    Philosopher philoTwo = new Philosopher("Philosopher #2", forkTwo, forkThree);
    Philosopher philoThree = new Philosopher("Philosopher #3", forkThree, forkFour);
    Philosopher philoFour = new Philosopher("Philosopher #4", forkFour, forkFive);
    Philosopher philoFive = new Philosopher("Philosopher #5", forkFive, forkOne);

    philoOne.start();
    philoTwo.start();
    philoThree.start();
    philoFour.start();
    philoFive.start();
}
