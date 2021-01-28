package com.github.mmc1234.world.toolkit.local;

public interface IWindowErrorHandler {
  public void handleError(String message);
  public void handleException(Throwable e);
}
