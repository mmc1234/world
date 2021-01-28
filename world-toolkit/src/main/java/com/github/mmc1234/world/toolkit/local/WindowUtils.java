package com.github.mmc1234.world.toolkit.local;

import org.lwjgl.glfw.GLFW;

import lombok.experimental.UtilityClass;

@UtilityClass
public class WindowUtils {
  public String getClipString(Window window) {
    window.checkEmpty();
    return GLFW.glfwGetClipboardString(window.getHandle());
  }

  public float getOpacity(Window window) {
    window.checkEmpty();
    return GLFW.glfwGetWindowOpacity(window.getHandle());
  }

  public void restore(Window window) {
    window.checkEmpty();
    GLFW.glfwRestoreWindow(window.getHandle());
  }

  public void setAutoIconify(Window window, boolean isEnable) {
    window.checkEmpty();
    GLFW.glfwSetWindowAttrib(window.getHandle(), GLFW.GLFW_AUTO_ICONIFY, isEnable ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
  }

  public void setClipString(Window window, String message) {
    window.checkEmpty();
    GLFW.glfwSetClipboardString(window.getHandle(), message);
  }

  public void setDecorated(Window window, boolean isEnable) {
    window.checkEmpty();
    GLFW.glfwSetWindowAttrib(window.getHandle(), GLFW.GLFW_DECORATED, isEnable ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
  }

  public void setFloating(Window window, boolean isEnable) {
    window.checkEmpty();
    GLFW.glfwSetWindowAttrib(window.getHandle(), GLFW.GLFW_FLOATING, isEnable ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
  }

  public void setFocusOnShow(Window window, boolean isEnable) {
    window.checkEmpty();
    GLFW.glfwSetWindowAttrib(window.getHandle(), GLFW.GLFW_FOCUS_ON_SHOW, isEnable ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
  }

  public void setLimits(Window window, int minWidth, int minHeight, int maxWidth, int maxHeight) {
    window.checkEmpty();
    GLFW.glfwSetWindowSizeLimits(window.getHandle(), minWidth, minHeight, maxWidth, maxHeight);
  }

  public void setMaximize(Window window) {
    window.checkEmpty();
    GLFW.glfwMaximizeWindow(window.getHandle());
  }

  public void setMinimize(Window window) {
    window.checkEmpty();
    GLFW.glfwIconifyWindow(window.getHandle());
  }

  public void setMonitor(Window window, Monitor monitor, int refreshRate) {
    window.checkEmpty();
    window.monitor = monitor;
    GLFW.glfwSetWindowMonitor(window.getHandle(), monitor.handle, window.x, window.y, window.width, window.height,
        refreshRate);
  }

  public void setOpacity(Window window, float inOpacity) {
    window.checkEmpty();
    GLFW.glfwSetWindowOpacity(window.getHandle(), inOpacity);
  }

  public void setPosition(Window window, int x, int y) {
    window.checkEmpty();
    GLFW.glfwSetWindowPos(window.getHandle(), x, y);
    GLFW.glfwGetWindowPos(window.getHandle(), window.buf1, window.buf2);
    window.x = window.buf1[0];
    window.y = window.buf2[0];
  }

  public void setRatio(Window window, int numer, int denom) {
    window.checkEmpty();
    GLFW.glfwSetWindowAspectRatio(window.getHandle(), numer, denom);
  }

  public void setResizable(Window window, boolean isEnable) {
    window.checkEmpty();
    GLFW.glfwSetWindowAttrib(window.getHandle(), GLFW.GLFW_RESIZABLE, isEnable ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
  }

  public void setSize(Window window, int w, int h) {
    window.checkEmpty();
    GLFW.glfwSetWindowSize(window.getHandle(), w, h);
    GLFW.glfwGetWindowSize(window.getHandle(), window.buf1, window.buf2);
    window.width = window.buf1[0];
    window.height = window.buf2[0];
  }

  public void setTitle(Window window, String inTitle) {
    window.checkEmpty();
    GLFW.glfwSetWindowTitle(window.getHandle(), (window.title = inTitle));
  }

  public void setVisable(Window window, boolean isVisable) {
    window.checkEmpty();
    if (isVisable) {
      GLFW.glfwShowWindow(window.getHandle());
    } else {
      GLFW.glfwHideWindow(window.getHandle());
    }
  }

  public boolean isVisable(Window window) {
    window.checkEmpty();
    return GLFW.glfwGetWindowAttrib(window.getHandle(), GLFW.GLFW_VISIBLE) == GLFW.GLFW_TRUE;
  }
}
