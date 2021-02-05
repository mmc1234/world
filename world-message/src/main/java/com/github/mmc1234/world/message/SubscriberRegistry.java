package com.github.mmc1234.world.message;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.function.Predicate;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.var;

public class SubscriberRegistry {
  /**
   * Class = Event Type </br>
   * Object, Method = Listener
   */
  protected HashMultimap<Class<?>, Subscriber> subscribers = HashMultimap.create();
  private final Object[] buf = new Object[1];

  public void register(Class<?> clazz, @NonNull Object obj, Method method) {
    subscribers.put(clazz, new Subscriber(obj, method));
  }

  public void unregister(Class<?> clazz, Object obj) {
    ArrayList<Entry<Class<?>, Subscriber>> list = new ArrayList<>();
    for (Entry<Class<?>, Subscriber> entry : subscribers.entries()) {
      if (entry.getKey() == clazz || clazz.isAssignableFrom(entry.getKey())) {
        if(entry.getValue().obj == obj) {
          list.add(entry);
        }
      }
    }
    for(var v:list) {
     subscribers.remove(v.getKey(), v.getValue()); 
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
