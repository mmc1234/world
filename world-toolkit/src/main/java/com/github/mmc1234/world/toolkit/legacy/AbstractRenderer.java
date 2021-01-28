package com.github.mmc1234.world.toolkit.legacy;

import java.util.TreeSet;

import com.github.mmc1234.world.toolkit.gui.View;
import com.github.mmc1234.world.toolkit.local.ILocalContext;
import com.github.mmc1234.world.toolkit.renderer.Track;

public abstract class AbstractRenderer  {
  /*
   * protected final TreeSet<Track> trackSet = new TreeSet<Track>((o1, o2) -> o1.layer - o2.layer);
  protected final Track[] trackArray = new Track[64];
  protected int currentIndex = 0, lastVertexCount, vertexCount;

  @Override
  public void render(ILocalContext context) {
    create(context);
    trackSet.clear();
    View root = context.getCurrentWindow().getRootView();
    root.onRender(context);
    malloc();
    update();
    draw();
    lastVertexCount = vertexCount;
  }
  
  public abstract void malloc();
  public abstract void update();
  public abstract void draw();

  @Override
  public void enter(View view, Object mesh) {
    for (int i = 0; i < currentIndex; i++) {
      if (trackArray[i].view == view) {
        return;
      }
    }
    trackArray[currentIndex].view = view;
    trackArray[currentIndex].layer = currentIndex;
    trackSet.add(trackArray[currentIndex++].clone());
  }

  @Override
  public void exit(View view) {
    if (trackArray[currentIndex - 1].view == view) {
      currentIndex--;
      trackArray[currentIndex].view = null;
    }
  }
   * */

}
