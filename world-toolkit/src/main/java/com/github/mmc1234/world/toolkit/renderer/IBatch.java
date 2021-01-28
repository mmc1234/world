package com.github.mmc1234.world.toolkit.renderer;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;

import com.github.mmc1234.world.toolkit.gui.View;

public interface IBatch {
  public void enter(View view);
  public void exit(View view);
  public IRenderBuffer getCurrentBuffer();
  public List<IRenderBuffer> getBuffers();
}
