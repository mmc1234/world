package com.github.mmc1234.world.window;

public interface Batch {
  public void begin();
  public void beginBuffer(BufferedDrawItem bufferedItem);
  public void beginFrame(float r, float g, float b);
  public void callBuffer(BufferedDrawItem bufferedItem);
  public void clean();
  public void color(float r, float g, float b);
  public void color(float r, float g, float b, float a);
  public void draw(float x, float y, float tx, float ty);
  public void end();
  public void endBuffer(BufferedDrawItem bufferedItem);
  public void endFrame();
  public void image(int handle, int x, int y, int w, int h);
  public void viewport(int x, int y, int w, int h);
  public void setPixel(boolean isPixel);
}
