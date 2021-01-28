package com.github.mmc1234.world.toolkit.renderer;

public interface IRenderBuffer {
  public int getLayer();
  public void put(float[] vertexs, Object data);
  public int getVertexCount();
}
