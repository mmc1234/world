package com.github.mmc1234.world.toolkit.window;

import com.github.mmc1234.world.message.CancelableEventBus;
import com.github.mmc1234.world.toolkit.renderer.UIRenderer;

public interface ILocalContext {
  public UIRenderer getRenderer();
  public Window getCurrentWindow();
  public CancelableEventBus getEventBus();
  public void loadGL();
  public void make(Window window);
  public void onWindowDestry(Window window);
  public void pollEvents();
  public void waitEvents();
  public void waitEvents(double timeout);
}
