package com.github.mmc1234.world.toolkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.lwjgl.glfw.GLFW;

class TestTimer {

  @Test
  void test() {
    GLFW.glfwInit();
    while(true) {
      GLFW.glfwWaitEventsTimeout(0.001);
    }
  }

}
