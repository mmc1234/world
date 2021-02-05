package com.github.mmc1234.world.window;

public interface ClickListener {
  public void onClickDown(ClickDownEvent event);
  public void onClick(ClickEvent event);
  public void onLongClick(LongClickEvent event);
  public void onCancelClick(CancelClickEvent event);
}
