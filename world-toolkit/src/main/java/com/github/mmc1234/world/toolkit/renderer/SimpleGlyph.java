package com.github.mmc1234.world.toolkit.renderer;

import java.util.TreeSet;

import com.github.mmc1234.world.toolkit.gui.View;

public abstract class SimpleGlyph implements IGlyph {
  protected final IRenderBuffer[] trackArray = new IRenderBuffer[64];
  protected int currentLayer = 0, color = 0x000000ff;
  protected int[] textures;
  protected float[] texCoords;
  protected float[] vertex;
  protected float[] clip;

  @Override
  public void color(int color) {
    this.color = color;
  }

  @Override
  public void texCoord(float[] texcoords) {
    this.texCoords = texcoords;
  }

  @Override
  public void texture(int[] textures) {
    this.textures = textures;
  }

  @Override
  public void vertex(float[] vertexs) {
  }

  @Override
  public void clip(int x, int y, int w, int h) {
  }

  @Override
  public void flush() {
  }

  @Override
  public void enter(View view) {
  }

  @Override
  public void exit(View view) {
  }


}
