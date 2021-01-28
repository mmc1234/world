package com.github.mmc1234.world.toolkit.renderer;

import java.util.ArrayList;
import java.util.List;

public class ListRenderBuffer implements IRenderBuffer {
  private int layer;
  private List<float[]> vl = new ArrayList<>();
  private List<Object> dl = new ArrayList<>();
  
  public ListRenderBuffer(int layer) {
    this.layer = layer;
  }
  
  @Override
  public int getLayer() {
    return layer;
  }

  @Override
  public void add(float[] vertexs, Object data) {
    vl.add(vertexs);
    dl.add(data);
  }

  @Override
  public List<float[]> getVertexs() {
    return vl;
  }

  @Override
  public List<Object> getDatas() {
    return dl;
  }

  @Override
  public int getVertexCount() {
    int size = 0;
    for(float[] vs: vl) {
      size+=(vs.length)/4;
    }
    return size;
  }

  @Override
  public void clear() {
    vl.clear();
    dl.clear();
  }

}
