package org.world.message;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.collections4.map.HashedMap;
import org.junit.jupiter.api.Test;

import com.github.mmc1234.world.message.CancelableEventBus;
import com.github.mmc1234.world.message.CancelableSubscribe;
import com.github.mmc1234.world.message.EditableQueryEvent;
import com.github.mmc1234.world.message.Event;
import com.github.mmc1234.world.message.QueryEvent;
import com.github.mmc1234.world.message.Subscribe;
import com.google.common.collect.HashMultimap;

class TestEventBus {

  public static class BoomEvent extends Event {
    
  }
  
  @Test
  void test() {
    CancelableEventBus eventBus = new CancelableEventBus("EventBus", e->e.printStackTrace(), HashMultimap.create());
    eventBus.register(new Object() {
      @Subscribe
      public void onE(Event event) {
        System.out.println("---");
      }
      @Subscribe
      public void onE(BoomEvent event) {
        System.out.println("Boom");
      }
      
      @CancelableSubscribe(BoomEvent.class)
      public void onCancelBoom(QueryEvent event) {
        System.out.println("abc");
      }
    });
    EditableQueryEvent event = new EditableQueryEvent().setEvent(new BoomEvent());
    eventBus.post(event);
    if(!event.getEvent().isCancel()) {
      eventBus.post(event.getEvent());
    }
  }

}
