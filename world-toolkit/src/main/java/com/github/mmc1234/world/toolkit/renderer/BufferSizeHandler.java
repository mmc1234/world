package com.github.mmc1234.world.toolkit.renderer;

public interface BufferSizeHandler {
  /**
   * @param currentBaseSize
   */
  public int handlerSize(int currentBaseSize, int lastVertexCount, int vertexCount);
}