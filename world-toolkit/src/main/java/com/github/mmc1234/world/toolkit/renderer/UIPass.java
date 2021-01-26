package com.github.mmc1234.world.toolkit.renderer;

import com.github.mmc1234.world.toolkit.context.ILocalContext;

public class UIPass extends Pass {

  boolean isInit;
  
  @Override
  void preRender(ILocalContext context) {
    if(!isInit) {
      isInit = true;
      create(context);
    }
    for(ViewTrack track : trackList) {
      System.out.println(track.view);
    }
  }

  @Override
  void render(ILocalContext context, UniformBufferExt uniformBufferExt) {
    this.trackList.clear();
  }

  @Override
  int calculateUniformBufferSize() {
    return 0;
  }

  @Override
  void create(ILocalContext context) {
    System.out.println(this.getClass()+" create");
  }

  @Override
  void close(ILocalContext context) {
    System.out.println(this.getClass()+" close");
  }

}
