package com.github.mmc1234.world.toolkit.renderer;

import java.util.ArrayList;
import java.util.Collection;

import com.github.mmc1234.world.toolkit.gui.View;

public final class DevBatch implements IBatch {
  
  private final View[] trackArray = new View[64];
  private final ArrayList<IRenderBuffer> renderBufferList = new ArrayList<>(64);
  private int currentIndex = 0;
  
  @Override
  public void enter(View view) {
    if(!isRepeat(view) && canGrow()) {
      trackArray[currentIndex++] = view;
    }
  }

  @Override
  public void exit(View view) {
    if(isCurrent(view)) {
      trackArray[--currentIndex] = null;
    }
  }

  @Override
  public IRenderBuffer getCurrentBuffer() {
    return renderBufferList.get(currentIndex-1);
  }

  @SuppressWarnings("unchecked")
  @Override
  public Collection<IRenderBuffer> getBuffers() {
    return (Collection<IRenderBuffer>) renderBufferList.clone();
  }

  public final boolean isRepeat(View view) {
    for(int i =0; i<currentIndex; i++) {
      if(trackArray[i] == view) {
        return true;
      }
    }
    return false;
  }
  
  public final boolean isCurrent(View view) {
    return currentIndex!=0 && trackArray[currentIndex-1] == view;
  }
  
  public final boolean canGrow() {
    return currentIndex < trackArray.length;
  }
  
}
