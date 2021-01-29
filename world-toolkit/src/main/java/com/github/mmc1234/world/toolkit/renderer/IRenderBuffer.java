package com.github.mmc1234.world.toolkit.renderer;

import java.util.Collection;
import java.util.List;

import com.github.mmc1234.world.toolkit.gui.ViewMesh;

public interface IRenderBuffer {
  public int getLayer();
  public void add(ViewMesh mesh);
  public List<ViewMesh> getMeshList();
  public int getVertexCount();
  public void clear();
}
