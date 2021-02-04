package com.github.mmc1234.world.toolkit.gl;

import org.lwjgl.opengl.GL30;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Type {
  Int(1, GL30.GL_INT), Float(1, GL30.GL_FLOAT), Vec2(2, GL30.GL_FLOAT), Vec3(3, GL30.GL_FLOAT), Vec4(4, GL30.GL_FLOAT);
  public final int size, type;
}