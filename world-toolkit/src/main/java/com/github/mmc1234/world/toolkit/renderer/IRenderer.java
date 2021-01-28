package com.github.mmc1234.world.toolkit.renderer;

import com.github.mmc1234.world.toolkit.local.ILocalContext;

public interface IRenderer {
  public void create(ILocalContext context);
  public void render(ILocalContext context);
  public void close(ILocalContext context);
}
