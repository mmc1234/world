package com.github.mmc1234.world.window;

import org.apache.log4j.Logger;

import com.github.mmc1234.world.message.CancelableEventBus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Delegate;

@RequiredArgsConstructor

public class Window implements Viewport {
  public static final @Getter Logger logger = Logger.getLogger(Window.class);
  private @Getter final CancelableEventBus eventBus;
  private @Getter @Delegate final PlatformExtension extension;
  private @Getter View focus;
  private @Getter ViewGroup layout;
  /**
   * Please don't touch this thing.
   * */
  private @Getter final Dimension2D viewportPosBuf = new Dimension2D(), viewportSizeBuf = new Dimension2D();

  public Window(PlatformExtension extension) {
    this(new CancelableEventBus("Window"), extension);
  }

  public void close() {
    extension.close(this);
  }

  public void create() {
    extension.create(this);
  }

  @Override
  public void getViewport(Dimension2D pos, Dimension2D size) {
    pos.set(0, 0);
    size.set(getWidth(), getHeight());
  }

  public void refreshLayout() {
    getViewport(viewportPosBuf, viewportSizeBuf);
    if(this.layout !=null) {
      this.layout.measure(MeasureSpec.AtMost, (int) viewportSizeBuf.x, (int) viewportSizeBuf.y);
      this.layout.layout((int) viewportPosBuf.x, this.layout.getMeasuredWidth(), (int) viewportPosBuf.y,
          this.layout.getMeasuredHeight());
    }
  }

  public void setFocus(View focus) {
    View lastFocus = this.focus;
    if (focus != lastFocus) {
      for (FocusChangedListener l : lastFocus.getListener(FocusChangedListener.class)) {
        l.onFocusChanged(lastFocus, false);
      }
      if (focus.isFocusable()) {
        for (FocusChangedListener l : focus.getListener(FocusChangedListener.class)) {
          l.onFocusChanged(focus, true);
        }
      }
    }

  }

  public void setLayout(ViewGroup layout) {
    if (this.layout != layout) {
      this.layout = layout;
      refreshLayout();
    }
  }
}