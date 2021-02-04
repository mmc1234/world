package com.github.mmc1234.world.lwjgl.test;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.junit.jupiter.api.Test;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import com.github.mmc1234.world.lwjgl.GL2Batch;
import com.github.mmc1234.world.lwjgl.LwjglWindowExtension;
import com.github.mmc1234.world.message.CancelableEventBus;
import com.github.mmc1234.world.window.Batch;
import com.github.mmc1234.world.window.Window;

class WindowResizeTest {

  @Test
  void test() {
    GLFW.glfwInit();
    Window window = new Window(
        new LwjglWindowExtension(), new CancelableEventBus("Window-EventBus"));
    window.create();
    GLFW.glfwMakeContextCurrent(window.getHandle());
    GL.createCapabilities();
    GL2Batch batch = new GL2Batch();
    batch.setWindow(window);
    GL30.glEnable(GL30.GL_TEXTURE_2D);
    
    int tex = GL30.glGenTextures();
    GL30.glBindTexture(GL30.GL_TEXTURE_2D, tex);
    ByteBuffer img = MemoryUtil.memAlloc(256*256*4);
    for(int i = 0; i<256; i++) {
      for(int j = 0; j<256; j++) {
        img.put((byte) 0x00).put((byte) (i)).put((byte) 0x00).put((byte) 0x00);
      }
    }
    GL30.glPixelStorei(GL30.GL_UNPACK_ALIGNMENT, 1);
    GL30.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_MIN_FILTER, GL30.GL_NEAREST);
    GL30.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_MAG_FILTER, GL30.GL_NEAREST);
    
    GL30.glTexImage2D(GL30.GL_TEXTURE_2D, 0, GL30.GL_RGBA, 256, 256, 0, GL30.GL_RGBA, GL30.GL_UNSIGNED_BYTE, (ByteBuffer) img.flip());
    
    while (!GLFW.glfwWindowShouldClose(window.getHandle())) {
      GLFW.glfwWaitEventsTimeout(0.001);
      if (window.getHandle() == 0) {
        break;
      }
      batch.beginFrame(0, 0, 0);
        batch.setPixel(false);
        batch.begin();
          batch.draw(0, 0, 0, 0);
          batch.draw(0, 0.5f, 0, 1);
          batch.draw(0.5f, 0, 1, 0);
          batch.draw(0.5f, 0, 1, 0);
          batch.draw(0, 0.5f, 0, 1);
          batch.draw(0.5f, 0.5f, 1, 1);
        batch.end();
      batch.endFrame();
    }
    GLFW.glfwTerminate();
  }

}
