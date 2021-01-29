package com.github.mmc1234.world.toolkit.local;

import org.apache.log4j.Logger;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

import com.github.mmc1234.world.message.CancelableEventBus;
import com.github.mmc1234.world.toolkit.bitmap.FastBitmapManager;
import com.github.mmc1234.world.toolkit.bitmap.IBitmap;
import com.github.mmc1234.world.toolkit.bitmap.IBitmapManager;
import com.github.mmc1234.world.toolkit.renderer.DevBatch;
import com.github.mmc1234.world.toolkit.renderer.GL30Renderer;
import com.github.mmc1234.world.toolkit.renderer.IBatch;
import com.github.mmc1234.world.toolkit.renderer.IRenderer;

import lombok.Getter;

public class LocalContext implements ILocalContext {

  private @Getter Window currentWindow;
  private @Getter IBatch batch;
  private @Getter IRenderer renderer;
  private @Getter CancelableEventBus eventBus;
  private @Getter IBitmapManager bitmapManager;
  private @Getter Logger logger;
  
  public LocalContext() {
    eventBus = new CancelableEventBus("Local-Context("+Thread.currentThread()+")");
    bitmapManager = new FastBitmapManager(1024, 1024);
    renderer = new GL30Renderer();
    batch = new DevBatch();
    logger = Logger.getLogger("Local-Context("+Thread.currentThread()+")");
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
