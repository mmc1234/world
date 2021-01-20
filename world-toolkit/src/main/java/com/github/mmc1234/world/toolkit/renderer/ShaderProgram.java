package com.github.mmc1234.world.toolkit.renderer;

import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import com.github.mmc1234.world.toolkit.exception.EmptyException;

import lombok.Getter;

public class ShaderProgram {
  @Getter
  protected int handle;
  
  public void create() {
    handle = GL30.glCreateProgram();
    if (handle == MemoryUtil.NULL) {
      throw new RuntimeException("Empty program");
    }
  }
  
  public static int createShader(int program, int type, String source) {
    int shader = GL30.glCreateShader(type);
    GL30.glShaderSource(shader, source);
    GL30.glCompileShader(shader);
    GL30.glAttachShader(program, shader);
    return shader;
  }
}
