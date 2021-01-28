package com.github.mmc1234.world.toolkit.local;

import java.util.List;

import com.github.mmc1234.world.toolkit.enumerate.ActionType;
import com.github.mmc1234.world.toolkit.enumerate.ButtonType;
import com.google.common.collect.ImmutableList;

public interface IWindowEventHandler {
  public void handleKey(Window window, ActionType actionType, int key, int scancode, int mods);
  public void handleButton(Window window, ActionType actionType, ButtonType buttonType, int mods);
  public void handleAcceptFile(Window window, ImmutableList<String> files);
  public void handleMoveCursor(Window window, double x, double y);
  public void handleInput(Window window, char ch);
  public void handleInputMods(Window window, char ch, int mods);
  public void handleEnter(Window window, boolean entered);
  public void handleFrameBufferResize(Window window, int width, int height);
  public void handleResize(Window window, double width, double height);
  public void handleFocus(Window window, boolean focused);
  public void handleContentScale(Window window, double xscale, double yscale);
  public void handleClose(Window window);
  public void handleScroll(Window window, double offsetX, double offsetY);
  public void handleIconify(Window window, boolean iconified);
  public void handleRefresh(Window window);
  public void handleMaximize(Window window, boolean maximized);
}
