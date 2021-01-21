package com.github.mmc1234.world.leg2.toolkit.window.listener;

import com.github.mmc1234.world.leg2.toolkit.window.ActionType;
import com.github.mmc1234.world.leg2.toolkit.window.Window;

public interface IWindowListener {
  public void onButton(Window inWindow, ActionType type, double x, double y);
}
