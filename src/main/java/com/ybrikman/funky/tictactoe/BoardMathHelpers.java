package com.ybrikman.funky.tictactoe;

import com.google.common.collect.ImmutableList;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BoardMathHelpers {
  private static final int DEFAULT_BOARD_SIZE = 9;

  public static boolean isPerfectSquare(int number) {
    return Math.sqrt(number) % 1 == 0;
  }

  public static List<Optional<Player>> defaultBoard() {
    return Stream
        .generate(Optional::<Player>empty)
        .limit(DEFAULT_BOARD_SIZE)
        .collect(Collectors.toList());
  }

  public static List<List<Integer>> calculateWinningRowIndices(int boardSize, int rowSize) {
    final Collection<List<Integer>> columnIndices = IntStream
        .range(0, boardSize)
        .boxed()
        .collect(Collectors.groupingBy(i -> i % rowSize))
        .values();

    final Collection<List<Integer>> rowIndices = IntStream
        .range(0, boardSize)
        .boxed()
        .collect(Collectors.groupingBy(i -> i / rowSize))
        .values();

    final List<Integer> leftToRightDiagonalIndices = IntStream
        .rangeClosed(0, rowSize - 1)
        .map(i -> i * (rowSize + 1))
        .boxed()
        .collect(Collectors.toList());

    final List<Integer> rightToLeftDiagonalIndices = IntStream
        .rangeClosed(1, rowSize)
        .map(i -> i * (rowSize - 1))
        .boxed()
        .collect(Collectors.toList());

    return ImmutableList
        .<List<Integer>>builder()
        .addAll(columnIndices)
        .addAll(rowIndices)
        .add(leftToRightDiagonalIndices)
        .add(rightToLeftDiagonalIndices)
        .build();
  }

  public static int numberOfDigits(int number) {
    return String.valueOf(number).length();
  }
}
