package com.github.mmc1234.world.toolkit.gui;

public interface IBatch {
  public IBatch draw(AbstractView inView, IMesh2D inMesh);
  /**
   * 画一下背景
   * */
  public IBatch draw(AbstractView inView);
  public IBatch flush();
  public IBatch gc();
}
