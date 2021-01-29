package com.github.mmc1234.world.toolkit.renderer;

import java.util.Collection;
import java.util.List;

import javax.activation.UnsupportedDataTypeException;

import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import com.github.mmc1234.world.toolkit.MyUtils;
import com.github.mmc1234.world.toolkit.ShaderUtils;
import com.github.mmc1234.world.toolkit.bitmap.IBitmap;
import com.github.mmc1234.world.toolkit.gui.ViewMesh;
import com.github.mmc1234.world.toolkit.local.ILocalContext;
import com.github.mmc1234.world.toolkit.local.Window;
import com.github.mmc1234.world.toolkit.renderer.VertexArray.Type;

public class GL30Renderer implements IRenderer {
  public static final Object EMPTY = new Object() {
    @Override
    public boolean equals(final Object obj) {
      return obj == this;
    }
  };
  int program = -1;
  private final VertexArray vertexArray;
  
  public GL30Renderer() {
    vertexArray = new VertexArray(Type.Vec4, Type.Int);
  }
  
  @Override
  public void close(Window window) {
    if(program != -1) {
      program = -1;
      vertexArray.close();
    }
  }

  @Override
  public void create(Window window) {
    if(program == -1) {
      program = ShaderUtils.createProgram(MyUtils.readTestFile("./happy/vs.glsl"), MyUtils.readTestFile("./happy/fs.glsl"));
      vertexArray.create();
    }
  }

  @Override
  public void render(Window window) {
    ILocalContext context = window.getContext();
    int vertexCount = 0;
    create(window);
    List<IRenderBuffer> buffers = context.getBatch().getBuffers();
    for(IRenderBuffer buffer : buffers) {
      buffer.clear();
    }
    window.getRootView().onRender(window);
    for(IRenderBuffer buffer : buffers) {
      vertexCount+=buffer.getVertexCount();
    }
    
    GL30.glUseProgram(program);
    GL30.glBindVertexArray(vertexArray.getVao());
    
    /**
     * bind vertex
     * */
    
    GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER,  vertexArray.getBuffer(0));
    GL30.glBufferData(GL30.GL_ARRAY_BUFFER, vertexCount*Type.Vec4.size*4, GL30.GL_STREAM_DRAW);
    
    System.out.println(vertexCount*Type.Vec4.size*4);
    System.out.println(vertexCount*Type.Float.size*4);
    
    /**
     * update
     * */
    
    int offset = 0;
    for(IRenderBuffer buffer : buffers) {
      for(ViewMesh mesh : buffer.getMeshList()) {
        float[] vertexArray = mesh.getVertex();
        GL30.glBufferSubData(GL30.GL_ARRAY_BUFFER, offset*Type.Float.size, vertexArray);
        offset = offset+vertexArray.length;
      }
    }
    
    /**
     * bind texture
     * */
    
    GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vertexArray.getBuffer(1));
    GL30.glBufferData(GL30.GL_ARRAY_BUFFER, vertexCount*Type.Int.size*4, GL30.GL_STREAM_DRAW);
    
    /**
     * update
     * */
    
    offset = 0;
    for(IRenderBuffer buffer : buffers) {
      for(ViewMesh mesh : buffer.getMeshList()) {
        int[] textureArray = new int[mesh.getBitmap().length];
        IBitmap[] bitmapArray = mesh.getBitmap();
        for(int i = 0; i<textureArray.length; i++) {
          textureArray[i] = bitmapArray[i] == null ? 0 : bitmapArray[i].getHandle();
        }
        GL30.glBufferSubData(GL30.GL_ARRAY_BUFFER, offset*Type.Int.size, textureArray);
        offset = offset+textureArray.length;
      }
    }
    
    /**
     * draw
     * */
    
    System.out.println("VertexCount:"+vertexCount);
    
    GL30.glEnable(GL30.GL_BLEND);
    GL30.glClearColor(0.2f, 0.2f, 0.2f, 1);
    GL30.glClear(GL30.GL_COLOR_BUFFER_BIT);
    GL30.glDrawArrays(GL30.GL_TRIANGLES, 0, vertexCount);
  }

}
