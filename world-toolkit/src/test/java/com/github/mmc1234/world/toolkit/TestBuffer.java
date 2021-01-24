package com.github.mmc1234.world.toolkit;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import lombok.SneakyThrows;

class TestBuffer {

  @Test
  void test() {
    new BaseGraphTestApp() {
      private int program, vao, pos, clip;

      @Override
      @SneakyThrows
      public void init() {
        program = createProgram("clip/vertexShader.glsl", "clip/fragmentShader.glsl");
        vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);
        pos = GL30.glGenBuffers();
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, pos);
        GL30.glEnableVertexAttribArray(0);
        GL30.glVertexAttribPointer(0, 2, GL30.GL_FLOAT, false, 0, MemoryUtil.NULL);
        GL30.glBufferData(GL30.GL_ARRAY_BUFFER, new float[] { -1, 1, -1, -1, 1, 1, 1, 1, -1, -1, 1, -1 },
            GL30.GL_STATIC_DRAW);
        clip = GL30.glGenBuffers();
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, clip);
        GL30.glEnableVertexAttribArray(1);
        GL30.glVertexAttribPointer(1, 4, GL30.GL_FLOAT, false, 0, MemoryUtil.NULL);
        GL30.glBufferData(
            GL30.GL_ARRAY_BUFFER, new float[] { 100, 100, 400, 400, 100, 100, 400, 400, 100, 100, 400, 400, 100, 100,
                400, 400, 100, 100, 400, 400, 100, 100, 400, 400, 100, 100, 400, 400, 100, 100, 400, 400 },
            GL30.GL_STATIC_DRAW);
      }

      @Override
      public void render() {
        GL30.glUseProgram(program);
        GL30.glEnable(GL30.GL_BLEND);
        GL30.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        GL30.glClearColor(0.4f, 0.4f, 0.4f, 1);
        GL30.glClear(GL30.GL_COLOR_BUFFER_BIT);
        GL30.glDrawArrays(GL30.GL_TRIANGLES, 0, 6);
      }
    }.run();
    ;

  }

}
