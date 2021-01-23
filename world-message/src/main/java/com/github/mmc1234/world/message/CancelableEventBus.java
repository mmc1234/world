package com.github.mmc1234.world.message;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.function.Consumer;

import javax.sql.PooledConnection;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.var;

@RequiredArgsConstructor
public class CancelableEventBus {
  private final @NonNull String name;
  private final @NonNull SubscriberExceptionHandler exceptionHandler;
  private final @NonNull Multimap<Class<?>, Subscriber> subscribers;
  private final Object[] buf = new Object[1];
  
  public CancelableEventBus(String name) {
    this(name, e -> e.printStackTrace(), HashMultimap.create());
  }
  
  public CancelableEventBus() {
    this("default");
  }

  public void register(Object listener) {
    for (var method : listener.getClass().getMethods()) {
      if (method.isAnnotationPresent(CancelableSubscribe.class)) {
        Subscriber sub = new Subscriber(listener, method);
        if (!subscribers.containsEntry(listener, sub)) {
          subscribers.put(QueryEvent.class, sub);
        }
      } else if (method.isAnnotationPresent(Subscribe.class)) {
        Subscriber sub = new Subscriber(listener, method);
        if (!subscribers.containsValue(sub)) {
          subscribers.put(method.getParameterTypes()[0], sub);
        }
      }
    }
    System.out.println(subscribers.keySet());
  }

  public void unregister(Object listener) {
    for (var method : listener.getClass().getMethods()) {
      if (method.isAnnotationPresent(Subscribe.class)) {
        removeListener(listener, method.getParameterTypes()[0]);
      }
      if (method.isAnnotationPresent(CancelableSubscribe.class)) {
        removeListener(listener, method.getAnnotation(CancelableSubscribe.class).getClass());
      }
    }
  }

  @SneakyThrows
  private void removeListener(Object obj, Class<?> clazz) {
    subscribers.keys().forEach(key -> {
      if (clazz.isAssignableFrom(key)) {
        subscribers.get(key).remove(obj);
      }
    });
  }

  public void post(Object obj) {
    if (obj instanceof QueryEvent) {
      subscribers.keys().forEach(key -> {
        if (QueryEvent.class.isAssignableFrom(key)) {
          subscribers.get(key).forEach(sub -> {
            try {
              if (obj.getClass()
                  .isAssignableFrom(sub.method.getAnnotation(CancelableSubscribe.class).value())) {
              }
              if (!sub.method.isAccessible()) {
                sub.method.setAccessible(true);
              }
              sub.method.invoke(sub.obj, buf);
            } catch (Throwable e) {
              exceptionHandler.handleException(e);
            }
          });
        }
      });
    } else {
      subscribers.keys().forEach(key -> {
        if (key.isAssignableFrom(obj.getClass())) {
          subscribers.get(key).forEach(sub -> {
            try {
              if (!sub.method.isAccessible()) {
                sub.method.setAccessible(true);
              }
              sub.method.invoke(sub.obj, buf);
            } catch (Throwable e) {
              exceptionHandler.handleException(e);
            }
          });
        }
      });
    }
  }
}
