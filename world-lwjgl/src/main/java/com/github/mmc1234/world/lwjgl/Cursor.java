package com.github.mmc1234.world.lwjgl;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWImage;

import com.github.mmc1234.world.window.Window;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Cursor {
  @Getter
  protected double x, y;
  private Map<String, Long> cursorImages = new HashMap<>();
  private Window window;

  public Cursor(Window inWindow) {
    this.window = inWindow;
  }

  public boolean createCursorImage(String name, GLFWImage image, int xhot, int yhot) {
    if (!cursorImages.containsKey(name)) {
      cursorImages.put(name, GLFW.glfwCreateCursor(image, xhot, yhot));
      return true;
    }
    return false;
  }

  public boolean createStdCursorImage(StdType type) {
    if (!cursorImages.containsKey(type.name())) {
      cursorImages.put(type.name(), GLFW.glfwCreateStandardCursor(type.shape));
      return true;
    }
    return false;
  }

  protected void destryAll() {
    for (long address : cursorImages.values()) {
      GLFW.glfwDestroyCursor(address);
    }
    cursorImages.clear();
  }

  public void setCurrentImage(String name) {
    if (cursorImages.containsKey(name)) {
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
}
