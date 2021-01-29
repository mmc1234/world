package com.github.mmc1234.world.toolkit.bitmap;

import java.net.URL;

class EmptyBitmap extends Bitmap {

  public EmptyBitmap(int x, int y, int width, int height, int handle) {
    super(x, y, width, height, handle, null);
  }
  
  @Override
  public String getName() {
    return "Empty";
  }

}
