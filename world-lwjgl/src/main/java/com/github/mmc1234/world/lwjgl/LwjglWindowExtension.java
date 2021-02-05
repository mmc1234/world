package com.github.mmc1234.world.lwjgl;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWDropCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

import com.github.mmc1234.world.window.ActionType;
import com.github.mmc1234.world.window.ButtonListener;
import com.github.mmc1234.world.window.ButtonType;
import com.github.mmc1234.world.window.ClickListener;
import com.github.mmc1234.world.window.KeyListener;
import com.github.mmc1234.world.window.PlatformExtension;
import com.github.mmc1234.world.window.View;
import com.github.mmc1234.world.window.Window;
import com.google.common.collect.ImmutableList;

import lombok.Getter;
import lombok.var;

@Getter
public class LwjglWindowExtension implements PlatformExtension {
  protected int[] buf1, buf2;
  protected @Getter long handle;
  protected boolean isMake;
  protected Monitor monitor;
  protected Window shareWindow;
  
  protected String title;
  protected int x, y, width, height;
  
  public LwjglWindowExtension() {
    this(null, null, "World", 600, 400);
  }

  public LwjglWindowExtension(Monitor monitor, String title, int width, int height) {
    this(monitor, null, title, width, height);
  }

  public LwjglWindowExtension(Monitor monitor, Window shareWindow, String title, int width, int height) {
    this.monitor = monitor;
    this.shareWindow = shareWindow;
    this.title = title;
    this.width = width;
    this.height = height;
    buf1 = new int[1];
    buf2 = new int[1];
    handle = 0;
  }

  public LwjglWindowExtension(String title, int width, int height) {
    this(null, null, title, width, height);
  }

  public void handleAcceptFile(Window window, ImmutableList<String> files) {
  }

  public void handleButton(Window window, ActionType actionType, ButtonType buttonType, int mods) {
    LwjglWindowExtension ext = (LwjglWindowExtension) window.getExtension();
    View result = window.getLayout();
    double[] b1 = new double[1];
    double[] b2 = new double[1];
    GLFW.glfwGetCursorPos(window.getHandle(), b1, b2);
    int hx = (int) b1[0], hy = (int) b2[0];
    
    if (result != null) {
      result = result.hit(hx, hy);
    }
    if (result != null) {
      System.out.println("x:"+hx+", y:"+hy);
      if(actionType == ActionType.Release) {
        for (var l : result.getListener(ClickListener.class)) {
          l.onClickUp(result, window);
        }
      } else if(actionType == ActionType.Press) {
        for (var l : result.getListener(ClickListener.class)) {
          l.onClickDown(result, window);
        }
      }
      for (ButtonListener l : result.getListener(ButtonListener.class)) {
        l.onButton(result, window, actionType, buttonType, mods, hx, hy);
      }
    }
  }
  
  public void handleChar(Window window, char ch) {

  }

  public void handleCharMods(Window window, char ch, int mods) {

  }

  @Override
  public void close(Window window) {
    if (handle > 0) {
      GLFW.glfwDestroyWindow(handle);
      handle = 0;
    }
  }

  public void handleContentScale(Window window, double xscale, double yscale) {

  }

  @Override
  public void create(Window window) {
    if (handle <= 0) {
      System.out.println("Create the window");
      LwjglWindowExtension ext = (LwjglWindowExtension) window.getExtension();
      handle = GLFW.glfwCreateWindow(ext.width, ext.height, ext.title,
          ext.monitor == null ? MemoryUtil.NULL : ext.monitor.getHandle(),
          ext.shareWindow == null ? MemoryUtil.NULL : ext.shareWindow.getHandle());
      setAllListener(window);
      
      GLFW.glfwGetWindowSize(window.getHandle(), this.buf1, this.buf2);
      handleResize(window, buf1[0], buf2[0]);
      GLFW.glfwGetWindowPos(window.getHandle(), this.buf1, this.buf2);
      handleWindowMove(window, buf1[0], buf2[0]);
      
      make();
      GL.createCapabilities();
    }
  }

  int cx, cy;
  
  public void handleCursorMove(Window window, double xpos, double ypos) {
    cx = (int) xpos;
    cy = (int) ypos;
  }

  public void handleEnter(Window window, boolean entered) {

  }

  public void handleFocus(Window window, boolean focused) {

  }

  public void handleFrameBufferResize(Window window, int width, int height) {

  }

  public void handleIconify(Window window, boolean iconified) {

  }

  public void handleKey(Window window, ActionType actionType, int key, int scancode, int mods) {
    LwjglWindowExtension ext = (LwjglWindowExtension) window.getExtension();
    View result = window.getFocus();
    if (result != null) {
      result = result.hit(ext.x, ext.y);
    }
    if (result != null) {
      for (KeyListener l : result.getListener(KeyListener.class)) {
        l.onKey(result, window, actionType, key, scancode, mods);
      }
    }
  }

  public void handleMaximize(Window window, boolean maximized) {

  }
  
  public void handleRefresh(Window window) {

  }
  
  public void handleResize(Window window, double width, double height) {
  }
  
  public void handleScroll(Window window, double offsetX, double offsetY) {

  }
  
  public void handleWindowMove(Window window, double x, double y) {
    LwjglWindowExtension ext = (LwjglWindowExtension) window.getExtension();
    ext.x = (int) x;
    ext.y = (int) y;
  }
  
  protected void setAllListener(Window window) {
    GLFW.glfwSetCharCallback(window.getHandle(), (win, codepoint) -> {
      this.handleChar(window, (char) codepoint);
    });
    GLFW.glfwSetCharModsCallback(window.getHandle(), (win, codepoint, mods) -> {
      this.handleCharMods(window, (char) codepoint, mods);
    });
    GLFW.glfwSetCursorEnterCallback(window.getHandle(), (win, entered) -> {
      this.handleEnter(window, entered);
    });
    GLFW.glfwSetCursorPosCallback(window.getHandle(), (win, xpos, ypos) -> {
      this.handleCursorMove(window, xpos, ypos);
    });
    GLFW.glfwSetDropCallback(window.getHandle(), (win, count, names) -> {
      ImmutableList.Builder<String> paths = ImmutableList.builder();
      for (int i = 0; i < count; i++) {
        paths.add(GLFWDropCallback.getName(names, i));
      }
      this.handleAcceptFile(window, paths.build());
    });
    GLFW.glfwSetFramebufferSizeCallback(window.getHandle(), (win, width, height) -> {
      this.handleFrameBufferResize(window, width, height);
    });
    GLFW.glfwSetKeyCallback(window.getHandle(), (win, key, scancode, action, mods) -> {
      this.handleKey(window, ActionType.from(action), key, scancode, mods);
    });
    GLFW.glfwSetMouseButtonCallback(window.getHandle(), (win, button, action, mods) -> {
      this.handleButton(window, ActionType.from(action), ButtonType.from(button), mods);
    });
    GLFW.glfwSetScrollCallback(window.getHandle(), (win, xoffset, yoffset) -> {
      this.handleScroll(window, xoffset, yoffset);
    });
    GLFW.glfwSetWindowCloseCallback(window.getHandle(), win -> {
      this.close(window);
    });
    GLFW.glfwSetWindowContentScaleCallback(window.getHandle(), (win, xscale, yscale) -> {
      this.handleContentScale(window, xscale, yscale);
    });
    GLFW.glfwSetWindowFocusCallback(window.getHandle(), (win, focused) -> {
      this.handleFocus(window, focused);
    });
    GLFW.glfwSetWindowIconifyCallback(window.getHandle(), (win, iconified) -> {
      this.handleIconify(window, iconified);
    });
    GLFW.glfwSetWindowMaximizeCallback(window.getHandle(), (win, maximized) -> {
      this.handleMaximize(window, maximized);
    });
    GLFW.glfwSetWindowPosCallback(window.getHandle(), (win, xpos, ypos) -> {
      this.handleWindowMove(window, xpos, ypos);
    });
    GLFW.glfwSetWindowRefreshCallback(window.getHandle(), wwinindow -> {
      this.handleRefresh(window);
    });
    GLFW.glfwSetWindowSizeCallback(window.getHandle(), (win, width, height) -> {
      this.handleResize(window, width, height);
    });
  }

  @Override
  public void setTitle(String title) {
    GLFW.glfwSetWindowTitle(this.getHandle(), title);
    this.title = title;
  }

  @Override
  public void make() {
    GLFW.glfwMakeContextCurrent(getHandle());
  }
  
  @Override
  public void unmake() {
    if(GLFW.glfwGetCurrentContext() == getHandle()) {
      GLFW.glfwMakeContextCurrent(0);
    }
  }

  @Override
  public boolean isShouldClose() {
    return GLFW.glfwWindowShouldClose(getHandle());
  }

  @Override
  public void setShouldClose(boolean shouldClose) {
    GLFW.glfwSetWindowShouldClose(getHandle(), shouldClose);
    
  }
}
