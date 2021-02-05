package com.github.mmc1234.world.lwjgl;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWImage;

import com.github.mmc1234.world.window.Cursor;
import com.github.mmc1234.world.window.Window;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class LwjglCursor implements Cursor {
  protected @Getter double x, y;
  private Map<String, Long> cursorImages = new HashMap<>();
  private Window window;
  private boolean isInit;
  private @Getter String current;

  public LwjglCursor(Window inWindow) {
    this.window = inWindow;
    current = null;
  }

  public boolean createImage(String name, GLFWImage image, int xhot, int yhot) {
    if (isInit && !cursorImages.containsKey(name)) {
      cursorImages.put(name, GLFW.glfwCreateCursor(image, xhot, yhot));
      return true;
    }
    return false;
  }
  
  @Override
  public void use(String name) {
    if (isInit && cursorImages.containsKey(name)) {
      GLFW.glfwSetCursor(window.getHandle(), cursorImages.get(name));
    }
  }

  @AllArgsConstructor
  public enum StdType {
    Arrow(GLFW.GLFW_ARROW_CURSOR), Ibeam(GLFW.GLFW_IBEAM_CURSOR), Crosshair(GLFW.GLFW_CROSSHAIR_CURSOR),
    Hand(GLFW.GLFW_HAND_CURSOR), HResize(GLFW.GLFW_HRESIZE_CURSOR), VResize(GLFW.GLFW_VRESIZE_CURSOR);
    @Getter
    public final int shape;
  }

  @Override
  public void create() {
    isInit = true;
    for(StdType st : StdType.values()) {
      cursorImages.put(st.name(), GLFW.glfwCreateStandardCursor(st.shape));
    }
  }

  @Override
  public void close() {
    isInit = false;
    for (long address : cursorImages.values()) {
      GLFW.glfwDestroyCursor(address);
    }
    cursorImages.clear();
  }
}
