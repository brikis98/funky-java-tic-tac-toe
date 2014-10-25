package com.ybrikman.funky.tictactoe;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static com.ybrikman.funky.tictactoe.BoardDsl.*;

public class TesBoardMathHelpers {

  @Test
  public void testIsPerfectSquareWithOddSquare() {
    assertTrue(BoardMathHelpers.isPerfectSquare(9));
  }

  @Test
  public void testIsPerfectSquareWithEvenSquare() {
    assertTrue(BoardMathHelpers.isPerfectSquare(64));
  }

  @Test
  public void testIsPerfectSquareWithOddNonSquare() {
    assertFalse(BoardMathHelpers.isPerfectSquare(37));
  }

  @Test
  public void testIsPerfectSquareWithEvenNonSquare() {
    assertFalse(BoardMathHelpers.isPerfectSquare(62));
  }

  @Test
  public void testDefaultBoard() {
    Board expected = board(
      empty(), empty(), empty(),
      empty(), empty(), empty(),
      empty(), empty(), empty()
    );

    assertEquals(expected, new Board(BoardMathHelpers.defaultBoard()));
  }

  @Test
  public void testCalculateWinningRowIndices() {
    List<List<Integer>> expected = ImmutableList.of(
        ImmutableList.of(0, 1, 2),
        ImmutableList.of(3, 4, 5),
        ImmutableList.of(6, 7, 8),
        ImmutableList.of(0, 3, 6),
        ImmutableList.of(1, 4, 7),
        ImmutableList.of(2, 5, 8),
        ImmutableList.of(0, 4, 8),
        ImmutableList.of(2, 4, 6)
    );
    List<List<Integer>> actual = BoardMathHelpers.calculateWinningRowIndices(9, 3);

    assertListsEqual(expected, actual);
  }

  @Test
  public void testCalculateWinningRowIndicesLargeBoard() {
    List<List<Integer>> expected = ImmutableList.of(
        ImmutableList.of(0,  1,  2,  3,  4),
        ImmutableList.of(5,  6,  7,  8,  9),
        ImmutableList.of(10, 11, 12, 13, 14),
        ImmutableList.of(15, 16, 17, 18, 19),
        ImmutableList.of(20, 21, 22, 23, 24),
        ImmutableList.of(0,  5,  10, 15, 20),
        ImmutableList.of(1,  6,  11, 16, 21),
        ImmutableList.of(2,  7,  12, 17, 22),
        ImmutableList.of(3,  8,  13, 18, 23),
        ImmutableList.of(4,  9,  14, 19, 24),
        ImmutableList.of(0,  6,  12, 18, 24),
        ImmutableList.of(4,  8,  12, 16, 20)
    );
    List<List<Integer>> actual = BoardMathHelpers.calculateWinningRowIndices(25, 5);

    assertListsEqual(expected, actual);
  }

  private <T> void assertListsEqual(List<T> first, List<T> second) {
    if (first == null) {
      assertNull(second);
    } else {
      assertNotNull(second);
      assertEquals(first.size(), second.size());
      for (T element : first) {
        assertTrue(second.contains(element));
      }
    }
  }
}
