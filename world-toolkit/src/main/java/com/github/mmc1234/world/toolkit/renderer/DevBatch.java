package com.github.mmc1234.world.toolkit.renderer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.github.mmc1234.world.toolkit.gui.View;

public final class DevBatch implements IBatch {
  
  private final View[] trackArray = new View[64];
  private final ArrayList<IRenderBuffer> renderBufferList;
  private int currentIndex = 0;
  
  public DevBatch() {
    renderBufferList = new ArrayList<>();
    for(int i = 0; i<64; i++) {
      renderBufferList.add(new ListRenderBuffer(currentIndex));
    }
  }
  
  @Override
  public void enter(View view) {
    if(!isRepeat(view) && canGrow()) {
      trackArray[currentIndex] = view;
      currentIndex++;
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
    if(currentIndex == 0) {
      return null;
    }
    return renderBufferList.get(currentIndex-1);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<IRenderBuffer> getBuffers() {
    return  (List<IRenderBuffer>) renderBufferList.clone();
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
