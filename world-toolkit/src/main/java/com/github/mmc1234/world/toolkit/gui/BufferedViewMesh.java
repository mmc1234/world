package com.github.mmc1234.world.toolkit.gui;

import com.github.mmc1234.world.toolkit.bitmap.IBitmap;

public class BufferedViewMesh extends ViewMesh {

  protected float[] vertexCache;
  protected String[] bitmapName;
  
  public BufferedViewMesh(float[] vertex, String[] bitmapName) {
    super(vertex, new int[vertex.length/4], new IBitmap[bitmapName.length]);
    this.bitmapName = bitmapName.clone();
    vertexCache = vertex.clone();
  }
  
  public void refresh(View view, int width, int height) {
    float scaleX = (float) (view.actualX/width);
    float scaleY = (float) (view.actualY/height);
    float scaleW = (float) (view.actualWidth/width);
    float scaleH = (float) (view.actualHeight/height);
    for(int i = 0; i<vertex.length/4; i++) {
      vertexCache[i*4] = (vertex[i*4]+1.0f) * scaleW+scaleX-1;
      vertexCache[i*4+1] = 1-(vertex[i*4+1]+1.0f) * scaleH+scaleY;
    }
    // 通过名称得到bitmap
  }
  
  @Override
  public float[] getVertex() {
    return vertexCache;
  }
  
}
