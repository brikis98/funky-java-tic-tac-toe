# Funky Java Tic Tac Toe

This repository contains a text-based tic-tac-toe game written as a way to experiment with functional programming and
persistent data structures in Java 8.
 
```
0 1 2
3 4 5
6 7 8

Player X, it's your turn, enter a number: 4

0 1 2
3 X 5
6 7 8

Player O, it's your turn, enter a number: 
```

# Quick start

Make sure you have [sbt](http://www.scala-sbt.org/) and 
[Java 8](http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html) installed on your system.

To run the tic-tac-toe game: `sbt run`
To run the tests: `sbt test`

See the [http://www.scala-sbt.org/0.13/tutorial/Running.html](SBT documentation) for other commands.

# Functional programming

[Java 8](http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html) introduced a number of new features 
to Java, including [lambda expressions](http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html) and 
[streams](http://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html) that open the door to a more
functional style of programming. I wrote this app to see what it was like to write a nearly pure functional app, subject 
to the following constraints:

* **Immutable data**: just about every variable and field is marked as final. All data structures are immutable.
* **Persistent data structures**: there is no need to worry about performance with a tic-tac-toe game, but for fun, I 
experimented with the [persistent data structures](http://en.wikipedia.org/wiki/Persistent_data_structure) from the 
[pcollections](http://pcollections.org/) library.
* **Pure functions**: the functions in the `TicTacToe` class are the only ones that performs I/O. All other functions 
are [pure](http://en.wikipedia.org/wiki/Pure_function).

# Navigating the code

Here are the main highlights of the code:

* `Board.java`: an immutable representation of an n x n tic-tac-toe board that can be filled with X's and O's. 
* `GameState.java`: an immutable representation of a game, including the `Board` and the current player's turn. The
`makeMove` method places a new X or O on the board and returns a new `GameState`. The `isGameOver` returns true if there 
is a winner or a tie. The `findWinner` method returns the winner of the game or `Optional.empty` if there is no winner.
* `TicTacToe.java`: this class contains the `main` method and is the only one that performs I/O. It starts with an empty
`GameState` and uses `Stream.iterate` and `GameState.makeMove` to generate new `GameStates` based on user input until
`Gametate.isGameOver` is true.
 
# License
 
This code is available under the MIT license. See the LICENSE file for more info.