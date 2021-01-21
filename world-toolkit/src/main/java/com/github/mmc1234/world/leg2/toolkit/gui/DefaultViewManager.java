package com.github.mmc1234.world.leg2.toolkit.gui;

import com.github.mmc1234.world.leg2.toolkit.window.ActionType;
import com.github.mmc1234.world.leg2.toolkit.window.Window;

import lombok.Getter;

public class DefaultViewManager implements IViewManager {
  
  private @Getter View root, focus;

  @Override
  public void setFocus(View inFocus) {
  }

  @Override
  public void setRoot(View inRoot) {
  }
  @Override
  public void onButton(Window inWindow, ActionType type, double x, double y) {
    System.out.println("DefaultContext: ButtonEvent(x:"+x+",y:"+y+")");
    System.out.println(this);
    //getRoot().onHit(this, x, y);
  }

}
