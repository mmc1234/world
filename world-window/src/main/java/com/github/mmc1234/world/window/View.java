package com.github.mmc1234.world.window;

import java.util.Collection;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class View {
  public static int getDefaultSize(MeasureSpec spec, LayoutMode mode, int pixelSize, int size, int measureSpec, double ratio) {
    System.out.println(mode);
    switch (mode) {
    case Match:
      if (spec == MeasureSpec.Unspecified) {
        return -measureSpec;
      } else {
        return -size;
      }
    case Ratio:
      // Is floating-point loss possible?
      return (int) (measureSpec * ratio);
    case Pixel:
      return pixelSize;
    case Wrap:
      return size;
    }
    return 0;
  }

  protected @Setter boolean isFocusable;
  protected @Setter AlignType layoutAlign;
  protected @Setter LayoutMode layoutWidth, layoutHeight;
  protected int left, right, top, bottom;
  protected Multimap<Class<?>, Object> listenerList;

  protected int measuredWidth, measuredHeight;
  protected int padLeft, padRight, padTop, padBottom;
  protected double ratioW, ratioH;
  protected @Setter VisibilityType visibility;

  protected Window window;

  protected int x, y, width, height;

  public View() {
    listenerList = HashMultimap.create();
    setFocusable(false);
    setVisibility(VisibilityType.Visible);
    setLayoutAlign(AlignType.Center);
    setLayoutWidth(LayoutMode.Wrap);
    setLayoutHeight(LayoutMode.Wrap);
  }
  
  public void setX(int x) {
    this.x = x<0?0:x;
  }
  
  public void setY(int y) {
    this.y = y<0?0:y;
  }
  
  public void setWidth(int width) {
    this.width = width <0 ? 0 : width;
  }

  public void setHeight(int height) {
    this.height = height <0 ? 0 : height;
  }
  
  public final void addListener(Class<?> listenerType, @NonNull Object l) {
    if (listenerType.isAssignableFrom(l.getClass())) {
      listenerList.put(listenerType, l);
    }
  }

  @SuppressWarnings("unchecked")
  public final <T> Collection<T> getListener(@NonNull Class<T> listenerType) {
    return (Collection<T>) listenerList.get(listenerType);
  }

  public int getSuggestedMinimumHeight() {
    return 0;
  }

  public int getSuggestedMinimumWidth() {
    return 0;
  }

  public final View hit(int x, int y) {
    if (x >= left && x <= right && y >= top && y <= bottom) {
      return onHit(x, y);
    }
    return null;
  }

  /**
   * The second phase of measurement.
   */
  public void layout(int left, int right, int top, int bottom) {
    boolean changed = this.left != left | this.right != right | this.top != top | this.bottom != bottom;
    this.left = left;
    this.right = right;
    this.top = top;
    this.bottom = bottom;
    onLayout(changed, left, right, top, bottom);
  }

  /**
   * This method is called by the layout to measure the child.If you need to
   * measure, you just override the onMeasure.
   */
  public final void measure(MeasureSpec spec, int widthMeasureSpec, int heightMeasureSpec) {
    if (visibility == VisibilityType.Gone) {
      setMeasuredDimension(0, 0);
      return;
    }
    switch (spec) {
    case Unspecified: // Slider view, you can apply any size, so you need to send events.
    case AtMost:
      onMeasure(spec, widthMeasureSpec, heightMeasureSpec);
      setMeasuredDimension(measuredWidth > widthMeasureSpec ? widthMeasureSpec : measuredWidth,
          measuredHeight > heightMeasureSpec ? heightMeasureSpec : measuredHeight);
      break;
    case Exactly:
      setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
      break;
    }
  }

  protected void onAttachedToWindow(Window window) {
    this.window = window;
  }

  protected void onDetachedFromWindow() {
    this.window = null;
  }

  public View onHit(int x, int y) {
    return this;
  }

  protected void onLayout(boolean changed, int left, int right, int top, int bottom) {
  }

  protected void onMeasure(MeasureSpec spec, int widthMeasureSpec, int heightMeasureSpec) {
    setMeasuredDimension(getDefaultSize(spec, getLayoutWidth(), width,  getSuggestedMinimumWidth(), widthMeasureSpec, ratioW),
        getDefaultSize(spec, getLayoutHeight(), height, getSuggestedMinimumHeight(), heightMeasureSpec, ratioH));
  }

  public final void removeListener(Class<?> listenerType, @NonNull Object l) {
    if (listenerType.isAssignableFrom(l.getClass())) {
      listenerList.remove(listenerType, l);
    }
  }

  public final void setMeasuredDimension(int measuredWidth, int measuredHeight) {
    this.measuredWidth = measuredWidth;
    this.measuredHeight = measuredHeight;
  }
}
