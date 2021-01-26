package com.github.mmc1234.world.toolkit.window;

import com.github.mmc1234.world.message.Event;
import com.github.mmc1234.world.toolkit.gui.LongClickEvent;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(value = AccessLevel.PACKAGE)
public class WillLongClickEvent extends Event {
  private LongClickEvent event;
}