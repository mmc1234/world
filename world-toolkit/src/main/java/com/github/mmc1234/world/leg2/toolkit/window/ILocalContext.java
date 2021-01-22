package com.github.mmc1234.world.leg2.toolkit.window;

import com.github.mmc1234.world.leg2.toolkit.gui.IBatch;
import com.google.common.eventbus.EventBus;

public interface ILocalContext {
  public IBatch getBatch();
  public Window getCurrentWindow();
  public EventBus getEventBus();
  public void loadGL();
  public void make(Window window);
  public void onWindowDestry(Window window);
  public void pollEvents();
  public void waitEvents();
  public void waitEvents(double timeout);
}
