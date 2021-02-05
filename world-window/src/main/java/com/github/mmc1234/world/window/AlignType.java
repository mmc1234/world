package com.github.mmc1234.world.window;

public enum AlignType {
  LeftTop, Top, RightTop, LeftCenter, Center, RightCenter, LeftBottom, Bottom, RightBottom;

  public static int getX(AlignType type, int size, int maxSize) {
    switch (type) {
    case Top:
    case Bottom:
    case Center:
      return Math.abs(maxSize - size) / 2;
    case RightTop:
    case RightCenter:
    case RightBottom:
      return Math.abs(maxSize - size);
    default:
      return 0;
    }
  }

  public static int getY(AlignType type, int size, int maxSize) {
    switch (type) {
    case LeftCenter:
    case Center:
    case RightCenter:
      return Math.abs(maxSize - size) / 2;
    case LeftBottom:
    case Bottom:
    case RightBottom:
      return Math.abs(maxSize - size);
    default:
      return 0;
    }

  }
}
