package com.github.mmc1234.world.leg2.toolkit.gui.render;

class UniformBufferExt {
  int offset;
  public void clean() {
    
  }
  public int request(int count) {
    return offset+count;
  }
  
  public void flush() {
    
  }
}
