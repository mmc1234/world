package com.github.mmc1234.world.toolkit.gl;

import org.lwjgl.opengl.GL30;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnableTarget {
  Blend(GL30.GL_BLEND, 0);
  final int target;
  final int index;
}
