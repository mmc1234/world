package com.github.mmc1234.world.engine.view;

import com.github.mmc1234.world.window.LayoutMode;
import com.github.mmc1234.world.window.MeasureSpec;
import com.github.mmc1234.world.window.View;
import com.github.mmc1234.world.window.ViewGroup;

public class LinearLayout extends ViewGroup {

  private int minWidth, minHeight;

  @Override
  protected void onLayout(boolean changed, int left, int right, int top, int bottom) {
    if (changed) {
      for (View view : children) {
        
      }
    }
  }

  @Override
  protected void onMeasure(MeasureSpec spec, int widthMeasureSpec, int heightMeasureSpec) {
    int totalWidth = 0;
    int totalHeight = 0;
    for(View view : children) {
      view.measure(spec, widthMeasureSpec, heightMeasureSpec);
      if(view.getMeasuredWidth() <= 0 || view.getMeasuredHeight() <= 0) {
        break;
      }
      totalWidth = totalWidth + getDefaultChildWidth(view, spec, widthMeasureSpec);
      totalHeight = totalHeight + getDefaultChildHeight(view, spec, heightMeasureSpec);
    }
    minWidth = totalWidth;
    minHeight = totalHeight;
  }

  @Override
  public int getSuggestedMinimumWidth() {
    return minWidth;
  }

  @Override
  public int getSuggestedMinimumHeight() {
    return minHeight;
  }
}
