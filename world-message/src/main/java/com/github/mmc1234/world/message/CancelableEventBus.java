package com.github.mmc1234.world.message;

import java.lang.reflect.Method;

import lombok.NonNull;

public class CancelableEventBus {
  private SubscriberRegistry def = new SubscriberRegistry();
  private SubscriberRegistry cel = new SubscriberRegistry();
  private final @NonNull SubscriberExceptionHandler exceptionHandler;
  private final @NonNull String identifier;
  
  public CancelableEventBus(String identifier) {
    this(identifier, e->e.printStackTrace());
  }
  
  public CancelableEventBus(String identifier, SubscriberExceptionHandler exceptionHandler) {
    this.identifier = identifier;
    this.exceptionHandler = exceptionHandler;
  }
  
  public void register(Object obj) {
    for(Method method : obj.getClass().getMethods()) {
      if(method.isAnnotationPresent(CancelableSubscribe.class)) {
        cel.register(method.getAnnotation(CancelableSubscribe.class).value(), obj, method);
      } else if(method.isAnnotationPresent(Subscribe.class)){
        def.register(method.getParameterTypes()[0], obj, method);
      }
    }
  }
  
  public void unreigster(Object obj) {
    for(Method method : obj.getClass().getMethods()) {
      if(method.isAnnotationPresent(CancelableSubscribe.class)) {
        cel.unregister(method.getAnnotation(CancelableSubscribe.class).value(), obj);
      } else if(method.isAnnotationPresent(Subscribe.class)) {
        def.unregister(method.getParameterTypes()[0], obj);
      }
    }
  }
  
  public void post(Object obj) {
    try {
      if(obj instanceof QueryEvent) {
        cel.post(((QueryEvent) obj).getEvent().getClass(), obj);
      } else {
        def.post(obj.getClass(), obj);
      }
    } catch (Exception e) {
      exceptionHandler.handleException(e);
    }
  }
}
