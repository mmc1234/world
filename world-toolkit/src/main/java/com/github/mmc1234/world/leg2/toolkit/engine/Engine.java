package com.github.mmc1234.world.leg2.toolkit.engine;

import java.util.Map;

public interface Engine {
  public void run(String... args);
  public void load(Screen screen);
  public Screen getCurrentScreen();
  public Screen getTargetScreen();
  public Map<String, Object> getUserdata();
}
