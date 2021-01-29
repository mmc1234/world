package com.github.mmc1234.world.toolkit.gui;

import com.github.mmc1234.world.toolkit.bitmap.IBitmap;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ViewMesh {
  protected float[] vertex; // 4
  protected int[] index; // 1
  protected IBitmap[] bitmap; // 1
  public int getVertexCount() {
    return getVertex().length/4;
  }
}
