package com.github.mmc1234.world.message;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
class Subscriber {
  public Object obj;
  public Method method;
  
  public void set(Object obj, Method method) {
    this.obj = obj;
    this.method = method;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Subscriber) {
      Subscriber sub = (Subscriber) obj;
      if ((sub.obj == this.obj) && (sub.method == this.method)) {
        return true;
      }
    }
    return false;
  }
  
  @Override
  public int hashCode() {
    return obj.hashCode();
  }
}
