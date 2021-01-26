package com.github.mmc1234.world.leg2.toolkit.gui.render;

import java.util.ArrayList;

import com.github.mmc1234.world.leg2.toolkit.gui.View;
import com.github.mmc1234.world.leg2.toolkit.window.ILocalContext;

abstract class Pass {
  int offset;
  ArrayList<ViewTrack> trackList = new ArrayList<ViewTrack>();
  abstract void preRender(ILocalContext context);
  abstract void render(ILocalContext context);
}
