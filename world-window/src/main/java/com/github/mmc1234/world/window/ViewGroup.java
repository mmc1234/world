package com.github.mmc1234.world.window;

import java.util.ArrayList;

import lombok.var;

public class ViewGroup extends View implements ViewTree.OnDrawListener, ViewTree.OnPreDrawListener, ClickListener {
  protected final ArrayList<View> children;

  public ViewGroup() {
    children = new ArrayList<View>();
    this.addListener(ViewTree.OnPreDrawListener.class, this);
    this.addListener(ViewTree.OnDrawListener.class, this);

  }

  @Override
  public void onDraw(View view, Batch batch) {
    for (View child : children) {
      for (ViewTree.OnDrawListener l : child.getListener(ViewTree.OnDrawListener.class)) {
        l.onDraw(child, batch);
      }
    }
  }

  @Override
  public void onPreDraw(View view, Batch batch) {
    for (View child : children) {
      for (ViewTree.OnPreDrawListener l : child.getListener(ViewTree.OnPreDrawListener.class)) {
        l.onPreDraw(child, batch);
      }
    }
  }

  @Override
  public void onCancelClick(View view, Window window) {
    for (View child : children) {
      for (var l : child.getListener(ClickListener.class)) {
        l.onCancelClick(view, window);
      }
    }
  }
  
  @Override
  public void onClickUp(View view, Window window) {
    for (View child : children) {
      for (var l : child.getListener(ClickListener.class)) {
        l.onClickUp(view, window);
      }
    }
  }
  
  @Override
  public void onClickDown(View view, Window window) {
    for (View child : children) {
      for (var l : child.getListener(ClickListener.class)) {
        l.onClickUp(view, window);
      }
    }
  }
  
  @Override
  public void onLongClick(View view, Window window, long time) {
    for (View child : children) {
      for (var l : child.getListener(ClickListener.class)) {
        l.onLongClick(view, window, time);
      }
    }
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
}
