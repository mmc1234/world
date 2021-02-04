package com.github.mmc1234.world.toolkit.gl;

import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import lombok.Getter;

public class VertexArray {
  @Getter
  private int vao;
  private int[] attribArray;
  private Type[] typeArray;
  public VertexArray(Type...attribs) {
    typeArray = attribs;
  }
  
  public void create() {
    if(attribArray == null) {
      attribArray = new int[typeArray.length];
      vao = GL30.glGenVertexArrays();
      GL30.glBindVertexArray(vao);
      for(int i = 0; i<attribArray.length; i++) {
        int buffer = attribArray[i] = GL30.glGenBuffers();
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, buffer);
        GL30.glEnableVertexAttribArray(i);
        GL30.glVertexAttribPointer(i, typeArray[i].size, typeArray[i].type, false, 0, MemoryUtil.NULL);
      }
    }
  }
  
  public void close() {
    boolean isUnbind = false;
    if(attribArray != null) {
      for(int i = 0; i<attribArray.length; i++) {
        if(!isUnbind && attribArray[i] == GL30.glGetInteger(GL30.GL_ARRAY_BUFFER_BINDING)) {
          GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, 0);
          isUnbind = true;
        }
        GL30.glDeleteBuffers(attribArray[i]);
      }
      attribArray = null;
      if(vao == GL30.glGetInteger(GL30.GL_VERTEX_ARRAY_BINDING)) {
        GL30.glDeleteVertexArrays(vao);
      }
    }
  }
  
  public int getBuffer(int index) {
    return attribArray[index];
  }
}
