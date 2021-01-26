package com.github.mmc1234.world.toolkit.window.listener;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import com.github.mmc1234.world.toolkit.window.Window;

public interface IWindowResizeListener {
  public void onResize(Window window, int lastWidth, int lastHeight, int width, int height);
}
