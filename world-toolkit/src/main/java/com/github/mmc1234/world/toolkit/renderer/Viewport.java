package com.github.mmc1234.world.toolkit.renderer;

import org.lwjgl.opengl.GL30;

public class Viewport {
  public int x, y, w, h;
  public IWindowResizeListener resizeListener;
  protected void onWindowResize(Window window, int x, int y, int w, int h) {
	  if(resizeListener != null) {
	    resizeListener.onResize(window, x, y, w, h);
	  }
  }
  
  public void apply() {
    GL30.glViewport(x, y, w, h);
  }
}