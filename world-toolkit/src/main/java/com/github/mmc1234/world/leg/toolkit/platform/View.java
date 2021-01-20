package com.github.mmc1234.world.leg.toolkit.platform;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class View {
  @Getter
  protected long id;
  public final int initW, initH;
  public final String initTitle;
}
