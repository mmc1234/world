package com.github.mmc1234.world.leg2.toolkit.gui;

public class DemoBatch implements IBatch {

  @Override
  public IBatch flush() {
    System.out.println("generator dirty rect.");
    System.out.println("foreach independent.");
    System.out.println("foreach all and draw.");
    System.out.println("clean all.");
    return this;
  }

  @Override
  public IBatch gc() {
    System.out.println("run gc!");
    return this;
  }

  @Override
  public IBatch draw(View inView, Mesh2D inMesh2d) {
    System.out.println("draw mesh2d");
    return this;
  }
}
