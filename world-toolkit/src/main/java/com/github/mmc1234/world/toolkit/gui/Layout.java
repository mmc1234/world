package com.github.mmc1234.world.toolkit.gui;

import java.util.Iterator;
import java.util.LinkedList;

import com.github.mmc1234.world.toolkit.local.ILocalContext;
import com.github.mmc1234.world.toolkit.local.Window;

public abstract class Layout extends View implements Iterable<View> {

  LinkedList<View> childList;
  
  @Override
  public void onRender(Window window) {
    for(View child : this) {
      child.onRender(window);
    }
  }

  @Override
  public View onHit(Window window, double x, double y) {
    for(View child : this) {
      View result = child.onHit(window, x, y);
      if(result!=null) {
        return result;
      }
    }
    return super.onHit(window, x, y);
  }
  
  @Override
  public Iterator<View> iterator() {
    return childList.iterator();
  }

}
