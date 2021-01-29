package com.github.mmc1234.world.toolkit.bitmap;

import java.net.URL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
class Bitmap implements IBitmap, Cloneable {
  protected int x, y, width, height, handle;
  protected URL url;
  @Override
  @SneakyThrows
  protected Bitmap clone() {
    Bitmap result = (Bitmap) super.clone();
    result.x = x;
    result.y = y;
    result.width = width;
    result.height = height;
    result.handle = handle;
    return result;
  }
  @Override
  public URL getURL() {
    return url;
  }
  
  @Override
  public String getName() {
    return url.toString();
  }
}
