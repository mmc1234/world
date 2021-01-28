package com.github.mmc1234.world.toolkit.gui;

import org.lwjgl.opengl.GL30;

import com.github.mmc1234.world.toolkit.Dimension2D;
import com.github.mmc1234.world.toolkit.enumerate.ActionType;
import com.github.mmc1234.world.toolkit.enumerate.ButtonType;
import com.github.mmc1234.world.toolkit.event.CancelClickEvent;
import com.github.mmc1234.world.toolkit.event.ClickEvent;
import com.github.mmc1234.world.toolkit.event.LongClickEvent;
import com.github.mmc1234.world.toolkit.local.ILocalContext;
import com.github.mmc1234.world.toolkit.local.Window;
import com.github.mmc1234.world.toolkit.renderer.GL30Renderer;
import com.google.common.collect.ImmutableList;

public class Button extends View {
  private boolean isClicking = false;
  public Button() {
  }
  
  @Override
  public void onClick(ClickEvent event) {
    super.onClick(event);
    isClicking = false;
    System.out.println(this);
  }

  @Override
  public void onButton(Window window, ActionType type, ButtonType buttonType, double x, double y, int mods) {
    if(type == ActionType.Press) {
      isClicking = true;
    }
  }
  
  @Override
  public void defaultSize(Dimension2D size) {
    size.set(100, 100);
  }
  
  float[] vs = new float[] {-1, 1, 0, 0, -1, -1, 0, 0, 1, 1, 0, 0,  1, 1, 0, 0, -1, -1, 0, 0, 1, -1, 0, 0};

  @Override
  public void onRender(Window window) {
    window.getContext().getBatch().enter(this);
    window.getContext().getBatch().getCurrentBuffer().add(vs, GL30Renderer.EMPTY);
    window.getContext().getBatch().exit(this);
  }
}
