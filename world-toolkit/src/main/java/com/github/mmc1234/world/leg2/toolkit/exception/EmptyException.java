package com.github.mmc1234.world.leg2.toolkit.exception;

public class EmptyException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 5406418244792909499L;
  public EmptyException() {
    this("Empty object");
  }
  public EmptyException(String message) {
    super(message);
  }
}
