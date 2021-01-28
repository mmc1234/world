package com.github.mmc1234.world.toolkit.local;

import com.github.mmc1234.world.message.CancelableEventBus;
import com.github.mmc1234.world.toolkit.bitmap.IBitmapManager;
import com.github.mmc1234.world.toolkit.renderer.IBatch;
import com.github.mmc1234.world.toolkit.renderer.IRenderer;

public interface ILocalContext {
  public IBatch getBatch();
  public IRenderer getRenderer();
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
