package com.github.mmc1234.world.toolkit.engine;

import com.github.mmc1234.world.toolkit.local.ILocalContext;

public interface IContext {
  public ILocalContext getLocalContext();
  public Object getResourceManager();
}
