package com.github.mmc1234.world.engine.view;

import java.util.ArrayList;

import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.Pointer;

import lombok.Getter;

@Getter
public class Monitor {
  protected long handle;
  protected String name;
  protected VideoMode videoMode;
  
  public void a() {
    GLFW.glfwGetVideoMode(handle);
  }
  
  public static ArrayList<Monitor> getMonitorList() {
    PointerBuffer pb = GLFW.glfwGetMonitors();
    int size = pb.sizeof()/Pointer.POINTER_SIZE;
    if(size <= 0) {
      return null;
    }
    ArrayList<Monitor> list = new ArrayList<Monitor>(size);
    for(int i = 0; i<size; i++) {
      Monitor monitor = new Monitor();
      monitor.handle = pb.get(i);
      monitor.name = GLFW.glfwGetMonitorName(monitor.handle);
      // 尚有众多数据未初始化
      
      list.add(monitor);
    }
    return list;
  }
}
