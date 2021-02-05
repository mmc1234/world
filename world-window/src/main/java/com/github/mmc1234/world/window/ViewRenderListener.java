package com.github.mmc1234.world.window;

import com.github.mmc1234.world.message.Subscribe;
import com.github.mmc1234.world.window.ViewTree.OnDrawListener;
import com.github.mmc1234.world.window.ViewTree.OnPreDrawListener;

public class ViewRenderListener {
  @Subscribe
  public void onRender(ViewRenderEvent event) {
    View view = event.getView();
    for(OnPreDrawListener l:view.getListener(OnPreDrawListener.class)) {
      l.onPreDraw(view, event.getBatch());
    }
    for(OnDrawListener l:view.getListener(OnDrawListener.class)) {
      l.onDraw(view, event.getBatch());
    }
  }
}
