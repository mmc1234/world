package com.github.mmc1234.world.window;

public interface Viewport {
  public void measureViewport(Viewport sup);

  public void getViewportRect(Dimension2D pos, Dimension2D size);
}