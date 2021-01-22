package com.github.mmc1234.world.leg2.toolkit.window;

import com.github.mmc1234.world.leg2.toolkit.gui.CancelClickEvent;
import com.github.mmc1234.world.leg2.toolkit.gui.View;
import com.github.mmc1234.world.message.Event;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Delegate;

@Getter
@Setter(value = AccessLevel.PACKAGE)
public class WillCancelClickEvent extends Event {
  private CancelClickEvent event;
}