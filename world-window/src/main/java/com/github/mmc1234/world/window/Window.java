package com.github.mmc1234.world.window;

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

  public void close() {
    extension.close(this);
  }

  public void create() {
    extension.create(this);
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