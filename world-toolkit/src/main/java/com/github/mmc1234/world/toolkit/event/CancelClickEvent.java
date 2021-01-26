package com.github.mmc1234.world.toolkit.event;

import com.github.mmc1234.world.message.Event;
import com.github.mmc1234.world.toolkit.enumerate.ButtonType;
import com.github.mmc1234.world.toolkit.gui.View;
import com.github.mmc1234.world.toolkit.window.Window;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(value = AccessLevel.PACKAGE)
@AllArgsConstructor
public class CancelClickEvent extends Event {
  private Window window;
  private double x, y;
  private ButtonType type;
  private View holdView;
}