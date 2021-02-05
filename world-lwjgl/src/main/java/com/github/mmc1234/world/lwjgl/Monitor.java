package com.github.mmc1234.world.lwjgl;

import java.util.ArrayList;

import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWVidMode.Buffer;
import org.lwjgl.system.Pointer;

import com.github.mmc1234.world.window.Dimension2D;
import com.google.common.collect.ImmutableList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.var;

/**
 * Alias display or screen.
 * Thanks to Mojang and Minecraft, otherwise I would have missed VideoModeList.
 * 
 */
@Getter
public class Monitor {
  protected long handle;
  protected String name;
  protected Dimension2D workPos, workSize;
  protected ArrayList<VideoMode> videoModeList;
  protected VideoMode defaultVideoMode;
  protected int virtualX, virtualY;

  @AllArgsConstructor
  public class VidMode implements VideoMode {
    GLFWVidMode mode;
    @Override
    public int getRefreshRate() {
      return mode.refreshRate();
    }

    @Override
    public int getWidth() {
      return mode.width();
    }

    @Override
    public int getHeight() {
      return mode.height();
    }

    @Override
    public int getRedBits() {
      return mode.redBits();
    }

    @Override
    public int getGreenBits() {
      return mode.greenBits();
    }

    @Override
    public int getBlueBits() {
      return mode.blueBits();
    }
  }
  
  public Monitor() {
    workPos = new Dimension2D();
    workSize = new Dimension2D();
    videoModeList = new ArrayList<VideoMode>();
  }

  /**
   * Update its own video mode. This method can only be called in window.
   */
  public void setup() {
    this.videoModeList.clear();
    Buffer buffer = GLFW.glfwGetVideoModes(this.handle);
    for(int i = buffer.limit() - 1; i >= 0; --i) {
       buffer.position(i);
       VideoMode videoMode = new VidMode(buffer.get());
       if (videoMode.getRedBits() >= 8 && videoMode.getGreenBits() >= 8 && videoMode.getBlueBits() >= 8) {
          videoModeList.add(videoMode);
       }
    }

    int[] ax = new int[1];
    int[] ay = new int[1];
    GLFW.glfwGetMonitorPos(this.handle, ax, ay);
    this.virtualX = ax[0];
    this.virtualY = ay[0];
    this.defaultVideoMode = new VidMode(GLFW.glfwGetVideoMode(this.handle));
  }

  /**
   * Returns all Monitor, with the main Monitor at index 0. This function depends
   * on the window thread.
   * 
   * @return The returned set is always immutable.If the Monitor is not found, an
   *         empty collection is returned
   */
  public static ImmutableList<Monitor> getMonitorList() {
    PointerBuffer pb = GLFW.glfwGetMonitors();
    int size = pb.sizeof() / Pointer.POINTER_SIZE;
    if (size <= 0) {
      return ImmutableList.of();
    }
    var builder = ImmutableList.<Monitor>builder();
    int[] xp = new int[1], yp =new int[1], wp= new int[1], hp= new int[1];
    for (int i = 0; i < size; i++) {
      Monitor monitor = new Monitor();
      monitor.handle = pb.get(i);
      monitor.name = GLFW.glfwGetMonitorName(monitor.handle);
      GLFW.glfwGetMonitorWorkarea(monitor.handle, xp, yp, wp, hp);
      monitor.workPos.set(xp[0], yp[0]);
      monitor.workSize.set(wp[0], hp[0]);
      builder.add(monitor);
    }
    return builder.build();
  }
}
