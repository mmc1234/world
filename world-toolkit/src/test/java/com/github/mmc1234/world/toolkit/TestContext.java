package com.github.mmc1234.world.toolkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.lwjgl.glfw.GLFW;

import com.github.mmc1234.world.toolkit.renderer.DefaultContext;
import com.github.mmc1234.world.toolkit.renderer.IContext;
import com.github.mmc1234.world.toolkit.renderer.Window;

import lombok.SneakyThrows;

class TestContext {

  @Test
  @SneakyThrows
  void test() {
    GLFW.glfwInit();
    IContext ctx = new DefaultContext(new Window());
    Window window =ctx.getWindow();
    window.start();
    window.make();
    Window.loadGL();
    while(!window.isShouldClose()) {
      Window.pollEvents();
      
      window.swapBuffers();
      Thread.sleep(66);
    }
  }

}
