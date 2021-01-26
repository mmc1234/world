package com.github.mmc1234.world.toolkit.gui.render;

import java.io.Closeable;
import java.io.IOException;

import javax.sound.midi.Track;

import com.github.mmc1234.world.toolkit.gui.View;
import com.github.mmc1234.world.toolkit.window.ILocalContext;

import lombok.NonNull;

public abstract class AbstractUIRenderer implements Closeable {
  private ViewTrack[] trackArray = new ViewTrack[64];
  private Pass[] passArray = new Pass[16];
  private int index = 0;
  
  public AbstractUIRenderer() {
    for(int i = 0; i<trackArray.length; i++) {
      trackArray[i] = new ViewTrack();
    }
  }
  
  public void enter(View view, int pass) {
    if(pass>passArray.length) {
      throw new IndexOutOfBoundsException("pass index out of bounds.");
    }
    for(int i = 0; i<index; i++) {
      if(trackArray[i].view == view) {
        return;
      }
    }
    trackArray[index].view = view;
    trackArray[index].pass = pass;
    trackArray[index].z = (255-index)/256D;
    passArray[pass].trackList.add(trackArray[index]);
    index++;
  }
  public void exit(View view) {
    if(trackArray[index-1].view == view) {
      index--;
      trackArray[index].view = null;
    }
  }
  
  public abstract void create(ILocalContext context);

  protected void render(@NonNull ILocalContext context) {
    View root = context.getCurrentWindow().getRootView();
    if(root != null) {
      root.onPreRender(context);
    }
    for(Pass pass : passArray) {
      pass.preRender(context);
    }
    for(Pass pass : passArray) {
      pass.render(context);
    }
  }
}
