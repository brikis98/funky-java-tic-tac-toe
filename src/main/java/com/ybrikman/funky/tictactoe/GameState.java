package com.ybrikman.funky.tictactoe;

import java.util.Optional;

public class GameState {
  private static final Player DEFAULT_FIRST_PLAYER = Player.X;

  private final Board board;
  private final Player currentPlayer;

  public GameState() {
    this(new Board(), DEFAULT_FIRST_PLAYER);
  }

  public GameState(Board board, Player currentPlayer) {
    this.board = board;
    this.currentPlayer = currentPlayer;
  }

  public GameState makeMove(int playerSelection) {
    final Board nextBoard = board.makeMove(currentPlayer, playerSelection);
    final Player nextPlayer = currentPlayer == Player.X ? Player.O : Player.X;
    return new GameState(nextBoard, nextPlayer);
  }

  public boolean isGameOver() {
    return board.findWinner().isPresent() || board.isFull();
  }

  public Optional<Player> findWinner() {
    return board.findWinner();
  }

  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof GameState) {
      GameState otherGameState = (GameState) other;
      return board.equals(otherGameState.board) && currentPlayer == otherGameState.currentPlayer;
    }

    return false;
  }

  @Override
  public int hashCode() {
    int result = board != null ? board.hashCode() : 0;
    result = 31 * result + (currentPlayer != null ? currentPlayer.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return board.toString();
  }
}