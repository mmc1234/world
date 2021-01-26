package com.github.mmc1234.world.toolkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

class TestDepthAndBlend {

  @Test
  void test() {
    new BaseGraphTestApp() {
      private int program, vao, colorBuffer, vertexBuffer;
      @Override
      public void init() {
        program = createProgram("depthAndBlend/vertexShader.glsl", "depthAndBlend/fragmentShader.glsl");
        vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);
        vertexBuffer = GL30.glGenBuffers();
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vertexBuffer);
        GL30.glEnableVertexAttribArray(0);
        GL30.glVertexAttribPointer(0, 3, GL30.GL_FLOAT, false, 0, MemoryUtil.NULL);
        colorBuffer = GL30.glGenBuffers();
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, colorBuffer);
        GL30.glEnableVertexAttribArray(1);
        GL30.glVertexAttribPointer(1, 1, GL30.GL_FLOAT, false, 0, MemoryUtil.NULL);
      }

      @Override
      public void render() {
        GL30.glUseProgram(program);
        GL30.glBindVertexArray(vao);
        GL30.glEnable(GL30.GL_BLEND);
        GL30.glEnable(GL30.GL_DEPTH_TEST);
        GL30.glClearColor(0.4f, 0.4f, 0.4f, 1);
        GL30.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vertexBuffer);
        GL30.glBufferData(GL30.GL_ARRAY_BUFFER, 
            new float[]{-1, 1, 0, -1, -1, 0, 1, -1, 0,    -1, 1, 0.1f, -1, -1, 0.1f, 1, 1, 0.1f}, GL30.GL_STREAM_DRAW);
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, colorBuffer);
        GL30.glBufferData(GL30.GL_ARRAY_BUFFER, new float[] {0, 0, 0, 1, 1, 1}, GL30.GL_STREAM_DRAW);
        GL30.glDrawArrays(GL30.GL_TRIANGLES, 0, 6); 
      }
      
    }.run();
  }

}
