package com.github.mmc1234.world.lwjgl;

import org.apache.commons.lang.IllegalClassException;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWDropCallback;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.glfw.GLFWImage.Buffer;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

import com.github.mmc1234.world.message.EditableQueryEvent;
import com.github.mmc1234.world.message.QueryEvent;
import com.github.mmc1234.world.message.Subscribe;
import com.github.mmc1234.world.window.ActionType;
import com.github.mmc1234.world.window.ButtonListener;
import com.github.mmc1234.world.window.ButtonType;
import com.github.mmc1234.world.window.CancelClickEvent;
import com.github.mmc1234.world.window.ClickDownEvent;
import com.github.mmc1234.world.window.ClickEvent;
import com.github.mmc1234.world.window.ClickListener;
import com.github.mmc1234.world.window.Cursor;
import com.github.mmc1234.world.window.Dimension2D;
import com.github.mmc1234.world.window.Icon;
import com.github.mmc1234.world.window.KeyListener;
import com.github.mmc1234.world.window.LongClickEvent;
import com.github.mmc1234.world.window.PlatformExtension;
import com.github.mmc1234.world.window.View;
import com.github.mmc1234.world.window.Window;
import com.google.common.collect.ImmutableList;

import lombok.Getter;
import lombok.var;

@Getter
public class LwjglWindowExtension implements PlatformExtension {
  protected int[] buf1, buf2;
  protected LwjglCursor cursor;
  protected long handle;
  protected Icon icon;

  protected Monitor monitor;
  protected EditableQueryEvent queryEvent;
  protected Window shareWindow;
  protected String title;
  protected Window win;

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
    this.buf1 = new int[1];
    this.buf2 = new int[1];
    this.handle = 0;
    this.queryEvent = new EditableQueryEvent();
  }

  public LwjglWindowExtension(String title, int width, int height) {
    this(null, null, title, width, height);
  }

  @Override
  public void close(Window window) {
    if (handle > 0) {
      Window.getLogger().info("LWJGL window extension stoping");
      window.getEventBus().unreigster(this);
      GLFW.glfwDestroyWindow(handle);
      cursor.close();
      cursor = null;
      handle = 0;
    }
  }

  @Override
  public void create(Window window) {
    if (handle <= 0) {
      Window.getLogger().info("LWJGL window extension initialization");
      window.getEventBus().register(this);
      handle = GLFW.glfwCreateWindow(width, height, title, monitor == null ? MemoryUtil.NULL : monitor.getHandle(),
          shareWindow == null ? MemoryUtil.NULL : shareWindow.getHandle());
      setAllListener(window);

      GLFW.glfwGetWindowSize(window.getHandle(), this.buf1, this.buf2);
      handleResize(window, buf1[0], buf2[0]);
      GLFW.glfwGetWindowPos(window.getHandle(), this.buf1, this.buf2);
      handleWindowMove(window, buf1[0], buf2[0]);

      cursor = new LwjglCursor(window);
      cursor.create();

      Window.getLogger().info("LWJGL window cursor initialization:"+cursor);
      
      make();
      GL.createCapabilities();
    }
  }

  public void handleAcceptFile(Window window, ImmutableList<String> files) {
    // TODO
  }

  public void handleButton(Window window, ActionType actionType, ButtonType buttonType, int mods) {
    View result = window.getLayout();
    double[] b1 = new double[1];
    double[] b2 = new double[1];
    GLFW.glfwGetCursorPos(window.getHandle(), b1, b2);
    int hx = (int) b1[0], hy = (int) b2[0];
    if (result != null) {
      result = result.hit(hx, hy);
    }
    if (result != null) {
      if (actionType == ActionType.Release) {
        window.getEventBus().post(queryEvent.setEvent(new ClickEvent(result, window)));
      } else if (actionType == ActionType.Press) {
        window.getEventBus().post(queryEvent.setEvent(new ClickDownEvent(result, window)));
      } else if (actionType == ActionType.Repeat) {
        window.getEventBus().post(queryEvent.setEvent(new LongClickEvent(result, window, GLFW.glfwGetTimerValue())));
      } else {
        throw new IllegalClassException("Event of this type does not exist.");
      }
      for (ButtonListener l : result.getListener(ButtonListener.class)) {
        l.onButton(result, window, actionType, buttonType, mods, hx, hy);
      }
    }
  }

  public void handleChar(Window window, char ch) {
    // TODO
  }

  public void handleCharMods(Window window, char ch, int mods) {
    // TODO
  }

  public void handleContentScale(Window window, double xscale, double yscale) {
    System.out.println("内容缩放??");
  }

  public void handleCursorMove(Window window, double xpos, double ypos) {
    cursor.x = xpos;
    cursor.y = ypos;

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
    View result = window.getFocus();
    if (result != null) {
      result = result.hit((int) window.getCursor().getX(), (int) window.getCursor().getY());
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
    Dimension2D size = window.getViewportSizeBuf();
    if (size.getX() != width || size.getY() != height) {
      window.getViewportSizeBuf().set(width, height);
      window.getViewport(window.getViewportPosBuf(), window.getViewportSizeBuf());
      window.refreshLayout();
    }
  }

  public void handleScroll(Window window, double offsetX, double offsetY) {
    Window.getLogger().info("Scroll:"+offsetX+", "+offsetY);
  }

  public void handleWindowMove(Window window, double x, double y) {
  }

  @Override
  public boolean isShouldClose() {
    return GLFW.glfwWindowShouldClose(getHandle());
  }

  @Override
  public void make() {
    GLFW.glfwMakeContextCurrent(getHandle());
  }

  @Subscribe
  public void onCancelClick(CancelClickEvent event) {
    for (var l : event.view.getListener(ClickListener.class)) {
      l.onCancelClick(event);
    }
  }

  @Subscribe
  public void onClick(ClickEvent event) {
    for (var l : event.view.getListener(ClickListener.class)) {
      l.onClick(event);
    }
  }

  @Subscribe
  public void onClickDown(ClickDownEvent event) {
    for (var l : event.view.getListener(ClickListener.class)) {
      l.onClickDown(event);
    }
  }

  @Subscribe
  public void onClickDown(LongClickEvent event) {
    for (var l : event.view.getListener(ClickListener.class)) {
      l.onLongClick(event);
    }
  }

  protected void setAllListener(Window window) {
    this.win = window;
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
  public void setIcon(Icon icon) {
    this.icon = icon;
    if (icon.getHandle() instanceof GLFWImage.Buffer) {
      GLFW.glfwSetWindowIcon(handle, (Buffer) icon.getHandle());
    } else {
      throw new IllegalArgumentException("LWJGL does not accept this icon.");
    }
  }

  @Override
  public void setPos(int x, int y) {
    GLFW.glfwSetWindowPos(this.getHandle(), x, y);
  }

  @Override
  public void setShouldClose(boolean shouldClose) {
    GLFW.glfwSetWindowShouldClose(getHandle(), shouldClose);
  }

  @Override
  public void setSize(int width, int height) {
    if (this.width != width || this.height != height) {
      GLFW.glfwSetWindowSize(this.getHandle(), width, height);
      GLFW.glfwGetWindowSize(this.getHandle(), this.buf1, this.buf2);
      handleResize(win, buf1[0], buf2[0]);
    }
  }

  @Override
  public void setTitle(String title) {
    GLFW.glfwSetWindowTitle(this.getHandle(), title);
    this.title = title;
  }

  @Override
  public void unmake() {
    if (GLFW.glfwGetCurrentContext() == getHandle()) {
      GLFW.glfwMakeContextCurrent(0);
    }
  }
}
