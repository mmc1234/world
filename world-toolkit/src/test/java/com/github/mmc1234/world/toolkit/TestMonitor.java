package com.github.mmc1234.world.toolkit;
import org.junit.jupiter.api.Test;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMonitorCallbackI;

import com.github.mmc1234.world.toolkit.renderer.Monitor;
import com.google.common.base.Optional;

class TestMonitor {

  @Test
  void test() {
    GLFW.glfwInit();
    Monitor.getMonitorList();
  }

}
