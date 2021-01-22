package com.github.mmc1234.world.leg2.toolkit.gui;

import com.github.mmc1234.world.leg2.toolkit.window.ButtonType;
import com.github.mmc1234.world.leg2.toolkit.window.ILocalContext;
import com.github.mmc1234.world.leg2.toolkit.window.Window;
import com.github.mmc1234.world.message.Event;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Getter
@Setter(value = AccessLevel.PACKAGE)
@AllArgsConstructor

public class LongClickEvent extends Event {
  private Window window;
  private double x, y;
  private ButtonType type;
  private View holdView;
}
