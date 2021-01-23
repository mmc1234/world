package com.github.mmc1234.world.leg2.toolkit.window;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWDropCallback;
import org.lwjgl.glfw.GLFWDropCallbackI;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

import com.github.mmc1234.world.leg2.toolkit.Dimension2D;
import com.github.mmc1234.world.leg2.toolkit.gui.ClickEvent;
import com.github.mmc1234.world.leg2.toolkit.gui.View;
import com.github.mmc1234.world.leg2.toolkit.gui.ViewUtils;
import com.github.mmc1234.world.leg2.toolkit.window.listener.IWindowListener;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.common.io.CharStreams;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.With;
import lombok.experimental.Helper;

/**
 * @author mmc1234 窗口包含了一些方法，来使用某些功能 对于某些属性，使用了监听来更新，这节约了查询开销
 */
@Getter
public class Window {
  private long clickTime, keyTime;
  private ILocalContext context;
  private @Getter Cursor cursor;

  private long handle = MemoryUtil.NULL;

  private boolean isFocus;

  private Monitor monitor;

  private View rootView, focusView, holdView;
  private Window shareWindow;
  private String title;

  private WillCancelClickEvent willCancelClickEvent;

  private WillClickEvent willClickEvent;
  private WillLongClickEvent willLongClickEvent;

  private int x, y, width, height;

  public Window() {
    this(null, null, "World", 600, 400);
  }

  public Window(Window inShare, Monitor inMonitor, String inTitle, int inWidth, int inHeight) {
    this.shareWindow = inShare;
    this.monitor = inMonitor;
    this.title = inTitle;
    this.width = inWidth;
    this.height = inHeight;
    cursor = new Cursor(this);
    willClickEvent = new WillClickEvent();
    willCancelClickEvent = new WillCancelClickEvent();
    willLongClickEvent = new WillLongClickEvent();
  }

  public void checkEmpty() {
    if (isEmpty()) {
      throw new RuntimeException("Empty window");
    }
  }
  
  protected void createWindow() {
    if (isEmpty()) {
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

  public void setFocusView(View inFocus) {
    View lastFocus = focusView;
    if (lastFocus != null) {
      lastFocus.onFocusExit(this.context);
    }
    if (inFocus != null) {
      inFocus.onFocusEnter(this.context);
    }
    focusView = inFocus;
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

  public void setRootView(View inRoot) {
    if (inRoot == null) {
      rootView = null;
    } else if (inRoot != rootView) {
      rootView = inRoot;
      Dimension2D size = new Dimension2D(width, height), pos = new Dimension2D(0, 0);
      rootView.calculate(size, pos);
      ViewUtils.reshape(rootView, pos.x, pos.y, size.x, size.y);
    }
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

    GLFW.glfwSetWindowSizeCallback(this.handle, (window, width, height) -> {
      // int lastWidth = this.width;
      // int lastHeight = this.height;
      this.width = width;
      this.height = height;
    });
    GLFW.glfwSetWindowFocusCallback(this.handle, (window, focused) -> {
      isFocus = focused;
    });
    GLFW.glfwSetMouseButtonCallback(this.handle, (window, button, action, mods) -> {
      double hx = getCursor().x, hy = getCursor().y;
      View result = getRootView().onHit(this.context, hx, hy);
      ButtonType buttonType = ButtonType.from(button);
      ActionType actionType = ActionType.from(action);

      if (result != null) {
        if (actionType == ActionType.Press) {
          this.holdView = result;
          clickTime = GLFW.glfwGetTimerValue();
        } else if (actionType == ActionType.Release) {
          View lastHoldView = this.holdView;
          holdView = null;
          if (result == lastHoldView) {
            willClickEvent.setEvent(new ClickEvent(this, hx, hy, buttonType));
            this.context.getEventBus().post(willClickEvent);
            if (!willClickEvent.isCancel()) {
              result.onClick(willClickEvent.getEvent());
            }
          } else {
            result.onCancelClick(willCancelClickEvent.getEvent());
          }
        } else {
          result.onLongClick(willLongClickEvent.getEvent());
        }
        result.onButton(this.context, actionType, buttonType, hx, hy, mods);
      }
    });

    GLFW.glfwSetKeyCallback(this.handle, (window, key, scancode, action, mods) -> {
      double hx = getCursor().x, hy = getCursor().y;
      ActionType actionType = ActionType.from(action);
      if (actionType == ActionType.Release) {
        keyTime = -1;
      } else if (actionType == ActionType.Press) {
        keyTime = GLFW.glfwGetTimerValue();
      }
      if (focusView != null) {
        focusView.onKey(this.context, actionType, hx, hy, key, GLFW.glfwGetTimerValue() - keyTime, scancode, mods);
      }
    });

    GLFW.glfwSetCharCallback(this.handle, (window, codepoint) -> {
      double hx = getCursor().x, hy = getCursor().y;
      focusView.onInput(this.context, (char) codepoint, hx, hy);
    });
    GLFW.glfwSetCursorPosCallback(this.handle, (window, xpos, ypos) -> {
      cursor.x = xpos;
      cursor.y = ypos;
    });
    GLFW.glfwSetDropCallback(this.handle, (window, count, names) -> {
      ImmutableList.Builder<String> paths = ImmutableList.builder();
      for (int i = 0; i < count; i++) {
        paths.add(GLFWDropCallback.getName(names, i));
      }
      double hx = getCursor().x, hy = getCursor().y;
      View result = getRootView().onHit(this.context, hx, hy);
      if (result != null) {
        result.onDropFile(this.context, paths.build());
      }
    });
  }

  public void stop() {
    if (!isEmpty()) {
      GLFW.glfwDestroyWindow(this.handle);
      if (context != null) {
        context.onWindowDestry(this);
      }
      cursor.destryAll();
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
}