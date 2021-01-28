package com.github.mmc1234.world.toolkit.renderer;

import java.util.Collection;
import java.util.List;

public interface IRenderBuffer {
  public int getLayer();
  public void add(float[] vertexs, Object data);
  public List<float[]> getVertexs();
  public List<Object> getDatas();
  public int getVertexCount();
  public void clear();
}
