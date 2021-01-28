package com.github.mmc1234.world.toolkit.gui;

import com.github.mmc1234.world.toolkit.Dimension2D;
import com.github.mmc1234.world.toolkit.enumerate.ActionType;
import com.github.mmc1234.world.toolkit.enumerate.ButtonType;
import com.github.mmc1234.world.toolkit.enumerate.SizeType;
import com.github.mmc1234.world.toolkit.event.CancelClickEvent;
import com.github.mmc1234.world.toolkit.event.ClickEvent;
import com.github.mmc1234.world.toolkit.event.LongClickEvent;
import com.github.mmc1234.world.toolkit.legacy.UIMesh;
import com.github.mmc1234.world.toolkit.local.ILocalContext;
import com.google.common.collect.ImmutableList;

import lombok.ToString;

@ToString
public abstract class View {
  protected double actualX, actualY, actualWidth, actualHeight;
  public double x, y, width, height, padTop, padBottom, padLeft, padRight;
  public SizeType xType = SizeType.Auto, yType = SizeType.Auto, wType = SizeType.Auto, hType = SizeType.Auto;
  
  public void calculate(Dimension2D size, Dimension2D pos) {
    ViewUtils.calculate(this, size, pos);
  }

  public void defaultSize(Dimension2D size) {
    size.set(32, 32);
  }

  public abstract void onButton(ILocalContext ctx, ActionType type, ButtonType buttonType, double x, double y, int mods);

  public abstract void onCancelClick(CancelClickEvent event);
  
  public void onClick(ClickEvent event) {
    event.getWindow().setFocusView(this);
  }
  
  public abstract void onDropFile(ILocalContext ctx, ImmutableList<String> paths);

  public abstract void onFocusEnter(ILocalContext ctx);

  public abstract void onFocusExit(ILocalContext ctx);

  public View onHit(ILocalContext ctx, double x, double y) {
    return ViewUtils.hit(this, x, y) ? this : null;
  }

  public abstract void onInput(ILocalContext ctx, char ch, double x,double y);

  public abstract void onKey(ILocalContext ctx, ActionType type, double x, double y, int key, long time, int scancode, int modes);

  public abstract void onLongClick(LongClickEvent event);

  public abstract void onRender(ILocalContext ctx);

  public abstract void onReshape(double x, double y, double width, double height);
}
