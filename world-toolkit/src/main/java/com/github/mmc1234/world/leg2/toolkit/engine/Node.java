package com.github.mmc1234.world.leg2.toolkit.engine;

import java.util.Map;

public interface Node {
  public void onUpdate();
  public void onPhysics();
  public void onRender();
  public void onStart();
  public void onStop();
  public void onEnter(Screen root);
  public void onExit(Screen root);
  
  public String getName();
  public void setName(String name);
  
  public Screen getRoot();
  public void setRoot(Screen root);
  
  public Map<String, Object> getUserdata();
  public Transform getTransform();
}