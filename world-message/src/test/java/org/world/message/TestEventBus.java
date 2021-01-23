package org.world.message;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.collections4.map.HashedMap;
import org.junit.jupiter.api.Test;
import org.world.message.TestEventBus.BoomEvent;

import com.github.mmc1234.world.message.CancelableEventBus;
import com.github.mmc1234.world.message.CancelableEventBus;
import com.github.mmc1234.world.message.CancelableSubscribe;
import com.github.mmc1234.world.message.EditableQueryEvent;
import com.github.mmc1234.world.message.Event;
import com.github.mmc1234.world.message.QueryEvent;
import com.github.mmc1234.world.message.Subscribe;
import com.google.common.collect.HashMultimap;
import com.google.common.eventbus.EventBus;

class TestEventBus {

  public static class BoomEvent extends Event {
  }
  
  public static class A {

    @Subscribe
    @com.google.common.eventbus.Subscribe
    public void onXxx(Object event) {
      System.out.println(" ");
    }
    @Subscribe
    @com.google.common.eventbus.Subscribe
    public void onYyy(String event) {
      System.out.println(" ");
    }
  }
  
  @Test
  void test() {
    CancelableEventBus ceb = new CancelableEventBus();
    
    for(int i = 0; i<500; i++) {
      ceb.register(new A());
    }
    for(int i = 0; i<1000; i++) {
      ceb.post(" ");
    }
  }
  
  @Test
 void guava() {
   EventBus eventBus = new EventBus();
   for(int i = 0; i<500; i++) {
     eventBus.register(new A());
   }
   for(int i = 0; i<1000; i++) {
     eventBus.post(" ");
   }
 }

}
