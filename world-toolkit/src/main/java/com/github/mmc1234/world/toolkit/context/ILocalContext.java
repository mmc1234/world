package com.github.mmc1234.world.toolkit.context;

import com.github.mmc1234.world.message.CancelableEventBus;
import com.github.mmc1234.world.toolkit.bitmap.IBitmapManager;
import com.github.mmc1234.world.toolkit.renderer.Renderer;
import com.github.mmc1234.world.toolkit.window.Window;

public interface ILocalContext {
  public Renderer getRenderer();
  public Window getCurrentWindow();
  public CancelableEventBus getEventBus();
  public IBitmapManager getBitmapManager();
  public void loadGL();
  public void make(Window window);
  public void onWindowDestry(Window window);
  public void pollEvents();
  public void waitEvents();
  public void waitEvents(double timeout);
}
