package com.github.mmc1234.world.toolkit.renderer;

import com.github.mmc1234.world.toolkit.context.ILocalContext;

import lombok.Getter;

public class DebugRenderer extends AbstractRenderer {
  boolean isInit = true;
  protected @Getter IGlyph glyph = new DebugGlyph();
  @Override
  public void create(ILocalContext context) {
    if (isInit) {
      isInit = false;
      //System.out.println("Create");
    }
  }

  @Override
  public void close(ILocalContext context) {
    if (!isInit) {
      isInit = true;
      //System.out.println("Close");
    }
  }

  @Override
  public void malloc() {
    //System.out.println("Malloc");
  }

  @Override
  public void update() {
    //System.out.println("Update");
  }

  @Override
  public void draw() {
    System.out.println("Draw:(VertexCount:" + vertexCount + ")");
  }

}
