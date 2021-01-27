package com.github.mmc1234.world.toolkit.renderer;

import com.github.mmc1234.world.toolkit.gui.View;

import lombok.SneakyThrows;

class Track implements Cloneable {
  protected View view;
  protected int layer;
  protected int offset;

  @Override
  @SneakyThrows
  protected Track clone() {
    Track result = (Track) super.clone();
    result.view = view;
    result.layer = layer;
    result.offset = offset;
    return result;
  }
}