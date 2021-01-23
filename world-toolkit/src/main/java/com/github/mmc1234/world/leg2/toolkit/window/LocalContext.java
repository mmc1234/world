package com.github.mmc1234.world.leg2.toolkit.window;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

import com.github.mmc1234.world.leg2.toolkit.gui.IBatch;
import com.github.mmc1234.world.message.CancelableEventBus;
import com.google.common.eventbus.EventBus;

import lombok.Getter;

public class LocalContext implements ILocalContext {

  private @Getter Window currentWindow;
  private @Getter IBatch batch;
  private @Getter CancelableEventBus eventBus;
  
  public LocalContext(IBatch inBatch) {
    this.batch = inBatch;
    eventBus = new CancelableEventBus("Local-Context"+Thread.currentThread());
  }

  @Override
  public void loadGL() {
    GL.createCapabilities();
  }

  @Override
  public void make(Window window) {
    if(window!=null && !window.isEmpty()) {
      currentWindow = window;
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
