package com.github.mmc1234.world.leg2.toolkit.gui;


import com.github.mmc1234.world.leg2.toolkit.Dimension2D;
import com.github.mmc1234.world.leg2.toolkit.window.ActionType;
import com.github.mmc1234.world.leg2.toolkit.window.ILocalContext;

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

  public abstract void onClick(ILocalContext ctx, int x, int y, int mouseType);

  public abstract void onLongClick(ILocalContext ctx, int x, int y, int mouseType);

  public abstract void onCancelClick(ILocalContext ctx, int x, int y, int mouseType);

  public abstract void onButton(ILocalContext ctx, int x, int y, ActionType type, int mouseType);

  public abstract void onKey(ILocalContext ctx, ActionType type, int key, int modes);

  public abstract void onInput(ILocalContext ctx, String message);

  public abstract void onFocusEnter(ILocalContext ctx);

  public abstract void onFocusExit(ILocalContext ctx);
  
  public abstract void onDropFile(ILocalContext ctx);
}
