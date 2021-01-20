package com.github.mmc1234.world.toolkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL30;

import com.github.mmc1234.world.toolkit.renderer.StateManager;
import com.github.mmc1234.world.toolkit.renderer.Window;

import lombok.SneakyThrows;

class TestStateManager {

  @Test
  @SneakyThrows
  void test() {
    GLFW.glfwInit();
    Window window = new Window();
    window.start();
    window.make();
    Window.loadGL();
    
    int program = GL30.glCreateProgram();

    while (!window.isShouldClose()) {
      Window.pollEvents();
      GL30.glClearColor(0, 0.7f, 1, 1);
      GL30.glClear(GL30.GL_COLOR_BUFFER_BIT);
      window.swapBuffers();
      Thread.sleep(100);
    }
    window.stop();
    GLFW.glfwTerminate();
  }

}
