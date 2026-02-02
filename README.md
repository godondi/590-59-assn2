# Dining Philosophers – Java Threads

## Team Members

* Jimena Luna
* Grace Odondi
* Destiny Okonkwo



## How to Run

Compile:

```
javac -d out src/dining/*.java
```

Run:

```
java -cp out dining.DiningPhilosophers
```

## Design Overview

Each philosopher is implemented as a thread. Philosophers alternate between thinking and eating. Forks are shared objects placed between philosophers. Forks have state that keeps track of if a philospher can pick it up. 

Philosophers think and eat (represented by the thread sleeping) for a random amount of time. When a philospher goes to eat, they attempt to pick up each fork--if unsuccessful, they place either fork down, join the waiting queue for that fork, and go into a waiting state. When that fork becomes available, the thread is awaken and that philosopher can attempt again to eat. 

## Representation

* Philosopher → Thread (Runnable object)
* Fork → Object with state
* Table → Represented indirectly through shared forks and fork queue

## Deadlock Prevention

Five forks and five philosophers are initalized at the beginning of the program. When a philosopher stops eating at the end of a random period of time, they attempt to eat. If a fork is occupied, they **put down both forks** and go into waiting state. This ensures that no fork is held as a philosopher waits and that other philosophers can also eat in the meantime. 

## Starvation

The queues contained in the fork ensure that a each philosopher that has tried to eat and is now in waiting state is able to eat in a reasonable amount of time before they starve. This prevents starvation and ensures every philosopher eats. 

## Race Conditions

Forks use "lock" state that is checked whenever a philosopher tries to access it so only one philosopher can hold a fork at a time. This prevents multiple threads from accessing the same fork simultaneously.
