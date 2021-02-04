package com.github.mmc1234.world.window;

public interface ButtonListener {
  public void onButton(View view, Window window, ActionType actionType, ButtonType buttonType, int mods, int x, int y);
}
