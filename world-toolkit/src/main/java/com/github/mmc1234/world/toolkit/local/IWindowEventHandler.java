package com.github.mmc1234.world.toolkit.local;

public interface IWindowEventHandler {
  public void handleKey(Window window);
  public void handleButton(Window window);
  public void handleAcceptFile(Window window);
  public void handleMove(Window window);
  public void handleInput(Window window);
  public void handleInputMods(Window window);
  public void handleEnter(Window window);
  public void handleFrameBufferResize(Window window);
  public void handleResize(Window window);
  public void handleJoystick(Window window);
  public void handleFocus(Window window);
  public void handleMonitor(Window window);
  public void handleContentScale(Window window);
  public void handleClose(Window window);
  public void handleScroll(Window window);
  public void handleMinimize(Window window);
  public void handleRefresh(Window window);
  public void handleMaximize(Window window);
}
