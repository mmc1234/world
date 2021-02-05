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
  public Icon getIcon();
  public void setIcon(Icon icon);
  public Cursor getCursor();
  public void setSize(int width, int height);
  public void setPos(int x, int y);
  /**
   * If the window is attached to the current thread, unattach it.
   * */
  public void unmake();
  public boolean isShouldClose();
  public void setShouldClose(boolean shouldClose);
}
