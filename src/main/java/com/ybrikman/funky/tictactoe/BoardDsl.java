package com.ybrikman.funky.tictactoe;

import com.google.common.collect.ImmutableList;

import java.util.Optional;

public class BoardDsl {
  
  public static Optional<Player> x() {
    return Optional.of(Player.X);
  }

  public static Optional<Player> o() {
    return Optional.of(Player.O);
  }

  public static Optional<Player> empty() {
    return Optional.empty();
  }

  @SafeVarargs
  public static Board board(Optional<Player> ... players) {
    return new Board(ImmutableList.<Optional<Player>>builder().add(players).build());
  }
}
