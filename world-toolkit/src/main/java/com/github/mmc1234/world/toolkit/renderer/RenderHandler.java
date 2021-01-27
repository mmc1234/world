package com.github.mmc1234.world.toolkit.renderer;

import java.util.TreeSet;

import com.github.mmc1234.world.toolkit.context.ILocalContext;

public interface RenderHandler {
  public int handleCalculateVertexCount(ILocalContext context, TreeSet<Track> trackSet);
  public void handleGenBuffer(ILocalContext context, TreeSet<Track> trackSet, VertexArray vertexArray);
  public void handleGenUniformBuffer(ILocalContext context, TreeSet<Track> trackSet, int texture);
}
