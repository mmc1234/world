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
public class Window {
  private @Getter @Delegate final PlatformExtension extension;
  private @Getter final CancelableEventBus eventBus;
  private @Getter ViewGroup layout;
  private @Getter View focus;
  public static final @Getter Logger logger = Logger.getLogger(Window.class);
  
  public Window(PlatformExtension extension) {
    this(extension, new CancelableEventBus("Window"));
  }

  public void close() {
    extension.close(this);
  }

  public void create() {
    extension.create(this);
  }
  
  public void setLayout(ViewGroup layout) {
    if(this.layout != layout) {
      this.layout = layout;
      refreshLayout();
    }
  }
  
  public void refreshLayout() {
    this.layout.measure(MeasureSpec.AtMost, getWidth(), getHeight());
    this.layout.layout(0, this.layout.getMeasuredWidth(), 0, this.layout.getMeasuredHeight());
  }

  public void setFocus(View focus) {
    View lastFocus = this.focus;
    if(focus!=lastFocus) {
      for(FocusChangedListener l : lastFocus.getListener(FocusChangedListener.class)) {
        l.onFocusChanged(lastFocus, false);
      }
      if(focus.isFocusable()) {
        for(FocusChangedListener l : focus.getListener(FocusChangedListener.class)) {
          l.onFocusChanged(focus, true);
        }
      }
    }
    
  }
}