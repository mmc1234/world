package com.github.mmc1234.world.toolkit.renderer;

abstract class UniformBufferExt {
  int offset;
  public int request(int count) {
    int result = offset;
    offset += count;
    return result;
  }
  
  public abstract void flush();
  public abstract void put(float[] data);
  public abstract void put(int[] data);
}
