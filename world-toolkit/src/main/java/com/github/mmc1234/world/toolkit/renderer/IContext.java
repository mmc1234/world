package com.github.mmc1234.world.toolkit.renderer;

import java.util.Map;

import com.github.mmc1234.world.toolkit.renderer.IRenderer;

public interface IContext {
  public Window getWindow();
  public IRenderer getUIRenderer();
}