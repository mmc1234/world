package com.github.mmc1234.world.toolkit.renderer;

import com.github.mmc1234.world.toolkit.bitmap.IBitmap;
import com.github.mmc1234.world.toolkit.gui.View;

public interface IGlyph {
  public void enter(View view);

  public void exit(View view);
  
  public void color(int color);

  public void texCoord(float[] texcoords);
  
  public void texture(int[] texture);

  public void vertex(float[] vertexs);

  public void clip(int x, int y, int w, int h);
  
  public void flush();
}
