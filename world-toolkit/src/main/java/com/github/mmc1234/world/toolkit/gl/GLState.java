package com.github.mmc1234.world.toolkit.gl;

class GLState {
  protected int vertexArrayBinding;
  protected int arrayBufferBinding;
  protected int frameBufferBinding;
  protected int readFrameBufferBinding;
  protected int drawFrameBufferBinding;
  protected int texture2DBinding;
  protected int programBinging = -1;
  protected int[] textureActiveArray;
  protected boolean[] enableArray; //alpha test, blend, depth test
  protected float clearRed,clearGreen, clearBlue, clearAlpha;
  
  public GLState() {
    textureActiveArray = new int[32];
    enableArray = new boolean[64];
  }
}
