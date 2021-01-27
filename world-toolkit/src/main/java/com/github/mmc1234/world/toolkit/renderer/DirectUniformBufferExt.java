package com.github.mmc1234.world.toolkit.renderer;

import com.google.common.primitives.Ints;

public class DirectUniformBufferExt extends UniformBufferExt {

  @Override
  public void flush() {
    System.out.println();
  }

  @Override
  public void put(float[] data) {
    System.out.print("put ");
  }

  @Override
  public void put(int[] data) {
    System.out.print("put ");
  }

}
