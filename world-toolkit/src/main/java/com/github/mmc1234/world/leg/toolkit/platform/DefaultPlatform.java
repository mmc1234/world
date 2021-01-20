package com.github.mmc1234.world.leg.toolkit.platform;

import org.apache.commons.lang.reflect.FieldUtils;
import org.joml.Vector2i;
import org.lwjgl.system.MemoryUtil;

import lombok.SneakyThrows;

import static org.lwjgl.glfw.GLFW.*;

public class DefaultPlatform implements Platform {

  @Override
  @SneakyThrows
  public void create(View view) {
    if(view.getId() == 0) {
      view.id = glfwCreateWindow(view.initW, view.initH, view.initTitle, MemoryUtil.NULL, MemoryUtil.NULL);
    }
  }
  
  @Override
  public void make(View view) {
    glfwMakeContextCurrent(view.getId());
  }

  @Override
  @SneakyThrows
  public void destroy(View view) {
    if(view.getId() != 0) {
      glfwDestroyWindow(view.getId());
      view.id = 0;
    }
  }

  @Override
  public void getSize(View view, Vector2i inSize) {
  }

  @Override
  public void getPos(View view, Vector2i inPos) {
  }

  @Override
  public String getClipString(View view) {
    return null;
  }

  @Override
  public void setClipString(View view, String meeage) {
  }

  @Override
  public void initialization() {
    glfwInit();
  }

  @Override
  public void termination() {
    glfwTerminate();
  }
  
  @Override
  public void load() {
    org.lwjgl.opengl.GL.createCapabilities();
  }

}
