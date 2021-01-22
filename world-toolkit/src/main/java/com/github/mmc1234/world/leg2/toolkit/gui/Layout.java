package com.github.mmc1234.world.leg2.toolkit.gui;

import java.util.Iterator;
import java.util.LinkedList;

import com.github.mmc1234.world.leg2.toolkit.window.ActionType;
import com.github.mmc1234.world.leg2.toolkit.window.ButtonType;
import com.github.mmc1234.world.leg2.toolkit.window.ILocalContext;
import com.google.common.collect.ImmutableList;

public abstract class Layout extends View implements Iterable<View> {

  LinkedList<View> childList;
  
  @Override
  public void onPreRender(ILocalContext ctx) {
    for(View child : this) {
      child.onPreRender(ctx);
    }
  }

  @Override
  public View onHit(ILocalContext ctx, double x, double y) {
    for(View child : this) {
      View result = child.onHit(ctx, x, y);
      if(result!=null) {
        return result;
      }
    }
    return super.onHit(ctx, x, y);
  }
  
  @Override
  public Iterator<View> iterator() {
    return childList.iterator();
  }

}
