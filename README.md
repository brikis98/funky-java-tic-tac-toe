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

* To run the tic-tac-toe game: `sbt run`
* To run the tests: `sbt test`

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

# Thoughts

Overall, Java 8 is a massive step forward for the language, but I'm not sure it's enough. It took much longer to write 
this app in a functional style than I expected due to lots of annoyances:

* The higher order functions in the `Stream API`, such as `map`, `reduce`, and `filter`, are all designed to work in 
parallel. This may be useful for performance reasons, but to support parallel operation, the signatures of some of these
methods are sometimes a little different than the equivalent methods in all other programming languages (e.g. there is 
no `foldLeft` or `foldRight` because a parallel operation doesn't go linearly from one side to the other).
* The need to convert Java collections to a `Stream` (e.g. by calling `.stream()`) and back (e.g. by calling 
`.collect(Collectors.toList())`) in order to use higher order functions is verbose. I wish I could just call `map` 
directly on a `List` or `Set`.
* Some code, such as determining if a `Board` had a winner, was much harder to do in an immutable fashion using the 
`Stream` API than with good old fashioned `for`, `break`, and `return`. If anyone knows a cleaner pattern for 
implementing `Board.findWinner()`, let me know.
* It is tedious and verbose to mark every variable as `final`. I wish immutable was the default or there was a more
concise construct, such as using `val` vs `var` in Scala.
* It's not clear why numeric streams need special treatment, such as the `IntStream` and `DoubleStream` classes and the
`mapToInt` and `mapToDouble` higher order functions.
* I sorely missed Scala's [sequence comprehensions](http://docs.scala-lang.org/tutorials/tour/sequence-comprehensions.html)
when I had to chain many `map` and `flatMap` calls together. 
* Most collection APIs in Java do not provide immutability guarantees, not even the Java 8 `Stream` APIs, so you have 
to be extra careful to use persistent collections or Guava's immutable collections.
* While some Java code uses `Optional`, most still uses `null`. And, of course, you could even set an `Optional` 
variable to null, so it's still very easy to hit a `NullPointerException`.  
* The compile errors, at least in IntelliJ, were less helpful than usual. Many of them seemed to be caused by a 
minor error in a type signature causing type inference to fail and return a less-than-helpful "cyclic reference" error.
 
# License
 
This code is available under the MIT license. See the LICENSE file for more info.