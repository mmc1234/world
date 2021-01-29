package com.github.mmc1234.world.toolkit.renderer;

import java.util.ArrayList;
import java.util.List;

import com.github.mmc1234.world.toolkit.gui.ViewMesh;

import lombok.Getter;

@Getter
public class ListRenderBuffer implements IRenderBuffer {
  private int layer;
  private @Getter List<ViewMesh> meshList = new ArrayList<>();
  
  public ListRenderBuffer(int layer) {
    this.layer = layer;
  }

  @Override
  public void add(ViewMesh mesh) {
    meshList.add(mesh);
  }

  @Override
  public int getVertexCount() {
    int size = 0;
    for(ViewMesh mesh : meshList) {
      size+=(mesh.getVertexCount());
    }
    return size;
  }

  @Override
  public void clear() {
    meshList.clear();
  }

}
