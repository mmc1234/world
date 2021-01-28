package com.github.mmc1234.world.toolkit.renderer;

public class DebugGlyph implements IGlyph {

  @Override
  public void color(int color) {
    System.out.println("Color");
  }

  @Override
  public void flush() {
    System.out.println("Flush");
  }

  @Override
  public void texCoord(float[] texcoords) {
    System.out.println("TexCoord");
  }

  @Override
  public void vertex(float[] vertexs) {
    System.out.println("Vertex");
  }

  @Override
  public void clip(int x, int y, int w, int h) {
    System.out.println("Clip");
  }

  @Override
  public void begin() {
  }

  @Override
  public void end() {
  }

}
