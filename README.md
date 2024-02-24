# PA2_COP4520
# Documentation

## Problem 1

### Objective
The objective was to design a solution to guarantee each guest at the Minotaur's birthday party can enter the labyrinth at least once, eat a cupcake on their first visit, and then not be able to after.

### Solution Overview
The solution utilizes Java threads to simulate guests and a cupcake within a synchronized `Labyrinth` class to manage the guest's ability to access the Labyrinth. 

### Proof of Correctness
- **Mutual Exclusion**: Ensured by synchronizing access to the labyrinth, allowing only one guest to enter and interact with the cupcake one at a one time.
- **Fairness**: Achieved through a array that tracks whether the guest has eaten a cupcake - which ensure each guest has equal oppurtunity to eat a cupcake.
- **No Deadlock/Livelock**: The algorithm's straightforward logic prevent general deadlocks.

### Efficiency Analysis
- **Time Complexity**: Mostly dictated by the number of guest attempts to enter the labyrinth
- **Space Complexity**: Minimal overhead, with most of the structures being an array tracking cupcake consumption and atomic variables for state management.

### Experimental Evaluation
Experimentation involved running the program with varying numbers of guests to observe performance. 
## Problem 2: Viewing the Minotaur's Favorite Crystal Vase

### Objective
The objective for the vase problem was to evelop a strategy that would allow guests to view the Minotaur's crystal vase one at a time.

### Solution Overview
The chosen queue system uses a semaphore to control guest access to the vase, simulating a queue.

### Proof of Correctness
- **Mutual Exclusion**: Guaranteed by the semaphore, which ensures only one guest can access the critical section vase viewing at a time.
- **Fairness**: The semaphore's queue mechanism ensures guests view the vase in the order they request access, providing a fair viewing opportunity to all of the guests.
- **No Deadlock/Livelock**: The simple control flow and semaphore's inherent properties prevent deadlock.

### Efficiency Analysis
- **Time Complexity**: Depends on the semaphore's and the number of guests attempting to view the vase, with each viewing and waiting operation handled efficiently - and equally depending on when they requested access.
- **Space Complexity**: Primarily involves the semaphore and a boolean array for tracking, and counting.

### Experimental Evaluation
Testing with various guest counts demonstrated the system's effectiveness in managing vase viewing without crowding.
