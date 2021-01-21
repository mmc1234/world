package com.github.mmc1234.world.leg2.toolkit.gui;

public interface IBatch {
  public IBatch draw(View inView, Mesh2D inMesh2d);
  public IBatch flush();
  public IBatch gc();
}
