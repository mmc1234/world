package com.github.mmc1234.world.toolkit.window;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

import com.github.mmc1234.world.message.CancelableEventBus;
import com.github.mmc1234.world.toolkit.bitmap.FastBitmapManager;
import com.github.mmc1234.world.toolkit.context.ILocalContext;
import com.github.mmc1234.world.toolkit.renderer.DebugRenderer;
import com.github.mmc1234.world.toolkit.renderer.DirectUniformBufferExt;
import com.github.mmc1234.world.toolkit.renderer.IRenderer;

import lombok.Getter;

public class LocalContext implements ILocalContext {

  private @Getter Window currentWindow;
  private @Getter IRenderer renderer;
  private @Getter CancelableEventBus eventBus;
  private @Getter FastBitmapManager bitmapManager;
  
  public LocalContext() {
    this.renderer = new DebugRenderer();
    eventBus = new CancelableEventBus("Local-Context"+Thread.currentThread());
    bitmapManager = new FastBitmapManager(1024, 1024);
  }

  @Override
  public void loadGL() {
    GL.createCapabilities();
  }

  @Override
  public void make(Window window) {
    if(window!=null && !window.isEmpty()) {
      currentWindow = window;
      window.context = this;
      GLFW.glfwMakeContextCurrent(window.getHandle());
    }
  }

  @Override
  public void onWindowDestry(Window window) {
    if(getCurrentWindow() == window) {
      currentWindow = null;
    }
  }

  @Override
  public void pollEvents() {
    GLFW.glfwPollEvents();
  }

  @Override
  public void waitEvents() {
    GLFW.glfwWaitEvents();
  }

  @Override
  public void waitEvents(double timeout) {
    GLFW.glfwWaitEventsTimeout(timeout);
  }

}
