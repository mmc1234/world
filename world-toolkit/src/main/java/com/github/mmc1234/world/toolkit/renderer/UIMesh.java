package com.github.mmc1234.world.toolkit.renderer;

import lombok.SneakyThrows;

public final class UIMesh {
  float[] vertex; // vertex

  int[] color; // triagnle
  String[] bitmap; // triagnle
  float[] rect; // triagnle

  public int getVertexCount() {
    return vertex.length;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private UIMesh source;
    private int size;

    public Builder() {
      source = new UIMesh();
      source.vertex = new float[4 * 128];
      source.color = new int[128];
      source.bitmap = new String[128];
    }

    public Builder create() {
      size = 0;
      return color(0x000000ff);
    }

    public Builder color(int inColor) {
      source.color[size / 3] = inColor;
      return this;
    }

    public Builder bitmap(String bitmap) {
      source.bitmap[size / 3] = bitmap;
      return this;
    }

    public Builder vertex(float x, float y, float tx, float ty) {
      source.vertex[size * 4] = x;
      source.vertex[size * 4 + 1] = y;
      source.vertex[size * 4 + 2] = tx;
      source.vertex[size * 4 + 3] = ty;
      return this;
    }

    public Builder next() {
      size++;
      return this;
    }

    @SneakyThrows
    public UIMesh build() {
      UIMesh mesh = (UIMesh) new UIMesh();
      mesh.vertex = new float[4*size];
      mesh.bitmap = new String[size];
      mesh.color = new int[size];
      System.arraycopy(source.vertex, 0, mesh.vertex, 0, size);
      System.arraycopy(source.bitmap, 0, mesh.bitmap, 0, size);
      System.arraycopy(source.color, 0, mesh.color, 0, size);
      return mesh;
    }
  }
}
