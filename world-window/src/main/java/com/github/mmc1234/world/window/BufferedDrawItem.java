package com.github.mmc1234.world.window;

public interface BufferedDrawItem {
  /**
   * The unit of measurement is ms.
   * */
  public int getBufferTimeout();
  public void onUpdateBuffer();
}
