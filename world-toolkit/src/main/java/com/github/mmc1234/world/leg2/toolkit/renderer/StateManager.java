package com.github.mmc1234.world.leg2.toolkit.renderer;

import org.lwjgl.opengl.GL30;

import com.github.mmc1234.world.leg2.toolkit.exception.RepeatBindException;
import com.github.mmc1234.world.leg2.toolkit.exception.UnableBindException;

public class StateManager {
  private int currentProgram = -1, currentVAO = -1, currentVBO = -1;
  public boolean isRepair;
  public StateManager() {
    this(false);
  }
  public StateManager(boolean isRepair) {
    this.isRepair = isRepair;
  }
  public void bindProgram(int program) throws RepeatBindException {
    if(currentProgram != program) {
      GL30.glUseProgram(program);
      currentProgram = program;
    } else {
      throw new RepeatBindException(program);
    }
  }
  
  public void bindVAO(int vao) throws UnableBindException {
    if(vao !=currentVAO) {
      GL30.glBindVertexArray(vao);
    }
    if(isRepair) {
      // 初步检查，防止外部修改导致的意外
      if(GL30.glGetInteger(GL30.GL_VERTEX_ARRAY_BINDING) != vao) {
        GL30.glBindVertexArray(vao);
        // 再次确认
        int real = GL30.glGetInteger(GL30.GL_VERTEX_ARRAY_BINDING);
        if(real != vao) {
          throw new UnableBindException(vao, real, currentVAO);
        }
      }
    }
    currentVAO = vao;
  }
  
  public void bindVBO(int vbo) throws UnableBindException {
    if(currentVBO != vbo) {
      GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vbo);
    }
    if(isRepair) {
      // 初步检查，防止外部修改导致的意外
      if(GL30.glGetInteger(GL30.GL_ARRAY_BUFFER_BINDING) != vbo) {
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vbo);
        // 再次确认
        int real = GL30.glGetInteger(GL30.GL_ARRAY_BUFFER_BINDING);
        if(real != vbo) {
          throw new UnableBindException(vbo, real, currentVAO);
        }
      }
    }
    currentVBO = vbo;
  }
  
}
