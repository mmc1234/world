package com.github.mmc1234.world.toolkit.window;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWDropCallback;
import org.lwjgl.system.MemoryUtil;

import com.github.mmc1234.world.message.EditableQueryEvent;
import com.github.mmc1234.world.message.QueryEvent;
import com.github.mmc1234.world.toolkit.Dimension2D;
import com.github.mmc1234.world.toolkit.context.ILocalContext;
import com.github.mmc1234.world.toolkit.enumerate.ActionType;
import com.github.mmc1234.world.toolkit.enumerate.ButtonType;
import com.github.mmc1234.world.toolkit.event.CancelClickEvent;
import com.github.mmc1234.world.toolkit.event.ClickEvent;
import com.github.mmc1234.world.toolkit.event.LongClickEvent;
import com.github.mmc1234.world.toolkit.gui.View;
import com.github.mmc1234.world.toolkit.gui.ViewUtils;
import com.google.common.collect.ImmutableList;

import lombok.Getter;

/**
 * @author mmc1234 窗口减肥了，啧啧
 */
@Getter
public final class Window {
  protected int[] buf1 = new int[1], buf2 = new int[1];
  private long clickTime, keyTime, handle = MemoryUtil.NULL;
  protected ILocalContext context;
  protected boolean isFocus;
  private @Getter Cursor cursor;
  protected Monitor monitor;
  private EditableQueryEvent queryEvent;
  private View rootView, focusView, holdView;
  private Window shareWindow;
  protected String title;
  protected int x, y, width, height;

  public Window() {
    this("World", 600, 400);
  }

  public Window(String inTitle, int inWidth, int inHeight) {
    this(null, null, "World", 600, 400);
  }

  public Window(Window inShare, Monitor inMonitor, String inTitle, int inWidth, int inHeight) {
    this.shareWindow = inShare;
    this.monitor = inMonitor;
    this.title = inTitle;
    this.width = inWidth;
    this.height = inHeight;
    cursor = new Cursor(this);
    queryEvent = new EditableQueryEvent();
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

  public boolean isEmpty() {
    return this.handle == MemoryUtil.NULL;
  }

  public boolean isShouldClose() {
    return isEmpty() || GLFW.glfwWindowShouldClose(this.handle);
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

  public void start() {
    createWindow();
    checkEmpty();
    GLFW.glfwSetWindowSizeCallback(this.handle, (window, width, height) -> {
      if(this.width != width | this.height != height) {
        if(getRootView()!=null) {
          Dimension2D size = new Dimension2D(width, height), pos = new Dimension2D(0, 0);
          rootView.calculate(size, pos);
          ViewUtils.reshape(rootView, pos.x, pos.y, size.x, size.y);
        }
      }
      this.width = width;
      this.height = height;
    });
    GLFW.glfwSetWindowFocusCallback(this.handle, (window, focused) -> {
      isFocus = focused;
    });
    GLFW.glfwSetMouseButtonCallback(this.handle, (window, button, action, mods) -> {
      if (getRootView() == null) {
        return;
      }
      double hx = getCursor().x, hy = getCursor().y;
      View result = getRootView().onHit(this.context, hx, hy);
      ButtonType buttonType = ButtonType.from(button);
      ActionType actionType = ActionType.from(action);

      View lastHoldView = this.holdView;

      if (result != null) {
        if (actionType == ActionType.Press) {
          this.holdView = result;
          clickTime = GLFW.glfwGetTimerValue();
        } else if (actionType == ActionType.Release) {
          holdView = null;
          if (result == lastHoldView) {
            queryEvent.setEvent(new ClickEvent(this, hx, hy, buttonType));
            this.context.getEventBus().post(queryEvent);
            if (!queryEvent.getEvent().isCancel()) {
              result.onClick((ClickEvent) queryEvent.getEvent());
            }
          } else {
            queryEvent.setEvent(new CancelClickEvent(this, hx, hy, buttonType, lastHoldView));
            this.context.getEventBus().post(queryEvent);
            if (!queryEvent.getEvent().isCancel()) {result.onCancelClick((CancelClickEvent) queryEvent.getEvent());
            }
          }
        } else {
          queryEvent.setEvent(new LongClickEvent(this, hx, hy, buttonType, lastHoldView));
          this.context.getEventBus().post(queryEvent);
          result.onLongClick((LongClickEvent) queryEvent.getEvent());
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
    GLFW.glfwGetWindowPos(this.handle, buf1, buf2);
    this.x = buf1[0];
    this.y = buf2[0];
    GLFW.glfwGetWindowSize(this.handle, buf1, buf2);
    this.width = buf1[0];
    this.height = buf2[0];
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