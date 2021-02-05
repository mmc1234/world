package com.github.mmc1234.world.window;

public interface Cursor {
  public double getX();
  public double getY();
  public void create();
  public void close();
  public String getCurrent();
  public void use(String name);
}
