package com.github.mmc1234.world.toolkit.core;

import java.util.Collection;

public interface IMerge <V, N, R> {
  public V get(N name);
  public boolean add(N name, R rect);
  public boolean remove(N name);
  public Collection<V> values();
}
