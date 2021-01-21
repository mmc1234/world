package com.github.mmc1234.world.leg2.toolkit.window;

import com.github.mmc1234.world.leg2.toolkit.gui.IBatch;

public interface ILocalContext {
  public IBatch getBatch();
  public Window getCurrentWindow();
  public void loadGL();
  public void make(Window window);
  public void onWindowDestry(Window window);
  public void pollEvents();
  public void waitEvents();
  public void waitEvents(double timeout);
}
