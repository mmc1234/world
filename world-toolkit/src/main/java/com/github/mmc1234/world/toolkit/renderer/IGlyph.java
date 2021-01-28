package com.github.mmc1234.world.toolkit.renderer;

public interface IGlyph {
  public void begin();

  public void end();
  
  public void color(int color);

  public void texCoord(float[] texcoords);

  public void vertex(float[] vertexs);

  public void clip(int x, int y, int w, int h);
  
  public void flush();
}
