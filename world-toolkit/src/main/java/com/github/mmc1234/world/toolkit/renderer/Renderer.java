package com.github.mmc1234.world.toolkit.renderer;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.TreeSet;

import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import com.github.mmc1234.world.toolkit.Dimension2D;
import com.github.mmc1234.world.toolkit.MyUtils;
import com.github.mmc1234.world.toolkit.context.ILocalContext;
import com.github.mmc1234.world.toolkit.gui.View;
import com.github.mmc1234.world.toolkit.renderer.VertexArray.Type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public final class Renderer implements IRenderer {
  private static class DataTexture {
    private @Getter int id = -1;

    public void create() {
      if (id == -1) {
        id = GL30.glGenTextures();
        GL30.glBindTexture(GL30.GL_TEXTURE_2D, id);
        GL30.glPixelStorei(GL30.GL_UNPACK_ALIGNMENT, 1);
        GL30.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_MIN_FILTER, GL30.GL_NEAREST);
        GL30.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_MAG_FILTER, GL30.GL_NEAREST);

        // Upload the texture data
        GL30.glTexImage2D(GL30.GL_TEXTURE_2D, 0, GL30.GL_RGBA, 512, 512, 0, GL30.GL_RGBA, GL30.GL_UNSIGNED_BYTE,
            MemoryUtil.NULL);
      }
    }

    public void close() {
      if (id != -1) {
        if (GL30.glGetInteger(GL30.GL_TEXTURE_BINDING_2D) == id) {
          GL30.glBindTexture(GL30.GL_TEXTURE_2D, 0);
        }
        GL30.glDeleteTextures(id);
      }
    }

    public int put(Track track) {
      // 占据空间
      return 0;
    }
  }

  private final TreeSet<Track> trackSet = new TreeSet<Track>((o1, o2) -> o1.layer - o2.layer);
  private final Track[] trackArray = new Track[64];
  private int currentIndex = 0, program = -1, lastVertexCount, vertexCount, currentBaseSize;
  private final VertexArray vertexArray = new VertexArray(Type.Vec2, Type.Vec2, Type.Int);
  private final @NonNull BufferSizeHandler bufferSizeHandler;
  private final DataTexture uniformBuffer = new DataTexture();

  public Renderer(BufferSizeHandler bufferSizeHandler) {
    this.bufferSizeHandler = bufferSizeHandler;
  }

  public Renderer() {
    this(null);
  }

  @Override
  public void render(ILocalContext context) {
    create(context);

    // 生成网格集合
    trackSet.clear();
    View root = context.getCurrentWindow().getFocusView();
    root.onRender(context);

    // 计算顶点大小
    for (Track t : trackSet) {
      vertexCount = vertexCount + t.view.getMesh().getVertexCount();
    }
    GL30.glUseProgram(program);

    // 计算基本大小
    int lastBaseSize = currentBaseSize;
    currentBaseSize = bufferSizeHandler.handlerSize(currentBaseSize, lastVertexCount, vertexCount);

    // 尝试变更vertex缓冲大小
    int offset = 0;
    int vertex = vertexArray.getBuffer(0);
    GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vertex);
    if (currentBaseSize != lastBaseSize) {
      GL30.glBufferData(GL30.GL_ARRAY_BUFFER, currentBaseSize*4*4, GL30.GL_DYNAMIC_DRAW);
    }

    // 提交 vertex 和 color 等数据
    for (Track t : trackSet) {
      GL30.glBufferSubData(GL30.GL_ARRAY_BUFFER, offset, t.view.getMesh().vertex);
      t.offset = offset;
      offset = offset + uniformBuffer.put(t);
    }

    // 试变更缓冲大小
    int index = vertexArray.getBuffer(1);
    GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, index);
    if (currentBaseSize != lastBaseSize) {
      GL30.glBufferData(GL30.GL_ARRAY_BUFFER, MemoryUtil.NULL, GL30.GL_DYNAMIC_DRAW);
    }

    try (MemoryStack stack = MemoryStack.stackPush()) {
      IntBuffer indexBuffer = stack.mallocInt(512);
      // 提交 index
      for (Track t : trackSet) {
        UIMesh mesh = t.view.getMesh();
        indexBuffer.clear();
        for (int i = 0; i < mesh.getVertexCount(); i++) {
          indexBuffer.put(t.offset/3); // 因为三个顶点共享一份数据
        }
        // 提交这个网格的数据
        GL30.glBufferSubData(GL30.GL_ARRAY_BUFFER, t.offset, (ByteBuffer) indexBuffer.flip());
      }
    }

    GL30.glDrawArrays(GL30.GL_TRIANGLES, 0, vertexCount);
    lastVertexCount = vertexCount;
  }

  @Override
  public void create(ILocalContext context) {
    if (program == -1) {
      program = ShaderUtils.createProgram(MyUtils.readTestFile("ui2/vertexShader.glsl"), "ui2/fragmentShader.glsl");
      vertexArray.create();
      uniformBuffer.create();
      GL30.glActiveTexture(GL30.GL_TEXTURE0);
    }
  }

  @Override
  public void close(ILocalContext context) {
    if (program != -1) {
      GL30.glDeleteProgram(program);
      vertexArray.close();
      uniformBuffer.close();
    }
  }

  @Override
  public void enter(View view, Object mesh) {
    for (int i = 0; i < currentIndex; i++) {
      if (trackArray[i].view == view) {
        return;
      }
    }
    trackArray[currentIndex].view = view;
    trackArray[currentIndex].layer = currentIndex;
    trackSet.add(trackArray[currentIndex++].clone());
  }

  @Override
  public void exit(View view) {
    if (trackArray[currentIndex - 1].view == view) {
      currentIndex--;
      trackArray[currentIndex].view = null;
    }
  }
}
