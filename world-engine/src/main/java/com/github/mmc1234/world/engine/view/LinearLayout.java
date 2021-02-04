package com.github.mmc1234.world.engine.view;

import com.github.mmc1234.world.window.LayoutMode;
import com.github.mmc1234.world.window.MeasureSpec;
import com.github.mmc1234.world.window.View;
import com.github.mmc1234.world.window.ViewGroup;

import lombok.Getter;
import lombok.Setter;

public class LinearLayout extends ViewGroup {

  private @Setter @Getter boolean isHorizontal;
  private int totalWidth, totalHeight;

  @Override
  public int getSuggestedMinimumHeight() {
    return totalHeight;
  }

  @Override
  public int getSuggestedMinimumWidth() {
    return totalWidth;
  }

  @Override
  protected void onLayout(boolean changed, int left, int right, int top, int bottom) {
    if (changed) {
      for (View view : children) {
        if (isHorizontal) {

        } else {

        }
      }
    }
  }

  @Override
  protected void onMeasure(MeasureSpec spec, int widthMeasureSpec, int heightMeasureSpec) {
    if (spec == MeasureSpec.Exactly) {
      setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
      return;
    }
    // First do some basic measurements
    totalWidth = 0;
    totalHeight = 0;
    for (View child : children) {
      child.measure(spec, widthMeasureSpec, heightMeasureSpec);
      if (spec == MeasureSpec.Unspecified) {
        if (isHorizontal) {
          totalWidth = totalWidth + child.getMeasuredWidth() + child.getPadLeft() + child.getPadRight();
          totalHeight = Math.max(totalHeight, child.getMeasuredHeight() + child.getPadTop() + child.getPadBottom());
        } else {
          totalWidth = Math.max(totalWidth, child.getMeasuredWidth() + child.getPadLeft() + child.getPadRight());
          totalHeight = totalHeight + child.getMeasuredHeight() + child.getPadTop() + child.getPadBottom();
        }
      } else if (widthMeasureSpec > 0 && heightMeasureSpec > 0) {
        // Calculate the available area.
        if (isHorizontal) {
          int freeArea = Math.min(Math.max(0, widthMeasureSpec - totalWidth),
              child.getMeasuredWidth() + child.getPadTop() + child.getPadBottom());
          if (freeArea == 0) {
            totalHeight = Math.min(
                Math.max(totalHeight, child.getMeasuredHeight() + child.getPadTop() + child.getPadBottom()),
                heightMeasureSpec);
            totalWidth = totalWidth + freeArea + child.getPadLeft() + child.getPadRight();
            child.measure(MeasureSpec.Exactly, freeArea, totalHeight);
          } else {
            child.measure(MeasureSpec.Exactly, 0, 0);
          }
        } else {
          int freeArea = Math.min(Math.max(0, heightMeasureSpec - totalHeight), child.getMeasuredHeight());
          if (height == 0) {
            totalWidth = Math.min(
                Math.max(totalWidth, child.getMeasuredWidth() + child.getPadLeft() + child.getPadRight()),
                widthMeasureSpec);
            totalHeight = totalHeight + freeArea + child.getPadTop() + child.getPadBottom();
            child.measure(MeasureSpec.Exactly, freeArea, totalWidth);
          } else {
            child.measure(MeasureSpec.Exactly, 0, 0);
          }
        }
      }
    }
    // A view that handles the match mode.
    for (View child : children) {
      if (spec == MeasureSpec.AtMost) {
        int childWidth = child.getMeasuredWidth();
        int childHeight = child.getMeasuredHeight();
        if (totalWidth >= widthMeasureSpec && totalHeight >= heightMeasureSpec) {
          break;
        }
        if (childWidth < 0 && totalWidth >= widthMeasureSpec) {
          childWidth = widthMeasureSpec - totalWidth;
          totalWidth = widthMeasureSpec;
        }
        if (childHeight < 0 && totalHeight >= heightMeasureSpec) {
          childHeight = heightMeasureSpec - totalHeight;
          totalHeight = heightMeasureSpec;
        }
        child.measure(MeasureSpec.Exactly, childWidth, childHeight);
      }
    }
    setMeasuredDimension(totalWidth, totalHeight);
  }
}
