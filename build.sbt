name := "funky-java-tic-tac-toe"

version := "1.0"

mainClass := Some("com.ybrikman.funky.tictactoe.TicTacToe")

libraryDependencies ++= Seq(
  "org.pcollections" % "pcollections" % "2.1.2",
  "com.google.guava" % "guava" % "14.0",
  "junit" % "junit" % "4.11" % Test,
  "com.novocode" % "junit-interface" % "0.8" % "test->default"
)