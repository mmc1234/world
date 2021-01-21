package com.github.mmc1234.world.leg2.toolkit.gui;

import com.github.mmc1234.world.leg2.toolkit.window.listener.IWindowListener;

public interface IViewManager extends IWindowListener {
  public void setFocus(View inFocus);
  public void setRoot(View inRoot);
  public View getRoot();
  public View getFocus();
}
