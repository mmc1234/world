package com.github.mmc1234.world.leg2.toolkit.window;

import com.github.mmc1234.world.leg2.toolkit.gui.CancelClickEvent;
import com.github.mmc1234.world.leg2.toolkit.gui.LongClickEvent;
import com.github.mmc1234.world.leg2.toolkit.gui.View;
import com.github.mmc1234.world.message.Event;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.Value;
import lombok.experimental.Accessors;

@Getter
@Setter(value = AccessLevel.PACKAGE)
public class WillLongClickEvent extends Event {
  private LongClickEvent event;
}