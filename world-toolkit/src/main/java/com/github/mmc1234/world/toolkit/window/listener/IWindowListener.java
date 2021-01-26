package com.github.mmc1234.world.toolkit.window.listener;

import com.github.mmc1234.world.toolkit.window.ActionType;
import com.github.mmc1234.world.toolkit.window.Window;

public interface IWindowListener {
  public void onButton(Window inWindow, ActionType type, double x, double y);
}
