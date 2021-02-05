package com.github.mmc1234.world.window;

import com.github.mmc1234.world.message.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class ViewRenderEvent extends Event {
  protected @Setter View view;
  protected @Getter Batch batch;
}