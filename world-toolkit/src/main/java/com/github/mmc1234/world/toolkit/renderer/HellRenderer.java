package com.github.mmc1234.world.toolkit.renderer;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import com.github.mmc1234.world.toolkit.MyUtils;
import com.github.mmc1234.world.toolkit.Rect;
import com.github.mmc1234.world.toolkit.context.ILocalContext;
import com.github.mmc1234.world.toolkit.renderer.VertexArray.Type;

public final class HellRenderer extends AbstractRenderer {

  private int program = -1, bufferSize, meshBuffer;
  private VertexArray vertexArray = new VertexArray(Type.Vec4, Type.Int);
  private ByteBuffer buffer;

  @Override
  public void create(ILocalContext context) {
    if(program == -1) {
      program = ShaderUtils.createProgram(MyUtils.readTestFile("ui2/vertexShader.glsl"), MyUtils.readTestFile("ui2/fragmentShader.glsl"));
      vertexArray.create();
      meshBuffer = GL30.glGenTextures();
      buffer = MemoryUtil.memAlloc(512*512*4);
      GL30.glBindTexture(GL30.GL_TEXTURE_2D, meshBuffer);
      GL30.glPixelStorei(GL30.GL_UNPACK_ALIGNMENT, 1);
      GL30.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_MIN_FILTER, GL30.GL_NEAREST);
      GL30.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_MAG_FILTER, GL30.GL_NEAREST);
      GL30.glTexImage2D(GL30.GL_TEXTURE_2D, 0, GL30.GL_RGBA, 512, 512, 0, GL30.GL_RGBA, GL30.GL_UNSIGNED_BYTE, MemoryUtil.NULL);
    }
  }

  @Override
  public void close(ILocalContext context) {
    if(program != -1) {
      program = -1;
      vertexArray.close();
      GL30.glDeleteTextures(meshBuffer);
      MemoryUtil.memFree(buffer);
    }
  }

  @Override
  public void malloc() {
    if(bufferSize < vertexCount) {
      bufferSize = vertexCount*2;
      GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vertexArray.getBuffer(0));
      GL30.glBufferData(GL30.GL_ARRAY_BUFFER, bufferSize*4*4, GL30.GL_DYNAMIC_DRAW);
      GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vertexArray.getBuffer(1));
      GL30.glBufferData(GL30.GL_ARRAY_BUFFER, bufferSize*1*4, GL30.GL_DYNAMIC_DRAW);
    }
  }

  @Override
  public void update() {
    GL30.glBindTexture(GL30.GL_TEXTURE_2D, meshBuffer);
    GL30.glActiveTexture(GL30.GL_TEXTURE0);
    buffer.clear();
    GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vertexArray.getBuffer(1));
    updateBitmap();
    for(Track track:trackSet) {
      UIMesh mesh = track.view.getMesh();
      for(int i = 0; i<mesh.getVertexCount()/3; i++) {
        buffer.putShort(mesh.rect[i*8]);
        buffer.putShort(mesh.rect[i*8+1]);
        buffer.putShort(mesh.rect[i*8+2]);
        buffer.putShort(mesh.rect[i*8+3]);
        buffer.putShort(mesh.rect[i*8+4]);
        buffer.putShort(mesh.rect[i*8+5]);
        buffer.putShort(mesh.rect[i*8+6]);
        buffer.putShort(mesh.rect[i*8+7]);
        buffer.putInt(mesh.bitmap[i].getHandle());
        buffer.putInt(mesh.color[i]);
      }
      // 提交index写在这里
    }
    GL30.glTexSubImage2D(GL30.GL_TEXTURE_2D, 0, 0, 0, 512, 512, GL30.GL_RGBA, GL30.GL_UNSIGNED_BYTE, (ByteBuffer) buffer.flip());
    int vertexOffset = 0;
    for(Track track:trackSet) {
      UIMesh mesh = track.view.getMesh();
      GL30.glBufferSubData(GL30.GL_ARRAY_BUFFER, vertexOffset, mesh.newVertex);
      vertexOffset = vertexOffset+mesh.newVertex.length*4;
    }
  }

  @Override
  public void draw() {
    GL30.glUseProgram(program);
    GL30.glBindVertexArray(vertexArray.getVao());
    GL30.glEnable(GL30.GL_BLEND);
    GL30.glDrawArrays(GL30.GL_TRIANGLES, 0, vertexCount);
  }
  
  public void updateBitmap() {
    
  }

}
