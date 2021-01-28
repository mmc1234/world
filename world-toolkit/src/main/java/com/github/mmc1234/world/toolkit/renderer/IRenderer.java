package com.github.mmc1234.world.toolkit.renderer;

import com.github.mmc1234.world.toolkit.local.ILocalContext;
import com.github.mmc1234.world.toolkit.local.Window;

public interface IRenderer {
  public void create(Window window);
  public void render(Window window);
  public void close(Window window);
}
