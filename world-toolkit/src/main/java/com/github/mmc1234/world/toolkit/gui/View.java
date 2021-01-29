package com.github.mmc1234.world.toolkit.gui;

import com.github.mmc1234.world.toolkit.Dimension2D;
import com.github.mmc1234.world.toolkit.enumerate.ActionType;
import com.github.mmc1234.world.toolkit.enumerate.ButtonType;
import com.github.mmc1234.world.toolkit.enumerate.SizeType;
import com.github.mmc1234.world.toolkit.event.CancelClickEvent;
import com.github.mmc1234.world.toolkit.event.ClickEvent;
import com.github.mmc1234.world.toolkit.event.LongClickEvent;
import com.github.mmc1234.world.toolkit.local.ILocalContext;
import com.github.mmc1234.world.toolkit.local.Window;
import com.google.common.collect.ImmutableList;

import lombok.Getter;
import lombok.ToString;

@ToString
public abstract class View {
  protected double actualX, actualY, actualWidth, actualHeight;
  public double x, y, width, height, padTop, padBottom, padLeft, padRight;
  public SizeType xType = SizeType.Auto, yType = SizeType.Auto, wType = SizeType.Auto, hType = SizeType.Auto;
  protected boolean isRedraw = true;

  public void calculate(Dimension2D size, Dimension2D pos) {
    ViewUtils.calculate(this, size, pos);
  }

  public void defaultSize(Dimension2D size) {
    size.set(32, 32);
  }

  public void onButton(Window window, ActionType type, ButtonType buttonType, double x, double y, int mods) {
  }

  public void onCancelClick(CancelClickEvent event) {
  }

  public void onClick(ClickEvent event) {
    event.getWindow().setFocusView(this);
  }

  public void onDropFile(Window window, ImmutableList<String> paths) {
  }

  public void onFocusEnter(Window window) {
  }

  public void onFocusExit(Window window) {
  }

  public View onHit(Window window, double x, double y) {
    return ViewUtils.hit(this, x, y) ? this : null;
  }

  public void onInput(Window window, char ch, double x, double y) {
  }

  public void onKey(Window window, ActionType type, double x, double y, int key, long time, int scancode,
      int modes) {
  }

  public void onLongClick(LongClickEvent event) {
  }

  public void onRender(Window window) {
    if(isRedraw) {
      isRedraw = false;
      onRedraw(window);
    }
  }

  public void onReshape(double x, double y, double width, double height) {
    isRedraw = true;
  }
  
  public void onEnter(Window window) {
  }
  
  public void onExit(Window window) {
  }
  
  public abstract void onRedraw(Window window);
}
