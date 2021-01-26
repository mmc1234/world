package com.github.mmc1234.world.toolkit.exception;

public class UnableBindException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = -7451411416380845785L;

  public UnableBindException(int target, int current, int buffer) {
    super("CurrentBind:"+current+", TargetBind:"+target+", Buffer:"+buffer);
  }
  public UnableBindException(int target, int current) {
    super("CurrentBind:"+current+", TargetBind:"+target);
  }
}
