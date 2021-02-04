package com.github.mmc1234.world.window;

public interface PlatformExtension {
  public int getX();
  public int getY();
  public int getWidth();
  public int getHeight();
  public String getTitle();
  public void setTitle(String title);
  public void create(Window window);
  public void close(Window window);
  public long getHandle();
  public void make();
  /**
   * If the window is attached to the current thread, unattach it.
   * */
  public void unmake();
}
