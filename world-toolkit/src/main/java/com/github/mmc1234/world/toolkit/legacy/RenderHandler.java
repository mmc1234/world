package com.github.mmc1234.world.toolkit.legacy;

import java.util.TreeSet;

import com.github.mmc1234.world.toolkit.local.ILocalContext;
import com.github.mmc1234.world.toolkit.renderer.Track;
import com.github.mmc1234.world.toolkit.renderer.VertexArray;

public interface RenderHandler {
  public int handleCalculateVertexCount(ILocalContext context, TreeSet<Track> trackSet);
  public void handleGenBuffer(ILocalContext context, TreeSet<Track> trackSet, VertexArray vertexArray);
  public void handleGenUniformBuffer(ILocalContext context, TreeSet<Track> trackSet, int texture);
}
