package com.github.mmc1234.world.leg2.toolkit.window;

import org.lwjgl.glfw.GLFW;

import lombok.AllArgsConstructor;
import lombok.Getter;



@AllArgsConstructor
public enum ActionType {
  Release(GLFW.GLFW_RELEASE),
  Press(GLFW.GLFW_PRESS),
  Repeat(GLFW.GLFW_REPEAT);
  
  public @Getter final int type;
  public static ActionType from(int inType) {
    for(ActionType t : values()) {
      if(t.type == inType) {
        return t;
      }
    }
    throw new IllegalArgumentException();
  }
}
