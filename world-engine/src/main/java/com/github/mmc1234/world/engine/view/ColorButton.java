package com.github.mmc1234.world.engine.view;

import java.awt.Color;

import com.github.mmc1234.world.window.Batch;
import com.github.mmc1234.world.window.CancelClickEvent;
import com.github.mmc1234.world.window.ClickDownEvent;
import com.github.mmc1234.world.window.ClickEvent;
import com.github.mmc1234.world.window.ClickListener;
import com.github.mmc1234.world.window.LongClickEvent;
import com.github.mmc1234.world.window.View;
import com.github.mmc1234.world.window.ViewTree;
import com.github.mmc1234.world.window.Window;

public class ColorButton extends View implements ViewTree.OnDrawListener, ClickListener {
  private Color buttonColor;
  private float r, g, b;
  public ColorButton(Color buttonColor) {
    this.buttonColor = buttonColor;
    addListener(ViewTree.OnDrawListener.class, this);
    addListener(ClickListener.class, this);
    r = (buttonColor.getRed()/255f);
    g = (buttonColor.getGreen()/255f);
    b = (buttonColor.getBlue()/255f);
  }
  @Override
  public void onDraw(View view, Batch batch) {
    batch.begin();
    batch.color(r, g, b, 1);
    batch.draw(view.getLeft(), view.getTop(), 0, 0);
    batch.draw(view.getLeft(), view.getBottom(), 0, 0);
    batch.draw(view.getRight(), view.getTop(), 0, 0);
    batch.draw(view.getRight(), view.getTop(), 0, 0);
    batch.draw(view.getLeft(), view.getBottom(), 0, 0);
    batch.draw(view.getRight(), view.getBottom(), 0, 0);
    batch.end();
  }
  @Override
  public void onClick(ClickEvent event) {
    r = (buttonColor.getRed()/255f);
    g = (buttonColor.getGreen()/255f);
    b = (buttonColor.getBlue()/255f);
  }
  @Override
  public void onClickDown(ClickDownEvent event) {
    r = 1-(buttonColor.getRed()/255f);
    g = 1-(buttonColor.getGreen()/255f);
    b = 1-(buttonColor.getBlue()/255f);
  }
  @Override
  public void onCancelClick(CancelClickEvent event) {
  }
  @Override
  public void onLongClick(LongClickEvent event) {};
}
