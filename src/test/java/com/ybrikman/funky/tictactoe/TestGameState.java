package com.ybrikman.funky.tictactoe;

import org.junit.Test;

import static com.ybrikman.funky.tictactoe.BoardDsl.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestGameState {

  @Test
  public void testEmptyGameIsNotOver() {
    GameState gameState = new GameState();
    assertFalse(gameState.isGameOver());
  }

  @Test
  public void testSparseGameIsNotOver() {
    Board board = board(
        x(),     empty(), empty(),
        empty(), x(),     empty(),
        o(),     empty(), o()
    );

    GameState gameState = new GameState(board, Player.X);
    assertFalse(gameState.isGameOver());
  }

  @Test
  public void testWinningGameIsOver() {
    Board board = board(
        x(),     empty(), o(),
        empty(), x(),     empty(),
        o(),     empty(), x()
    );

    GameState gameState = new GameState(board, Player.X);
    assertTrue(gameState.isGameOver());
  }

  @Test
  public void testTieGameIsOver() {
    Board board = board(
        x(), o(), o(),
        o(), x(), x(),
        o(), x(), o()
    );

    GameState gameState = new GameState(board, Player.X);
    assertTrue(gameState.isGameOver());
  }

  @Test
  public void testNoWinnerInSparseGame() {
    Board board = board(
        x(),     empty(), empty(),
        empty(), x(),     empty(),
        o(),     empty(), o()
    );

    GameState gameState = new GameState(board, Player.X);
    assertEquals(empty(), gameState.findWinner());
  }

  @Test
  public void testNoWinnerInTie() {
    Board board = board(
        x(), o(), o(),
        o(), x(), x(),
        o(), x(), o()
    );

    GameState gameState = new GameState(board, Player.X);
    assertEquals(empty(), gameState.findWinner());
  }

  @Test
  public void testWinnerInWonGame() {
    Board board = board(
        x(),     empty(), o(),
        empty(), x(),     empty(),
        o(),     empty(), x()
    );

    GameState gameState = new GameState(board, Player.X);
    assertEquals(x(), gameState.findWinner());
  }

  @Test
  public void testMakeMoveEmptyGame() {
    GameState gameState = new GameState();

    Board expectedBoard = board(
        empty(), empty(), empty(),
        empty(), x(),     empty(),
        empty(), empty(), empty()
    );

    GameState expected = new GameState(expectedBoard, Player.O);

    assertEquals(expected, gameState.makeMove(4));
  }

  @Test
  public void testMakeMoveSparseGame() {
    Board originalBoard = board(
        x(),     empty(), o(),
        empty(), x(),     empty(),
        x(),     empty(), o()
    );

    GameState gameState = new GameState(originalBoard, Player.O);

    Board expectedBoard = board(
        x(),     empty(), o(),
        empty(), x(),     o(),
        x(),     empty(), o()
    );

    GameState expected = new GameState(expectedBoard, Player.X);

    assertEquals(expected, gameState.makeMove(5));
  }

  @Test(expected = IllegalMoveException.class)
  public void testMakeMoveOutOfBounds() {
    GameState gameState = new GameState();
    gameState.makeMove(12);
  }

  @Test(expected = IllegalMoveException.class)
  public void testMakeMoveTakenTile() {
    Board board = board(
        empty(), empty(), empty(),
        empty(), o(),     empty(),
        empty(), empty(), empty()
    );

    GameState gameState = new GameState(board, Player.X);
    gameState.makeMove(4);
  }

}
