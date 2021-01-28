package com.github.mmc1234.world.toolkit.renderer;

import java.lang.reflect.Array;
import java.util.Collection;

import com.github.mmc1234.world.toolkit.gui.View;

public interface IBatch {
  public void enter(View view);
  public void exit(View view);
  public IRenderBuffer getCurrentBuffer();
  public Collection<IRenderBuffer> getBuffers();
}
