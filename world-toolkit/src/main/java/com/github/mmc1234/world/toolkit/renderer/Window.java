package com.github.mmc1234.world.toolkit.renderer;

import java.util.ArrayList;
import java.util.Optional;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Window {

  public static void loadGL() {
    GL.createCapabilities();
  }
  public static void pollEvents() {
    GLFW.glfwPollEvents();
  }
  public static void waitEvents(double timeout) {
    GLFW.glfwWaitEventsTimeout(timeout);;
  }
  public @Setter IWindowShouldCloseListener closeListener;
  protected long handle = MemoryUtil.NULL;
  public boolean isEnable = true;
  private boolean isFocus;
  private Monitor monitor;
  public @Setter IWindowResizeListener resizeListener;
  private Window shareWindow;
  private String title;

  protected int x, y, width, height;

  public Window() {
    this(null, null, "World", 600, 400);
  }

  /**
   * @param inShare
   */
  public Window(Window inShare, Monitor inMonitor, String inTitle, int inWidth, int inHeight) {
    this.shareWindow = inShare;
    this.monitor = inMonitor;
    this.title = inTitle;
    this.width = inWidth;
    this.height = inHeight;

  }

  public void checkEmpty() {
    if (isEmpty()) {
      throw new RuntimeException("Empty window");
    }
  }

  protected void createWindow() {
    if (isEmpty() && isEnable) {
      handle = GLFW.glfwCreateWindow(width, height, title, (monitor == null ? MemoryUtil.NULL : monitor.handle),
          (shareWindow == null ? MemoryUtil.NULL : shareWindow.handle));
    }
  }

  public void focus() {
    GLFW.glfwFocusWindow(this.handle);
  }

  public String getClipString() {
    checkEmpty();
    return GLFW.glfwGetClipboardString(this.handle);
  }

  public float getOpacity() {
    checkEmpty();
    return GLFW.glfwGetWindowOpacity(this.handle);
  }

  public boolean isEmpty() {
    return this.handle == MemoryUtil.NULL;
  }

  public boolean isShouldClose() {
    return isEmpty() || GLFW.glfwWindowShouldClose(this.handle);
  }

  public boolean isVisable() {
    checkEmpty();
    return GLFW.glfwGetWindowAttrib(this.handle, GLFW.GLFW_VISIBLE) == GLFW.GLFW_TRUE;
  }

  public void make() {
    checkEmpty();
    GLFW.glfwMakeContextCurrent(this.handle);
  }

  public void restore() {
    checkEmpty();
    GLFW.glfwRestoreWindow(this.handle);
  }

  public void setAutoIconify(boolean isEnable) {
    checkEmpty();
    GLFW.glfwSetWindowAttrib(this.handle, GLFW.GLFW_AUTO_ICONIFY, isEnable ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
  }

  public void setClipString(String message) {
    checkEmpty();
    GLFW.glfwSetClipboardString(this.handle, message);
  }

  public void setDecorated(boolean isEnable) {
    checkEmpty();
    GLFW.glfwSetWindowAttrib(this.handle, GLFW.GLFW_DECORATED, isEnable ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
  }

  public void setFloating(boolean isEnable) {
    checkEmpty();
    GLFW.glfwSetWindowAttrib(this.handle, GLFW.GLFW_FLOATING, isEnable ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
  }

  public void setFocusOnShow(boolean isEnable) {
    checkEmpty();
    GLFW.glfwSetWindowAttrib(this.handle, GLFW.GLFW_FOCUS_ON_SHOW, isEnable ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
  }

  public void setLimits(int minWidth, int minHeight, int maxWidth, int maxHeight) {
    checkEmpty();
    GLFW.glfwSetWindowSizeLimits(this.handle, minWidth, minHeight, maxWidth, maxHeight);
  }

  public void setMaximize() {
    checkEmpty();
    GLFW.glfwMaximizeWindow(this.handle);
  }

  public void setMinimize() {
    checkEmpty();
    GLFW.glfwIconifyWindow(this.handle);
  }

  public void setMonitor(Monitor monitor, int refreshRate) {
    checkEmpty();
    this.monitor = monitor;
    GLFW.glfwSetWindowMonitor(this.handle, monitor.handle, x, y, width, height, refreshRate);
  }

  public void setOpacity(float inOpacity) {
    checkEmpty();
    GLFW.glfwSetWindowOpacity(this.handle, inOpacity);
  }

  public void setPosition(int x, int y) {
    checkEmpty();
    GLFW.glfwSetWindowPos(this.handle, x, y);
  }

  public void setRatio(int numer, int denom) {
    checkEmpty();
    GLFW.glfwSetWindowAspectRatio(this.handle, numer, denom);
  }

  public void setResizable(boolean isEnable) {
    checkEmpty();
    GLFW.glfwSetWindowAttrib(this.handle, GLFW.GLFW_RESIZABLE, isEnable ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
  }

  public void setShouldClose(boolean shouldClose) {
    GLFW.glfwSetWindowShouldClose(this.handle, shouldClose);
  }

  public void setSize(int w, int h) {
    checkEmpty();
    GLFW.glfwSetWindowSize(this.handle, w, h);
  }

  public void setTitle(String inTitle) {
    checkEmpty();
    GLFW.glfwSetWindowTitle(this.handle, (this.title = inTitle));
  }

  public void setVisable(boolean isVisable) {
    checkEmpty();
    if (isVisable) {
      GLFW.glfwShowWindow(this.handle);
    } else {
      GLFW.glfwHideWindow(this.handle);
    }
  }

  public void start() {
    createWindow();
    checkEmpty();
    // 设置大量监听
    GLFW.glfwSetWindowCloseCallback(this.handle, window -> {
      if (closeListener != null) {
        closeListener.onShouldClose(this);
      }
    });
    GLFW.glfwSetWindowSizeCallback(this.handle, (window, width, height) -> {
      int lastWidth = this.width;
      int lastHeight = this.height;
      this.width = width;
      this.height = height;
      if (resizeListener != null) {
        resizeListener.onResize(this, lastWidth, lastHeight, width, height);
      }
    });
    GLFW.glfwSetWindowFocusCallback(this.handle, (window, focused) -> {

      isFocus = focused;
    });
  }

  public void stop() {
    if (!isEmpty()) {
      GLFW.glfwDestroyWindow(this.handle);
      this.handle = MemoryUtil.NULL;
    }
  }

  public void swapBuffers() {
    checkEmpty();
    GLFW.glfwSwapBuffers(this.handle);
  }

  public void swapInterval(int interval) {
    checkEmpty();
    GLFW.glfwSwapInterval(interval);
  }

  /**
   * setCursor getCursor createStdCursor
   */
}