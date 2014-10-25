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

To run the tic-tac-toe game:

1. Make sure you have [sbt](http://www.scala-sbt.org/) installed on your system
2. `sbt run`

To compile, test, etc:

1. `sbt`
2. This starts the interactive prompt where you can use commands like `compile` and `test`.

# Functional programming

[Java 8](http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html) introduced a number of new features 
to Java, including [lambda expressions](http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html) and 
[streams](http://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html) that open the door to a more
functional style of programming. I wanted to see if it was possible to write a nearly pure functional app, subject to
the following constraints:

* Only immutable data: just about every variable and field is marked as final and I only use immutable data structures.
* Persistent data structures: there is no need to worry about performance with a tic-tac-toe game, but for fun, I 
experimented with [http://pcollections.org/](pcollections), which provides 
[persistent](http://en.wikipedia.org/wiki/Persistent_data_structure) data structures that are compatible with the 
[Java collections framework](http://java.sun.com/javase/6/docs/technotes/guides/collections/index.html).
* All side effects contained in one class: the `TicTacToe` class is the only one that performs any I/O.

# Navigating the code

Here are the main highlights of the code:

* `Board.java`: an immutable representation of an n x n tic-tac-toe board that can be filled with X's and O's. 
* `GameState.java`: an immutable representation of a game, including a `Board` and the current player's turn. Has a
`makeMove` method that places a new X or O on the board and a `isGameOver` method to determine if there is a winner or
a tie.
* `TicTacToe.java`: this class contains the `main` method and is the only one that performs I/O. It starts with an empty
`GameState` and uses `Stream.iterate` and `GameState.makeMove` to generate new `GameStates` based on user input until
`Gametate.isGameOver` is true.
 
# License
 
This code is available under the MIT license. See the LICENSE file for more info.