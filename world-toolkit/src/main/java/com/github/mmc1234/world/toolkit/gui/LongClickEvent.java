package com.github.mmc1234.world.toolkit.gui;

import com.github.mmc1234.world.message.Event;
import com.github.mmc1234.world.toolkit.window.ButtonType;
import com.github.mmc1234.world.toolkit.window.Window;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(value = AccessLevel.PACKAGE)
@AllArgsConstructor

public class LongClickEvent extends Event {
  private Window window;
  private double x, y;
  private ButtonType type;
  private View holdView;
}
