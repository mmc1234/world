package com.github.mmc1234.world.toolkit.renderer;

import java.util.Collection;
import java.util.List;

import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import com.github.mmc1234.world.toolkit.MyUtils;
import com.github.mmc1234.world.toolkit.ShaderUtils;
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
  public void render(Window window) {
    ILocalContext context = window.getContext();
    create(window);
    for(IRenderBuffer buffer : window.getContext().getBatch().getBuffers()) {
      buffer.clear();
    }
    context.getCurrentWindow().getRootView().onRender(window);
    List<IRenderBuffer> buffers = context.getBatch().getBuffers();
    int vertexCount = 0;
    for(IRenderBuffer buffer : buffers) {
      vertexCount+=buffer.getVertexCount();
    }
    
    GL30.glUseProgram(program);
    
    // 提交顶点
    int vertex = vertexArray.getBuffer(0);
    GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vertex);
    GL30.glBufferData(GL30.GL_ARRAY_BUFFER, vertexCount*4*4, GL30.GL_DYNAMIC_DRAW);
    
    int offset = 0;
    for(IRenderBuffer buffer : buffers) {
      for(float[] vertexs : buffer.getVertexs()) {
        GL30.glBufferSubData(GL30.GL_ARRAY_BUFFER, offset*4, vertexs);
        System.out.print("Vertex offset:"+offset+";");
        offset+=vertexs.length;
      }
    }
    
    
    // 生成并提交索引
    int index = vertexArray.getBuffer(1);
    offset = 0;
    GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, index);
    GL30.glBufferData(GL30.GL_ARRAY_BUFFER, vertexCount*4, GL30.GL_DYNAMIC_DRAW);
    
    for(IRenderBuffer buffer : buffers) {
      List<float[]> vertexs = buffer.getVertexs();
      List<Object> datas = buffer.getDatas();
      int size = vertexs.size();
      if(size == 0) {
        continue;
      }
      System.out.print("Size:"+size+";");
      for(int i = 0; i<size; i++) {
        float[] va = vertexs.get(i);
        Object data = datas.get(i);
        if(data == EMPTY) {
          int indexArraySize = va.length/4;
          int[] indexs = new int[indexArraySize];
          for(int i1 = 0; i1<indexs.length; i1++) {
            indexs[i1] = -1;
          }
          System.out.print("Index offset:"+offset+";");
          GL30.glBufferSubData(GL30.GL_ARRAY_BUFFER, offset*Type.Int.size, indexs);
          offset+=indexArraySize;
        }
      }
    }
    System.out.println("VertexCount:"+vertexCount);
    GL30.glEnable(GL30.GL_BLEND);
    GL30.glClearColor(0.2f, 0.2f, 0.2f, 1);
    GL30.glClear(GL30.GL_COLOR_BUFFER_BIT);
    GL30.glDrawArrays(GL30.GL_TRIANGLES, 0, vertexCount);
  }

  @Override
  public void create(Window window) {
    if(program == -1) {
      program = ShaderUtils.createProgram(MyUtils.readTestFile("./happy/vs.glsl"), MyUtils.readTestFile("./happy/fs.glsl"));
      vertexArray.create();
    }
  }

  @Override
  public void close(Window window) {
    if(program != -1) {
      program = -1;
      vertexArray.close();
    }
  }

}
