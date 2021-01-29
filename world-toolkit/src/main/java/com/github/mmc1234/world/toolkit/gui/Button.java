package com.github.mmc1234.world.toolkit.gui;

import org.lwjgl.opengl.GL30;

import com.github.mmc1234.world.toolkit.Dimension2D;
import com.github.mmc1234.world.toolkit.bitmap.IBitmap;
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
  
  BufferedViewMesh mesh = new BufferedViewMesh(new float[] {
      -1, 1, 0, 0,
      -1, -1, 0, 0,
      1, 1, 0, 0,
      1, 1, 0, 0,
      -1, -1, 0, 0,
      1, -1, 0, 0,
  }, new String[]{"HelloKitty", "HelloKitty"});
  
 @Override
  public void onRedraw(Window window) {
   mesh.refresh(this, window.getWidth(), window.getHeight());
  }
 
  @Override
  public void onRender(Window window) {
    onRedraw(window);
    window.getContext().getBatch().enter(this);
    window.getContext().getBatch().getCurrentBuffer().add(mesh);
    window.getContext().getBatch().exit(this);
  }
  
  @Override
  public void onEnter(Window window) {
    System.out.println("Enter");
  }
 
}
