package com.github.mmc1234.world.leg.toolkit.render;

import static org.lwjgl.opengl.GL30.*;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EnumState {
  ColorBuffer(GL_COLOR_BUFFER_BIT, true), ZBuffer(GL_DEPTH_BUFFER_BIT, true), ZWrite(0, false);
  final int value;
  final boolean isNormal;
}
