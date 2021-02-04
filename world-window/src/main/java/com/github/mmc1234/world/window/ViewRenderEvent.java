package com.github.mmc1234.world.window;

import com.github.mmc1234.world.message.Event;

import lombok.Getter;
import lombok.Setter;

public class ViewRenderEvent extends Event {
  protected @Getter @Setter View view;
  public ViewRenderEvent(View view) {
    this.view = view;
  }
}
