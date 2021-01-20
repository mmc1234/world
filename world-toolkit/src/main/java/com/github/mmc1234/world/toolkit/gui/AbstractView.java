package com.github.mmc1234.world.toolkit.gui;

import java.awt.geom.Dimension2D;

import com.github.mmc1234.world.toolkit.renderer.IContext;

import lombok.NonNull;

public abstract class AbstractView {
  public double x, y, width, height;
  public SizeType sizeType = SizeType.Auto;
  protected double actualX, actualY, actualWidth, actualHeight;
  String background = null;

  public abstract void onPreRender(IContext ctx);

  public void onRender(IContext ctx) {
    ctx.getBatch().draw(this);
  }
  public void calculateSize(@NonNull Dimension2D inSize) {}
  public void calculatePosition(@NonNull Dimension2D inPos) {}
}
