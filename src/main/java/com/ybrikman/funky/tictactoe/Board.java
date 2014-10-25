package com.ybrikman.funky.tictactoe;

import com.google.common.base.Strings;
import org.pcollections.PVector;
import org.pcollections.TreePVector;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Board {
  private final PVector<Optional<Player>> board;
  private final int rowSize;
  private final List<List<Integer>> winningRowIndices;

  public Board() {
    this(BoardMathHelpers.defaultBoard());
  }

  public Board(List<Optional<Player>> board) {
    if (!BoardMathHelpers.isPerfectSquare(board.size())) {
      throw new IllegalBoardException("The size of the board must be a perfect square (e.g. 9, 16, 25)");
    }

    this.rowSize = (int) Math.sqrt(board.size());
    this.board = TreePVector.from(board);
    this.winningRowIndices = BoardMathHelpers.calculateWinningRowIndices(board.size(), rowSize);
  }

  public Optional<Player> findWinner() {
    return winningRowIndices
        .stream()
        .map(this::findWinningPlayerInRow)
        .filter(Optional::isPresent)
        .findFirst()
        .orElseGet(Optional::empty);
  }

  public boolean isFull() {
    return board.stream().allMatch(Optional::isPresent);
  }

  public Board makeMove(Player player, int index) {
    validateMove(index);
    return new Board(board.with(index, Optional.of(player)));
  }

  @Override
  public int hashCode() {
    return board.hashCode();
  }

  @Override
  public boolean equals(Object other) {
    return other instanceof Board && board.equals(((Board) other).board);

  }

  @Override
  public String toString() {
    final int maxColumnSeparatorLength = BoardMathHelpers.numberOfDigits(board.size());
    final String columnSeparator = " ";
    final String rowSeparator = "\n";

    return IntStream
        .range(0, board.size())
        .mapToObj(i -> {
          final String label = board.get(i).map(Player::name).orElseGet(() -> String.valueOf(i));

          final boolean isEndOfRow = i > 0 && (i + 1) % rowSize == 0;
          final int columnSeparatorLength = maxColumnSeparatorLength - BoardMathHelpers.numberOfDigits(i) + 1;
          final String separator = isEndOfRow ? rowSeparator : Strings.repeat(columnSeparator, columnSeparatorLength);

          return label + separator;
        })
        .collect(Collectors.joining());
  }

  private void validateMove(int index) {
    if (index < 0 || index >= board.size()) {
      throw new IllegalMoveException("You may only enter values between 0 and " + (board.size() - 1) + ".");
    }

    if (board.get(index).isPresent()) {
      throw new IllegalMoveException("Board slot " + index + " is already taken.");
    }
  }

  private Optional<Player> findWinningPlayerInRow(List<Integer> indices) {
    final Stream<Optional<Player>> playersInRow = indices.stream().map(i -> board.get(i));
    return playersInRow.reduce((a, b) -> a.equals(b) ? a : Optional.empty()).orElseGet(Optional::empty);
  }
}
