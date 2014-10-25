package com.ybrikman.funky.tictactoe;

public class IllegalMoveException extends RuntimeException {
  public IllegalMoveException(String message) {
    super(message);
  }
}