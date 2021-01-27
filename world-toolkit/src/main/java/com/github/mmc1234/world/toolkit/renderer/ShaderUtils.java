package com.github.mmc1234.world.toolkit.renderer;

import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.lwjgl.opengl.GL30;

import lombok.SneakyThrows;

public class ShaderUtils {
  @SneakyThrows
  public static int createProgram(String vs, String fs) {
    int program = GL30.glCreateProgram();
    int vertexShader = GL30.glCreateShader(GL30.GL_VERTEX_SHADER);
    int fragmentShader = GL30.glCreateShader(GL30.GL_FRAGMENT_SHADER);
    GL30.glShaderSource(vertexShader, vs);
    GL30.glShaderSource(fragmentShader, fs);
    GL30.glCompileShader(vertexShader);
    GL30.glCompileShader(fragmentShader);
    GL30.glAttachShader(program, vertexShader);
    GL30.glAttachShader(program, fragmentShader);
    GL30.glValidateProgram(program);
    GL30.glLinkProgram(program);
    GL30.glDeleteShader(vertexShader);
    GL30.glDeleteShader(fragmentShader);
    System.out.println(GL30.glGetProgramInfoLog(program));
    return program;
  }
}
