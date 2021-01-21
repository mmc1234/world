package com.github.mmc1234.world.leg2.toolkit.gui;

import com.github.mmc1234.world.leg2.toolkit.Dimension2D;

public class ViewUtils {
  public static void calculate(View view, Dimension2D size, Dimension2D pos) {
    switch (view.sizeType) {
    case Auto:
      Dimension2D defaultSize = new Dimension2D();
      view.defaultSize(defaultSize);
      size.set(Math.min(size.x, defaultSize.x), Math.min(size.x, defaultSize.y));
      break;
    case Pixel:
      size.set(Math.min(size.x, view.width), Math.min(size.y, view.height));
      break;
    case Ratio:
      size.set(size.x*Math.min(view.width, 1), size.y*
          Math.min(view.height, 1));
      break;
    case Full:
      break;
    default:
      break;
    }
  }
  
  public static void reshape(View view, double x, double y, double w, double h) {
    view.actualX = x;
    view.actualY = y;
    view.actualWidth = w;
    view.actualHeight = h;
    view.onReshape(x, y, w, h);
  }
  
  public static boolean hit(View view, double x, double y) {
    return x >= view.actualX && y >= view.actualY && x<= (view.actualX+view.actualWidth) && y<= (view.actualY+view.actualHeight);
  }
}
