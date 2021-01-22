package com.github.mmc1234.world.message;

import com.google.common.eventbus.EventBus;

public class CancelableEventBus extends EventBus {
  private Event event;
  private QueryEvent queryEvent = () -> event;
  @Override
  public void post(Object event) {
    if(event instanceof Event) {
      this.event = (Event) event;
      super.post(queryEvent);
    }
    super.post(event);
  }
}
