package com.ybrikman.funky.tictactoe;

import org.junit.Test;

import static com.ybrikman.funky.tictactoe.BoardDsl.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestBoard {

  @Test
  public void testEmptyBoardHasNoWinner() {
    assertEquals(empty(), new Board().findWinner());
  }

  @Test
  public void testSparseBoardHasNoWinner() {
    Board board = board(
        x(),     empty(), empty(),
        empty(), o(),     empty(),
        empty(), empty(), x()
    );
    assertEquals(empty(), board.findWinner());
  }

  @Test
  public void testSparseBoardLargeHasNoWinner() {
    Board board = board(
        x(),     empty(), empty(), empty(), empty(),
        empty(), o(),     empty(), x(),     o(),
        empty(), empty(), x(),     empty(), empty(),
        empty(), empty(), x(),     empty(), empty(),
        empty(), empty(), x(),     empty(), o()
    );
    assertEquals(empty(), board.findWinner());
  }

  @Test
  public void testFullBoardHasNoWinner() {
    Board board = board(
        x(), x(), o(),
        o(), o(), x(),
        x(), o(), x()
    );
    assertEquals(empty(), board.findWinner());
  }

  @Test
  public void testWinnerInRow() {
    Board board = board(
        x(),     x(),     x(),
        empty(), empty(), empty(),
        empty(), empty(), empty()
    );
    assertEquals(x(), board.findWinner());
  }

  @Test
  public void testWinnerInColumn() {
    Board board = board(
        empty(), o(), empty(),
        empty(), o(), empty(),
        empty(), o(), empty()
    );
    assertEquals(o(), board.findWinner());
  }

  @Test
  public void testWinnerInColumnLargeBoard() {
    Board board = board(
        x(),     empty(), empty(), empty(), o(),
        empty(), o(),     empty(), x(),     o(),
        empty(), empty(), x(),     empty(), o(),
        empty(), empty(), x(),     empty(), o(),
        empty(), empty(), x(),     empty(), o()
    );
    assertEquals(o(), board.findWinner());
  }

  @Test
  public void testWinnerInLeftToRightDiagonal() {
    Board board = board(
        x(),     o(), empty(),
        empty(), x(), empty(),
        empty(), o(), x()
    );
    assertEquals(x(), board.findWinner());
  }

  @Test
  public void testWinnerInRightToLeftDiagonal() {
    Board board = board(
        x(),     o(), o(),
        empty(), o(), empty(),
        x(),     o(), x()
    );
    assertEquals(o(), board.findWinner());
  }

  @Test
  public void testWinnerInRightToLeftDiagonalLargeBoard() {
    Board board = board(
        x(),     empty(), empty(), empty(), o(),
        empty(), o(),     empty(), o(),     empty(),
        empty(), empty(), o(),     empty(), empty(),
        empty(), o(),     x(),     empty(), empty(),
        o(),     empty(), x(),     empty(), empty()
    );
    assertEquals(o(), board.findWinner());
  }

  @Test
  public void testEmptyBoardIsNotFull() {
    Board board = new Board();
    assertFalse(board.isFull());
  }

  @Test
  public void testSparseBoardIsNotFull() {
    Board board = board(
        x(),     empty(), o(),
        empty(), empty(), empty(),
        empty(), empty(), empty()
    );
    assertFalse(board.isFull());
  }

  @Test
  public void testSparseWinningBoardIsNotFull() {
    Board board = board(
        x(),     x(),     x(),
        empty(), o(),     empty(),
        o(),     empty(), empty()
    );
    assertFalse(board.isFull());
  }

  @Test
  public void testFullBoardIsFull() {
    Board board = board(
        x(), o(), x(),
        x(), o(), x(),
        o(), x(), o()
    );
    assertTrue(board.isFull());
  }

  @Test(expected = IllegalMoveException.class)
  public void testMakeMoveOutOfBoundsPositive() throws IllegalMoveException {
    Board board = board(
        empty(), empty(), empty(),
        empty(), empty(), empty(),
        empty(), empty(), empty()
    );
    board.makeMove(Player.X, 10);
  }

  @Test(expected = IllegalMoveException.class)
  public void testMakeMoveOutOfBoundsNegative() throws IllegalMoveException {
    Board board = new Board();
    board.makeMove(Player.X, -1);
  }

  @Test(expected = IllegalMoveException.class)
  public void testMakeMoveSpotAlreadyTaken() throws IllegalMoveException {
    Board board = board(
        x(),     empty(), empty(),
        empty(), empty(), empty(),
        empty(), empty(), empty()
    );
    board.makeMove(Player.X, 0);
  }

  @Test
  public void testMakeMoveEmptyBoard() throws IllegalMoveException {
    Board originalBoard = board(
        empty(), empty(), empty(),
        empty(), empty(), empty(),
        empty(), empty(), empty()
    );

    Board actualBoard = originalBoard.makeMove(Player.X, 0);

    Board expectedBoard = board(
        x(),     empty(), empty(),
        empty(), empty(), empty(),
        empty(), empty(), empty()
    );

    assertEquals(expectedBoard, actualBoard);
  }

  @Test
  public void testMakeMoveSparseBoard() throws IllegalMoveException {
    Board originalBoard = board(
        x(),     o(),     empty(),
        empty(), x(),     empty(),
        x(),     empty(), o()
    );

    Board actualBoard = originalBoard.makeMove(Player.O, 2);

    Board expectedBoard = board(
        x(),     o(),     o(),
        empty(), x(),     empty(),
        x(),     empty(), o()
    );

    assertEquals(expectedBoard, actualBoard);
  }

  @Test(expected = IllegalBoardException.class)
  public void testNonSquareBoard() {
    board(
        x(), empty(), empty()
    );
  }
}
