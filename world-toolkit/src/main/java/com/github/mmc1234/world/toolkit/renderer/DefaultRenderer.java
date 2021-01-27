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

public final class DefaultRenderer implements IRenderer {
  private final TreeSet<Track> trackSet = new TreeSet<Track>((o1, o2) -> o1.layer - o2.layer);
  private final Track[] trackArray = new Track[64];
  private int currentIndex = 0, program = -1, lastVertexCount, vertexCount, currentBaseSize;
  private final VertexArray vertexArray = new VertexArray(Type.Vec2, Type.Vec2, Type.Int);

  @Override
  public void render(ILocalContext context) {
    create(context);
    context.getCurrentWindow().getRootView().onRender(context);
    onResize();
    onUpdateBuffer();
    onRender();
    
    lastVertexCount = vertexCount;
  }
  
  private void onResize() {
    
  }
  private void onUpdateBuffer() {
  }
  private void onRender() {
  }

  @Override
  public void create(ILocalContext context) {
  }

  @Override
  public void close(ILocalContext context) {
    
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