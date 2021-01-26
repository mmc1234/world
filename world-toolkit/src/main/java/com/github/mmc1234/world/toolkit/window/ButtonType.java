package com.github.mmc1234.world.toolkit.window;

import org.lwjgl.glfw.GLFW;

import lombok.AllArgsConstructor;
import lombok.Getter;

  @AllArgsConstructor
  public enum ButtonType {
    Left(GLFW.GLFW_MOUSE_BUTTON_LEFT),
    Right(GLFW.GLFW_MOUSE_BUTTON_RIGHT),
    Last(GLFW.GLFW_MOUSE_BUTTON_LAST),
    Middle(GLFW.GLFW_MOUSE_BUTTON_MIDDLE),
    Button1(GLFW.GLFW_MOUSE_BUTTON_1),
    Button2(GLFW.GLFW_MOUSE_BUTTON_2),
    Button3(GLFW.GLFW_MOUSE_BUTTON_3),
    Button4(GLFW.GLFW_MOUSE_BUTTON_4),
    Button5(GLFW.GLFW_MOUSE_BUTTON_5),
    Button6(GLFW.GLFW_MOUSE_BUTTON_6),
    Button7(GLFW.GLFW_MOUSE_BUTTON_7),
    Button8(GLFW.GLFW_MOUSE_BUTTON_8);
    
    public @Getter final int type;
    public static ButtonType from(int inType) {
      for(ButtonType t : values()) {
        if(t.type == inType) {
          return t;
        }
      }
      throw new IllegalArgumentException();
    }
  }
