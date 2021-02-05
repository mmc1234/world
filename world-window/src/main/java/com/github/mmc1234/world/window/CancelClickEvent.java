package com.github.mmc1234.world.window;

import com.github.mmc1234.world.message.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CancelClickEvent extends Event {
  public final View view;
  public final Window window;
}
