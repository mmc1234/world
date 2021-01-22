package com.github.mmc1234.world.leg2.toolkit.event;

import lombok.Getter;

public class Event {
  private @Getter boolean isCancel = false;
  
  public void setCancel(boolean isCancel) {
    this.isCancel = isCancel && canCancel();
  }
  
  public boolean canCancel() {
    return true;
  }
}