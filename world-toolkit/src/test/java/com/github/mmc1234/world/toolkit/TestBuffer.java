package com.github.mmc1234.world.toolkit;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import com.github.mmc1234.world.toolkit.renderer.UIMesh;

import lombok.SneakyThrows;
import lombok.var;

class TestBuffer {

  @Test
  void test() {
    new BaseGraphTestApp() {
      private int program, vao, pos, clip, rect;
      private int lastVertexCount = 0;

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
        clip = GL30.glGenBuffers();
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, clip);
        GL30.glEnableVertexAttribArray(1);
        GL30.glVertexAttribPointer(1, 4, GL30.GL_FLOAT, false, 0, MemoryUtil.NULL);
        for(int i = 0; i<20; i++) {
          for(int j = 0; j<20; j++) {
            list.add(new UIMesh(new float[] {-1, 1, -1, -1, 1, 1, 1, 1, -1, -1, 1, -1}, new float[] {i, j, i+i*10, j+j*10}, new float[] {i, j, i+i*10, j+j*10}));
          }
        }
      }
      ArrayList<UIMesh> list = new ArrayList<UIMesh>();
      @Override
      public void render() {
        int vertexCount = 0;
        for(var m:list) {
          vertexCount+=m.getVertexCount();
        }
        GL30.glUseProgram(program);
        GL30.glEnable(GL30.GL_BLEND);
        GL30.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        GL30.glClearColor(0.4f, 0.4f, 0.4f, 1);
        GL30.glClear(GL30.GL_COLOR_BUFFER_BIT);
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, pos);
        GL30.glBufferData(GL30.GL_ARRAY_BUFFER, vertexCount*2*4, GL30.GL_STATIC_DRAW);
        
        int offset = 0;
        for(var m:list) {
          GL30.glBufferSubData(GL30.GL_ARRAY_BUFFER, offset, m.pos);
          offset=offset+m.getVertexCount()*2*4;
        }
        offset = 0;
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, clip);
        GL30.glBufferData(GL30.GL_ARRAY_BUFFER, vertexCount*4*4, GL30.GL_STATIC_DRAW);
        for(var m:list) {
          int meshVertexCount = m.getVertexCount();
          for(int i = 0; i<meshVertexCount; i++) {
            GL30.glBufferSubData(GL30.GL_ARRAY_BUFFER, offset, m.clip);
            offset+=4*4;
          }
        }
        GL30.glDrawArrays(GL30.GL_TRIANGLES, 0, vertexCount);
      }
    }.run();

  }

}
