package com.github.mmc1234.world.toolkit.renderer;

import com.github.mmc1234.world.toolkit.gui.View;

import lombok.SneakyThrows;
import lombok.ToString;

@ToString
class ViewTrack implements Cloneable {
  int pass;
  double z;
  View view;
  @Override
  @SneakyThrows
  public ViewTrack clone() {
    return (ViewTrack) super.clone();
  }
}
