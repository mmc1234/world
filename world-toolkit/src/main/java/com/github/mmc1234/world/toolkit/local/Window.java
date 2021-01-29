package com.github.mmc1234.world.toolkit.local;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWDropCallback;
import org.lwjgl.glfw.GLFWErrorCallbackI;
import org.lwjgl.system.MemoryUtil;

import com.github.mmc1234.world.message.EditableQueryEvent;
import com.github.mmc1234.world.message.QueryEvent;
import com.github.mmc1234.world.toolkit.Dimension2D;
import com.github.mmc1234.world.toolkit.enumerate.ActionType;
import com.github.mmc1234.world.toolkit.enumerate.ButtonType;
import com.github.mmc1234.world.toolkit.event.CancelClickEvent;
import com.github.mmc1234.world.toolkit.event.ClickEvent;
import com.github.mmc1234.world.toolkit.event.LongClickEvent;
import com.github.mmc1234.world.toolkit.exception.EmptyException;
import com.github.mmc1234.world.toolkit.gui.View;
import com.github.mmc1234.world.toolkit.gui.ViewUtils;
import com.google.common.collect.ImmutableList;

import lombok.Getter;

/**
 * @author mmc1234 窗口减肥了，啧啧
 */
@Getter
public class Window {
  protected int[] buf1 = new int[1], buf2 = new int[1];
  protected long clickTime, keyTime, handle = MemoryUtil.NULL;
  protected ILocalContext context;
  protected boolean isFocus;
  protected @Getter Cursor cursor;
  protected Monitor monitor;

  protected View rootView, focusView, holdView, stayView;
  protected Window shareWindow;
  protected String title;
  protected int x, y, width, height;
  private final IWindowExceptionHandler exceptionHandler;
  private final IWindowEventHandler eventHandler;

  public Window() {
    this("World", 600, 400);
  }

  public Window(String inTitle, int inWidth, int inHeight) {
    this(null, null, "World", 600, 400);
  }
  
  public Window(Window inShare, Monitor inMonitor, String inTitle, int inWidth, int inHeight) {
    this(new WindowExceptionHandler(), new WindowEventHandler(), inShare, inMonitor, "World", 600, 400);
  }

  public Window(IWindowExceptionHandler errorHandler, IWindowEventHandler eventHandler, Window inShare, Monitor inMonitor,
      String inTitle, int inWidth, int inHeight) {
    this.shareWindow = inShare;
    this.monitor = inMonitor;
    this.title = inTitle;
    this.width = inWidth;
    this.height = inHeight;
    cursor = new Cursor(this);

    this.exceptionHandler = errorHandler;
    this.eventHandler = eventHandler;
  }

  public void checkEmpty() {
    if (isEmpty()) {
      exceptionHandler.handleException(new EmptyException("Empty window"));
    }
  }

  protected void createWindow() {
    if (isEmpty()) {
      handle = GLFW.glfwCreateWindow(width, height, title, (monitor == null ? MemoryUtil.NULL : monitor.handle),
          (shareWindow == null ? MemoryUtil.NULL : shareWindow.handle));
    }
  }

  public void setFocus() {
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
    try {
      if (lastFocus != null) {
        lastFocus.onFocusExit(this);
      }
      if (inFocus != null) {
        inFocus.onFocusEnter(this);
      }
    } catch (OutOfMemoryError oom) {
      throw oom;
    } catch (Exception e) {
      exceptionHandler.handleException(e);
    }
    focusView = inFocus;
  }

  public void setRootView(View inRoot) {
    if (inRoot == null) {
      rootView = null;
    } else if (inRoot != rootView) {
      rootView = inRoot;
      Dimension2D size = new Dimension2D(width, height), pos = new Dimension2D(0, 0);
      try {
        rootView.calculate(size, pos);
        ViewUtils.reshape(rootView, pos.x, pos.y, size.x, size.y);
      } catch (OutOfMemoryError oom) {
        throw oom;
      } catch (Exception e) {
        exceptionHandler.handleException(e);
      }
    }
  }

  public void setShouldClose(boolean shouldClose) {
    GLFW.glfwSetWindowShouldClose(this.handle, shouldClose);
  }

  public void start() {
    createWindow();
    checkEmpty();
    GLFW.glfwSetCharCallback(this.handle, (window, codepoint) -> {
      eventHandler.handleInput(this, (char) codepoint);
    });
    GLFW.glfwSetCharModsCallback(this.handle, (window, codepoint, mods) -> {
      eventHandler.handleInputMods(this, (char) codepoint, mods);
    });
    GLFW.glfwSetCursorEnterCallback(this.handle, (window, entered) -> {
      eventHandler.handleEnter(this, entered);
    });
    GLFW.glfwSetCursorPosCallback(this.handle, (window, xpos, ypos) -> {
      eventHandler.handleMoveCursor(this, xpos, ypos);
    });
    GLFW.glfwSetDropCallback(this.handle, (window, count, names) -> {
      ImmutableList.Builder<String> paths = ImmutableList.builder();
      for (int i = 0; i < count; i++) {
        paths.add(GLFWDropCallback.getName(names, i));
      }
      eventHandler.handleAcceptFile(this, paths.build());
    });
    GLFW.glfwSetFramebufferSizeCallback(this.handle, (window, width, height) -> {
      eventHandler.handleFrameBufferResize(this, width, height);
    });
    GLFW.glfwSetKeyCallback(this.handle, (window, key, scancode, action, mods) -> {
      eventHandler.handleKey(this, ActionType.from(action), key, scancode, mods);
    });
    GLFW.glfwSetMouseButtonCallback(this.handle, (window, button, action, mods) -> {
      eventHandler.handleButton(this, ActionType.from(action), ButtonType.from(button), mods);
    });
    GLFW.glfwSetScrollCallback(this.handle, (window, xoffset, yoffset) -> {
      eventHandler.handleScroll(this, xoffset, yoffset);
    });
    GLFW.glfwSetWindowCloseCallback(this.handle, window -> {
      eventHandler.handleClose(this);
    });
    GLFW.glfwSetWindowContentScaleCallback(this.handle, (window, xscale, yscale) -> {
      eventHandler.handleContentScale(this, xscale, yscale);
    });
    GLFW.glfwSetWindowFocusCallback(this.handle, (window, focused) -> {
      eventHandler.handleFocus(this, focused);
    });
    GLFW.glfwSetWindowIconifyCallback(this.handle, (window, iconified) -> {
      eventHandler.handleIconify(this, iconified);
    });
    GLFW.glfwSetWindowMaximizeCallback(this.handle, (window, maximized) -> {
      eventHandler.handleMaximize(this, maximized);
    });
    GLFW.glfwSetWindowPosCallback(this.handle, (window, xpos, ypos) -> {
      eventHandler.handleMoveCursor(this, xpos, ypos);
    });
    GLFW.glfwSetWindowRefreshCallback(this.handle, window -> {
      eventHandler.handleRefresh(this);
    });
    GLFW.glfwSetWindowSizeCallback(this.handle, (window, width, height) -> {
      eventHandler.handleResize(this, width, height);
    });
    GLFW.glfwGetWindowPos(this.handle, buf1, buf2);
    this.x = buf1[0];
    this.y = buf2[0];
    GLFW.glfwGetWindowSize(this.handle, buf1, buf2);
    this.width = buf1[0];
    this.height = buf2[0];
  }

  public void stop() {
    try {
      if (!isEmpty()) {
        GLFW.glfwDestroyWindow(this.handle);
        if (context != null) {
          context.onWindowDestry(this);
        }
        cursor.destryAll();
        this.handle = MemoryUtil.NULL;

      }
    } catch (OutOfMemoryError oom) {
      throw oom;
    } catch (Exception e) {
      exceptionHandler.handleException(e);
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