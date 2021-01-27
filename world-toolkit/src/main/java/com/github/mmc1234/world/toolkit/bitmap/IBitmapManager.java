package com.github.mmc1234.world.toolkit.bitmap;

import java.util.Collection;

public interface IBitmapManager {
  public boolean add(String name, int width, int height);
  public boolean remove(String name);
  public IBitmap get(String name);
  public Collection<IBitmap> getAll();
  public int getMaxWidth();
  public int getMaxHeight();
  public void close();
}
