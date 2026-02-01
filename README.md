
# Dining Philosophers – Java Threads

## Team Members

* Jimena Luna
* Grace Odondi
* Destiny Okonkwo
 ·

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

Each philosopher is implemented as a thread. Philosophers alternate between thinking and eating. Forks are shared objects placed between philosophers. A semaphore acts as a waiter that limits how many philosophers may attempt to eat at the same time.

## Representation

* Philosopher → Thread (Runnable)
* Fork → Object with a ReentrantLock
* Table → Represented indirectly through shared forks and the semaphore

## Deadlock Prevention

A semaphore initialized to N−1 is used so that at most N−1 philosophers can try to pick up forks at once. This prevents the circular waiting condition that causes deadlock.

## Starvation

The semaphore and fork locks are created as fair, meaning waiting threads are served in order. This greatly reduces the chance of starvation.

## Race Conditions

Forks use locks so only one philosopher can hold a fork at a time. This prevents multiple threads from accessing the same fork simultaneously.

## Notes

Deadlock is not possible with this design. Starvation is extremely unlikely under fair scheduling.


