package com.github.mmc1234.world.message;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Predicate;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import lombok.NonNull;
import lombok.SneakyThrows;

public class SubscriberRegistry {
  /**
   * Class = Event Type </br>
   * Object, Method = Listener
   */
  protected Multimap<Class<?>, Subscriber> subscribers = HashMultimap.create();
  private final Object[] buf = new Object[1];

  public void register(Class<?> clazz, @NonNull Object obj, Method method) {
    subscribers.put(clazz, new Subscriber(obj, method));
  }

  public void unregister(Class<?> clazz, Object obj) {
    for (Entry<Class<?>, Subscriber> entry : subscribers.entries()) {
      if (entry.getKey() == clazz) {
        subscribers.get(entry.getKey()).removeIf(p -> p.obj == obj);
      } else if(clazz.isAssignableFrom(entry.getKey())){
        subscribers.get(entry.getKey()).removeIf(p -> p.obj == obj);
      }
    }
  }

  public void post(Class<?> clazz, Object event) {
    for (Entry<Class<?>, Subscriber> entry : subscribers.entries()) {
      if (entry.getKey() == clazz) {
        run(entry.getValue().method, entry.getValue().obj, event);
      } else if (entry.getKey().isAssignableFrom(clazz)) {
        run(entry.getValue().method, entry.getValue().obj, event);
      }
    }
  }

  @SneakyThrows
  protected void run(Method method, Object obj, Object event) {
    if (!method.isAccessible()) {
      method.setAccessible(true);
    }
    buf[0] = event;
    method.invoke(obj, buf);
  }
}
