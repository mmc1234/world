package com.github.mmc1234.world.leg.toolkit.platform;

import org.joml.Vector2i;

public interface Platform {
  public void initialization();
  public void termination();
  public void load();
  public void create(View view);
  public void destroy(View view);
  public void make(View view);
  public void getSize(View view, Vector2i inSize);
  public void getPos(View view, Vector2i inPos);
  public String getClipString(View view);
  public void setClipString(View view, String meeage);
}
