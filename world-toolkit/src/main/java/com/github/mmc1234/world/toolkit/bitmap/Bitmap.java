package com.github.mmc1234.world.toolkit.bitmap;

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
  protected String name;
  @Override
  @SneakyThrows
  protected Bitmap clone() {
    Bitmap result = (Bitmap) super.clone();
    result.x = x;
    result.y = y;
    result.width = width;
    result.height = height;
    result.handle = handle;
    result.name = name;
    return result;
  }
}
