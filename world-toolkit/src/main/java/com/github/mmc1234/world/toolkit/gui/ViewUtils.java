package com.github.mmc1234.world.toolkit.gui;

import com.github.mmc1234.world.toolkit.Dimension2D;
import com.github.mmc1234.world.toolkit.bitmap.IBitmap;
import com.github.mmc1234.world.toolkit.bitmap.IBitmapManager;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ViewUtils {
  @SuppressWarnings("incomplete-switch")
  public static void calculate(View view, Dimension2D size, Dimension2D pos) {
    Dimension2D defaultSize = new Dimension2D();
    view.defaultSize(defaultSize);
    
    switch (view.wType) {
    case Auto:
      size.x = Math.min(size.x, defaultSize.y);
      break;
    case Pixel:
      size.x = Math.min(size.x, view.width);
      break;
    case Ratio:
      size.x = size.x*Math.min(Math.max(view.width, 0), 1);
      break;
    }
    switch (view.hType) {
    case Auto:
      size.y = Math.min(size.y, defaultSize.y);
      break;
    case Pixel:
      size.y = Math.min(size.y, view.height);
      break;
    case Ratio:
      size.y = size.y*Math.min(Math.max(view.height, 0), 1);
      break;
    }
    switch (view.xType) {
    case Ratio:
      pos.x = pos.x+size.x*Math.min(Math.max(pos.x, 0), 1);
      break;
    default:
      pos.x = pos.x+Math.min(Math.min(pos.x, 0), size.x);
      break;
    }
    switch (view.yType) {
    case Ratio:
      pos.y = pos.y+size.y*Math.min(Math.max(pos.y, 0), 1);
      break;
    default:
      pos.y = pos.y+Math.min(Math.min(pos.y, 0), size.y);
      break;
    }
  }
  
  public static void reshape(View view, double x, double y, double w, double h) {
    if(view.actualX != x || view.actualY != y || view.actualWidth != w || view.actualHeight != h) {
      view.actualX = x;
      view.actualY = y;
      view.actualWidth = w;
      view.actualHeight = h;
      view.onReshape(x, y, w, h);
    }
  }
  
  public static boolean hit(View view, double x, double y) {
    return x >= view.actualX && y >= view.actualY && x<= (view.actualX+view.actualWidth) && y<= (view.actualY+view.actualHeight);
  }
  
  public static void getBitmap(IBitmapManager bitmapManager, String[] bitmapNames, IBitmap[] bitmaps) {
    int size = Math.min(bitmaps.length, bitmapNames.length);
    for(int i = 0; i<size; i++) {
      bitmaps[i] = bitmapManager.get(bitmapNames[i]);
    }
  }
}
