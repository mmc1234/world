package com.github.mmc1234.world.toolkit.window;

import com.github.mmc1234.world.message.CancelableEventBus;
import com.github.mmc1234.world.toolkit.gui.render.AbstractUIRenderer;

public interface ILocalContext {
  public AbstractUIRenderer getRenderer();
  public Window getCurrentWindow();
  public CancelableEventBus getEventBus();
  public void loadGL();
  public void make(Window window);
  public void onWindowDestry(Window window);
  public void pollEvents();
  public void waitEvents();
  public void waitEvents(double timeout);
}
