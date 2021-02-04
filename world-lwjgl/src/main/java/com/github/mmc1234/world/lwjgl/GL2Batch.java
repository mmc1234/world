package com.github.mmc1234.world.lwjgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL30;

import com.github.mmc1234.world.window.Batch;
import com.github.mmc1234.world.window.BufferedDrawItem;
import com.github.mmc1234.world.window.Window;

import lombok.AllArgsConstructor;
import lombok.Setter;

public class GL2Batch implements Batch {
  @AllArgsConstructor
  public class Info {
    private int startTime;
    private int handle;
  }
  private Map<BufferedDrawItem, Info> bufferMap;
  private ArrayList<Info> infoCache;
  private @Setter Window window;
  private int handle, x, y, w, h;
  
  public GL2Batch() {
    infoCache = new ArrayList<>();
    bufferMap = new HashMap<>();
  }
  
  @Override
  public void begin() {
    GL30.glBegin(GL30.GL_TRIANGLES);
  }

  @Override
  public void beginBuffer(BufferedDrawItem bufferedItem) {
    int startTime = (int) (GLFW.glfwGetTimerValue()/(GLFW.glfwGetTimerFrequency()/1000));
    if(!bufferMap.containsKey(bufferedItem)) {
      bufferMap.put(bufferedItem, getInfo(startTime));
    } else {
      bufferMap.get(bufferedItem).startTime = startTime;
    }
    GL30.glBegin(GL30.GL_COMPILE);
  }

  @Override
  public void beginFrame(float r, float g, float b) {
    GL30.glClearColor(r, g, b, 1);
    GL30.glClear(GL30.GL_COLOR_BUFFER_BIT);
  }

  @Override
  public void callBuffer(BufferedDrawItem bufferedItem) {
    GL30.glCallList(bufferMap.get(bufferedItem).handle);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void clean() {
    for(Entry<BufferedDrawItem, Info> entry : bufferMap.entrySet()) {
      int time = (int) (GLFW.glfwGetTimerValue()/(GLFW.glfwGetTimerFrequency()/1000));
      if((time-entry.getValue().startTime) >= entry.getKey().getBufferTimeout()) {
        returnInfo(entry.getValue(), time);
      }
    }
    for(Info info : ((ArrayList<Info>)infoCache.clone())) {
      int time = (int) (GLFW.glfwGetTimerValue()/(GLFW.glfwGetTimerFrequency()/1000));
      if(time-info.startTime > 1000) {
        if(infoCache.remove(info)) {
          GL30.glDeleteLists(info.handle, 1);
        }
      }
    }
  }

  @Override
  public void color(float r, float g, float b) {
    GL30.glColor3f(r, g, b);
  }

  @Override
  public void color(float r, float g, float b, float a) {
    GL30.glColor4f(r, g, b, a);
  }

  @Override
  public void setPixel(boolean isPixel) {
    GL30.glMatrixMode(GL30.GL_PROJECTION);
    GL30.glLoadIdentity();
    if(isPixel) {
      GL30.glScaled(1/window.getWidth(), window.getHeight(), 0);
    }
    GL30.glOrtho(0, 1, 1, 0, 0, 1);
  }
  

  @Override
  public void end() {
    GL30.glEnd();
  }

  @Override
  public void endBuffer(BufferedDrawItem bufferedItem) {
    GL30.glEndList();
  }

  @Override
  public void endFrame() {
    GLFW.glfwSwapBuffers(window.getHandle());
    clean();
  }

  @Override
  public void image(int handle, int x, int y, int w, int h) {
    this.handle = handle;
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }

  @Override
  public void viewport(int x, int y, int w, int h) {
    GL30.glViewport(x, y, w, h);
  }
  
  protected Info getInfo(int startTime) {
    if(infoCache.size() > 0) {
      return infoCache.remove(infoCache.size()-1);
    }
    return new Info(startTime, GL30.glGenLists(1));
  }
  
  protected void returnInfo(Info info, int endTime) {
    if(!infoCache.contains(info)) {
      info.startTime = endTime;
      infoCache.add(info);
    }
  }

  @Override
  public void draw(float x, float y, float tx, float ty) {
    GL30.glTexCoord2f(tx, ty);
    GL30.glVertex2f(x, y);
  }

}
