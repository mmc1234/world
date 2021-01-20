package com.github.mmc1234.world.leg.toolkit.render;

import java.lang.reflect.Method;

import org.apache.commons.lang.reflect.MethodUtils;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL30C;

import lombok.NonNull;
import lombok.SneakyThrows;

public interface GL {
  public void activeTexture(int unit);
  public void attachShader(int program, int shader);
  public void bindFramebuffer(EnumFramebuffer target, int framebuffer);
  public void bindRenderbuffer(int renderbuffer);
  public void bindVertexArray(int vao);
  public void bindBuffer(EnumBuffer target, int buffer);
  public void bindTexture(EnumTexture target, int texture);
  public void blendFunc(EnumBlendFunc sourceFactor, EnumBlendFunc destinationFactor);
  public void bufferData(EnumBuffer target, EnumDraw usage, Object data);
  public void bufferDataSize(EnumBuffer target, EnumDraw usage, long size);
  public void bufferSubData(EnumBuffer target, long offset, Object data);
  public void bind();
  public void disable(EnumState state);
  public void enable(EnumState state);
}
