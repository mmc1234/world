package com.github.mmc1234.world.message;

import com.google.common.eventbus.Subscribe;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Event {
  private boolean isCancel = false;
  public @Setter long time;
  
  public void setCancel(boolean isCancel) {
    this.isCancel = isCancel && canCancel();
  }

  public boolean canCancel() {
    return true;
  }
}