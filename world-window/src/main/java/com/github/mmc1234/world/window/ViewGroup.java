package com.github.mmc1234.world.window;

import java.util.ArrayList;

public class ViewGroup extends View {
  protected final ArrayList<View> children;

  public ViewGroup() {
    children = new ArrayList<View>();
  }

  @Override
  protected void onAttachedToWindow(Window win) {
    super.onAttachedToWindow(win);
    for (View view : children) {
      view.onAttachedToWindow(win);
    }
  }

  @Override
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    for (View view : children) {
      view.onDetachedFromWindow();
    }
  }

  public void addView(View view) {
    if (children.add(view)) {
      view.onAttachedToWindow(this.window);
    }
  }

  public void addView(View view, int index) {
    // Check index out of bounds.
    if (index < children.size()) {
      children.add(index, view);
      view.onAttachedToWindow(this.window);
    } else {
      addView(view);
    }
  }

  public void removeView(View view) {
    if (children.remove(view)) {
      view.onDetachedFromWindow();
    }
  }

  public View getView(int index) {
    // Check index out of bounds.
    if (index < children.size()) {
      return children.get(index);
    }
    return null;
  }
  
  @Override
  public View onHit(int x, int y) {
    View result = null;
    for (View view : children) {
      result = view.hit(x, y);
    }
    return result;
  }

  @SuppressWarnings("unchecked")
  public ArrayList<View> getChildren() {
    return (ArrayList<View>) children.clone();
  }
  
  public static int getDefaultChildWidth(View view, MeasureSpec spec, int measureSize) {
    if(spec == MeasureSpec.Unspecified) {
      return measureSize;
    } else if(spec == MeasureSpec.AtMost){
      return view.getSuggestedMinimumWidth();
    } else {
      return view.getMeasuredWidth();
    }
  }
  
  public static int getDefaultChildHeight(View view, MeasureSpec spec, int measureSize) {
    if(spec == MeasureSpec.Unspecified) {
      return measureSize;
    } else if(spec == MeasureSpec.AtMost){
      return view.getSuggestedMinimumHeight();
    } else {
      return view.getMeasuredHeight();
    }
  }
}
