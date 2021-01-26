package com.github.mmc1234.world.toolkit.gui.render;

abstract class UniformBufferExt {
  int offset;
  public abstract void clean();
  public int request(int count) {
    return offset+count;
  }
  
  public abstract void flush();
}
