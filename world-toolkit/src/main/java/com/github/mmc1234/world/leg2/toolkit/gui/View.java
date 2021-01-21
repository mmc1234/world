package com.github.mmc1234.world.leg2.toolkit.gui;

import com.github.mmc1234.world.leg2.toolkit.Dimension2D;
import com.github.mmc1234.world.leg2.toolkit.window.ActionType;
import com.github.mmc1234.world.leg2.toolkit.window.ButtonType;
import com.github.mmc1234.world.leg2.toolkit.window.ILocalContext;
import com.google.common.collect.ImmutableList;

import lombok.ToString;

@ToString
public abstract class View {
  public double x, y, width, height, padTop, padBottom, padLeft, padRight;
  public SizeType sizeType = SizeType.Auto;
  protected double actualX, actualY, actualWidth, actualHeight;
  String background = null;

  public abstract void onPreRender(ILocalContext ctx);

  public View onHit(ILocalContext ctx, double x, double y) {
    return ViewUtils.hit(this, x, y) ? this : null;
  }

  public void onRender(ILocalContext ctx) {
    throw new UnsupportedOperationException();
  }

  public void calculate(Dimension2D size, Dimension2D pos) {
    ViewUtils.calculate(this, size, pos);
  }

  public void defaultSize(Dimension2D size) {
    size.set(32, 32);
  }

  public abstract void onReshape(double x, double y, double width, double height);

  public abstract void onClick(ILocalContext ctx, double x, double y, ButtonType buttonType);

  public abstract void onLongClick(ILocalContext ctx, double x, double y, ButtonType buttonType);

  public abstract void onCancelClick(ILocalContext ctx, double x, double y, ButtonType buttonType);

  public abstract void onButton(ILocalContext ctx, double x, double y, ActionType type, ButtonType buttonType,
      int mods);

  public abstract void onKey(ILocalContext ctx, ActionType type, double x, double y, int key, long time, int scancode,
      int modes);

  public abstract void onInput(ILocalContext ctx, char ch, double x,double y);

  public abstract void onFocusEnter(ILocalContext ctx);

  public abstract void onFocusExit(ILocalContext ctx);

  public abstract void onDropFile(ILocalContext ctx, ImmutableList<String> paths);
}
