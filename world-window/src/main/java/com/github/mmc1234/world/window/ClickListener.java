package com.github.mmc1234.world.window;

public interface ClickListener {
  public void onClick(View view, Window window);
  public void onLongClick(View view, Window window, long time);
  public void onCancelClick(View view, Window window);
}
