package com.github.mmc1234.world.toolkit.renderer;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public interface IWindowResizeListener {
  public void onResize(Window window, int lastWidth, int lastHeight, int width, int height);
}
