package com.ybrikman.funky.tictactoe;

public class IllegalBoardException extends RuntimeException {
  public IllegalBoardException(String message) {
    super(message);
  }
}
