package com.github.mmc1234.world.toolkit.gui.core;

import com.github.mmc1234.world.toolkit.context.ILocalContext;
import com.github.mmc1234.world.toolkit.enumerate.ActionType;
import com.github.mmc1234.world.toolkit.enumerate.ButtonType;
import com.github.mmc1234.world.toolkit.event.CancelClickEvent;
import com.github.mmc1234.world.toolkit.event.ClickEvent;
import com.github.mmc1234.world.toolkit.event.LongClickEvent;
import com.github.mmc1234.world.toolkit.gui.View;
import com.google.common.collect.ImmutableList;

public class Background extends View {

  @Override
  public void onPreRender(ILocalContext ctx) {
    ctx.getRenderer().enter(this, 0);
    ctx.getRenderer().exit(this);
  }

  @Override
  public void onReshape(double x, double y, double width, double height) {
    System.out.println("reshape");
  }

  @Override
  public void onClick(ClickEvent event) {
  }

  @Override
  public void onLongClick(LongClickEvent event) {
  }

  @Override
  public void onCancelClick(CancelClickEvent event) {
  }

  @Override
  public void onButton(ILocalContext ctx, ActionType type, ButtonType buttonType, double x, double y, int mods) {
  }

  @Override
  public void onKey(ILocalContext ctx, ActionType type, double x, double y, int key, long time, int scancode,
      int modes) {
  }

  @Override
  public void onInput(ILocalContext ctx, char ch, double x, double y) {
  }

  @Override
  public void onFocusEnter(ILocalContext ctx) {
  }

  @Override
  public void onFocusExit(ILocalContext ctx) {
  }

  @Override
  public void onDropFile(ILocalContext ctx, ImmutableList<String> paths) {
  }

}
