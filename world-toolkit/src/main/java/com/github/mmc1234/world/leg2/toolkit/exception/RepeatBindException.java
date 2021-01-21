package com.github.mmc1234.world.leg2.toolkit.exception;

public class RepeatBindException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = -688470819267443109L;
  public RepeatBindException(int id) {
    super("Repeat ID:"+id);
  }
}
