package com.github.mmc1234.world.window;

public interface ViewTree {
  public interface OnDrawListener {
    public void onDraw(View view);
  }

  public interface OnPreDrawListener {
    public void onPreDraw(View view);
  }
}
