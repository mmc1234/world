package com.github.mmc1234.world.toolkit.gui;

import org.lwjgl.opengl.GL30;

import com.github.mmc1234.world.toolkit.Dimension2D;
import com.github.mmc1234.world.toolkit.enumerate.ActionType;
import com.github.mmc1234.world.toolkit.enumerate.ButtonType;
import com.github.mmc1234.world.toolkit.event.CancelClickEvent;
import com.github.mmc1234.world.toolkit.event.ClickEvent;
import com.github.mmc1234.world.toolkit.event.LongClickEvent;
import com.github.mmc1234.world.toolkit.local.ILocalContext;
import com.github.mmc1234.world.toolkit.renderer.IGlyph;
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
  public void onFocusEnter(ILocalContext ctx) {
  }

  @Override
  public void onFocusExit(ILocalContext ctx) {
  }
@Override
  public void onCancelClick(CancelClickEvent event) {
  }

  @Override
  public void onButton(ILocalContext ctx, ActionType type, ButtonType buttonType, double x, double y, int mods) {
    if(type == ActionType.Press) {
      isClicking = true;
    }
  }

  
  @Override
  public void onDropFile(ILocalContext ctx, ImmutableList<String> paths) {

  }

  @Override
  public void onInput(ILocalContext ctx, char ch, double x, double y) {
  }

  @Override
  public void onKey(ILocalContext ctx, ActionType type, double x, double y, int key, long time, int scancode,
      int modes) {
  }

  @Override
  public void onLongClick(LongClickEvent event) {
  }
  
  @Override
  public void defaultSize(Dimension2D size) {
    size.set(100, 100);
  }

  @Override
  public void onRender(ILocalContext ctx) {
    System.out.println("Render");
    GL30.glBegin(GL30.GL_TRIANGLES);
    if(isClicking) {
      GL30.glColor4f(0, 255, 0, 255);
    } else {
      GL30.glColor4f(255, 0, 255, 255);
    }
    GL30.glVertex2d(-1+actualX/400, 1-actualY/300);
    GL30.glVertex2d(-1+actualX/400, 1-(actualY+actualHeight)/300);
    GL30.glVertex2d(-1+(actualX+actualWidth)/400, 1-actualY/300);
    GL30.glVertex2d(-1+(actualX+actualWidth)/400, 1-actualY/300);
    GL30.glVertex2d(-1+actualX/400, 1-(actualY+actualHeight)/300);
    GL30.glVertex2d(-1+(actualX+actualWidth)/400, 1-(actualY+actualHeight)/300);
    
    GL30.glEnd();
  }

  @Override
  public void onReshape(double x, double y, double width, double height) {
  }

}
