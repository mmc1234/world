package com.github.mmc1234.world.toolkit.renderer;

import com.github.mmc1234.world.toolkit.gui.View;

import lombok.SneakyThrows;

public class Track implements Cloneable {
  public View view;
  public int layer, color;
  

  @Override
  @SneakyThrows
  public Track clone() {
    Track result = (Track) super.clone();
    result.view = view;
    result.layer = layer;
    return result;
  }
}