package com.github.mmc1234.world.message;

import java.lang.reflect.Method;

import com.google.common.collect.Multimap;

public class CancelableEventBus {
  SubscriberRegistry def = new SubscriberRegistry();
  SubscriberRegistry cel = new SubscriberRegistry();
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
    if(obj instanceof QueryEvent) {
      cel.post(((QueryEvent) obj).getEvent().getClass(), obj);
    } else {
      def.post(obj.getClass(), obj);
    }
  }
}
