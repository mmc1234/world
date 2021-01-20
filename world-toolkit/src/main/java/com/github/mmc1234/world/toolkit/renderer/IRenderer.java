package com.github.mmc1234.world.toolkit.renderer;

public interface IRenderer {
  public void onStart(IContext ctx);
  public void onRender(IContext ctx);
  public void onStop(IContext ctx);
}
