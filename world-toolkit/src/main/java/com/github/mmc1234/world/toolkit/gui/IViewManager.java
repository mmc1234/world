package com.github.mmc1234.world.toolkit.gui;

import com.github.mmc1234.world.toolkit.window.listener.IWindowListener;

public interface IViewManager extends IWindowListener {
  public void setFocus(View inFocus);
  public void setRoot(View inRoot);
  public View getRoot();
  public View getFocus();
}
