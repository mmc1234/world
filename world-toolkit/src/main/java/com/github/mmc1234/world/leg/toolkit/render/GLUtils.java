package com.github.mmc1234.world.leg.toolkit.render;

import org.lwjgl.opengl.GL30;

public class GLUtils {
  public static int createShader(int program, int type, String source) {
    int shader = GL30.glCreateShader(type);
    GL30.glShaderSource(shader, source);
    GL30.glCompileShader(shader);
    GL30.glAttachShader(program, shader);
    return shader;
  }
  
}
