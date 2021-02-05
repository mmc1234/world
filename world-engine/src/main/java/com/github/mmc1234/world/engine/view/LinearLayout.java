package com.github.mmc1234.world.engine.view;

import com.github.mmc1234.world.window.AlignType;
import com.github.mmc1234.world.window.LayoutMode;
import com.github.mmc1234.world.window.MeasureSpec;
import com.github.mmc1234.world.window.View;
import com.github.mmc1234.world.window.ViewGroup;
import com.github.mmc1234.world.window.ViewTree;
import com.github.mmc1234.world.window.VisibilityType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
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
    int ox = AlignType.getX(getLayoutAlign(), this.getMeasuredWidth(), Math.abs(right - left));
    int oy = AlignType.getY(getLayoutAlign(), this.getMeasuredHeight(), Math.abs(bottom - top));
    if (changed) {
      for (View child : children) {
        if (isHorizontal) {
          ox += child.getPadLeft();
          child.layout(left + ox, left + ox + child.getMeasuredWidth(), top + oy + child.getPadTop(),
              top + oy + child.getPadTop() + child.getMeasuredHeight());
          ox += (child.getMeasuredWidth() + child.getPadRight());
        } else {
          oy += child.getPadTop();
          child.layout(left + ox + child.getPadLeft(), left + ox+child.getPadLeft() + child.getMeasuredWidth(),
              top + oy, top + oy + child.getMeasuredHeight());
          oy += (child.getMeasuredHeight() + child.getPadBottom());
        }
      }
    }
  }

  public boolean isVertical() {
    return !isHorizontal;
  }

  @Override
  protected void onMeasure(MeasureSpec spec, int widthMeasureSpec, int heightMeasureSpec) {
    if (spec == MeasureSpec.Exactly) {
      setMeasuredDimension(totalWidth = widthMeasureSpec, totalWidth = heightMeasureSpec);
      return;
    }

    totalWidth = 0;
    totalHeight = 0;

    for (View child : children) {
      // First do some basic measurements
      if (child.getVisibility() == VisibilityType.Gone) {
        break;
      }
      child.measure(spec, widthMeasureSpec, heightMeasureSpec);
      if (!isEnableMeasure(child)) {
        break;
      }
      if (spec == MeasureSpec.Unspecified) {
        // Apply view padding.
        child.measure(MeasureSpec.Exactly, child.getMeasuredWidth() + child.getPadLeft() + child.getPadRight(),
            child.getMeasuredHeight() + child.getPadTop() + child.getPadBottom());
      } else if (spec == MeasureSpec.AtMost) {
        int giveWidth = 0;
        int giveHeight = 0;
        int mainSize = 0;
        // Apply view padding.
        if (isVertical()) {
          giveWidth = Math.min(widthMeasureSpec,
              Math.abs(child.getMeasuredWidth()) + child.getPadLeft() + child.getPadRight());
          mainSize = Math.max(0, child.getMeasuredHeight()) + child.getPadTop() + child.getPadBottom();
          giveHeight = Math.min(Math.max(heightMeasureSpec - totalHeight, 0), mainSize);
          if (child.getMeasuredWidth() > 0 && child.getMeasuredHeight() > 0) {
            totalHeight += giveHeight;
            totalWidth = Math.min(Math.max(giveWidth, totalWidth), widthMeasureSpec);
          }

        } else if (isHorizontal()) {
          giveHeight = Math.min(heightMeasureSpec,
              Math.abs(child.getMeasuredHeight()) + child.getPadTop() + child.getPadBottom());
          mainSize = Math.max(0, child.getMeasuredWidth()) + child.getPadLeft() + child.getPadRight();
          giveWidth = Math.min(Math.max(widthMeasureSpec - totalWidth, 0), mainSize);
          if (child.getMeasuredWidth() > 0 && child.getMeasuredHeight() > 0) {
            totalWidth += giveWidth;
            totalHeight = Math.min(Math.max(giveHeight, totalHeight), heightMeasureSpec);
          }
        }
      }
    }

    for (View child : children) {
      if (spec == MeasureSpec.AtMost) {
        if (isVertical()) {
          if (child.getMeasuredHeight() < 0 && child.getMeasuredWidth() != 0) {
            child.setMeasuredDimension(child.getMeasuredWidth(), Math.min(heightMeasureSpec - totalHeight, 0));
          }
        } else {
          if (child.getMeasuredWidth() < 0 && child.getMeasuredHeight() != 0) {
            child.setMeasuredDimension(Math.min(widthMeasureSpec - totalWidth, 0), child.getMeasuredHeight());
          }
        }
      }
    }
    View.setDefaultMeasuredDimension(this, spec, widthMeasureSpec, heightMeasureSpec);
  }

  public boolean isEnableMeasure(View view) {
    if (view.getVisibility() == VisibilityType.Gone) {
      return false;
    }
    if (view.getMeasuredHeight() == 0 || view.getMeasuredHeight() == 0) {
      return false;
    }
    return true;
  }
}
