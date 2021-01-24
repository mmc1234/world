package com.github.mmc1234.world.toolkit;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWErrorCallbackI;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GLDebugMessageCallback;
import org.lwjgl.opengl.GLDebugMessageCallbackI;
import org.lwjgl.opengl.KHRDebug;
import org.lwjgl.system.MemoryUtil;

import com.github.mmc1234.world.leg2.toolkit.renderer.UIMesh;
import com.github.mmc1234.world.leg2.toolkit.window.ILocalContext;
import com.github.mmc1234.world.leg2.toolkit.window.LocalContext;
import com.github.mmc1234.world.leg2.toolkit.window.Window;

import lombok.SneakyThrows;

class TestUIRenderer {

  @Test
  @SneakyThrows
  void testClip() {
    GLFW.glfwInit();
    ILocalContext ctx = new LocalContext(null);
    Window window = new Window(null, null, "Render UI-0.1", 800, 600);
    window.start();
    
    ctx.make(window);
    ctx.loadGL();
    
    double lastTime = GLFW.glfwGetTime();
    final double FPS = 75;
    double t = 0;
    window.swapInterval(1);
    GLFW.glfwSetErrorCallback(new GLFWErrorCallbackI() {
      
      @Override
      public void invoke(int error, long description) {
        System.out.println("GLFW("+error+"):"+GLFWErrorCallback.getDescription(description)); 
      }
    });
    
    GL30.glEnable(KHRDebug.GL_DEBUG_OUTPUT);
    KHRDebug.glDebugMessageCallback(new GLDebugMessageCallbackI() {
      
      @Override
      public void invoke(int source, int type, int id, int severity, int length, long message, long userParam) {
        System.out.println(GLDebugMessageCallback.getMessage(length, message));
      }
    }, MemoryUtil.NULL);
    
    UIRenderer renderer = new UIRenderer();
    renderer.init();
    renderer.draw(new UIMesh(new float[] {-1, 1, -1, -1, 1, 1, 1, 1, -1, -1, 1, -1}, 0, 200, 200, 0));
    
    while(!window.isShouldClose()) {
      ctx.waitEvents(0.001);
      double time = GLFW.glfwGetTime();
      t = t+(time-lastTime);
      lastTime = time;
      if(t>(FPS/1000)) {
        t = t%(FPS/1000);
        renderer.render();
        window.swapBuffers();
      }
    }
  }

}
