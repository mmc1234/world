package com.github.mmc1234.world.toolkit.renderer;

import java.util.ArrayList;

import com.github.mmc1234.world.toolkit.context.ILocalContext;

abstract class Pass {
  int offset;
  ArrayList<ViewTrack> trackList = new ArrayList<ViewTrack>();
  abstract void preRender(ILocalContext context);
  abstract void render(ILocalContext context, UniformBufferExt uniformBufferExt);
  abstract void create(ILocalContext context);
  abstract void close(ILocalContext context);
  abstract int calculateUniformBufferSize();
}
