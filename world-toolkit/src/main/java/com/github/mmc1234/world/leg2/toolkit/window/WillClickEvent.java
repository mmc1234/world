package com.github.mmc1234.world.leg2.toolkit.window;

import com.github.mmc1234.world.leg2.toolkit.event.Event;
import com.github.mmc1234.world.leg2.toolkit.gui.View;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(value = AccessLevel.PACKAGE)
@AllArgsConstructor
@NoArgsConstructor
public class WillClickEvent extends Event {
  protected ILocalContext context;
  protected View view;
  protected double x, y;
  protected ButtonType buttonType;
}