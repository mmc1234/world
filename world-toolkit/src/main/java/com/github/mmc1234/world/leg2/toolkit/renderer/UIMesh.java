package com.github.mmc1234.world.leg2.toolkit.renderer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UIMesh {
  public float[] pos;
  public final float[] clip, rect;
  public int getVertexCount() {
    return pos.length/2;
  }
}
