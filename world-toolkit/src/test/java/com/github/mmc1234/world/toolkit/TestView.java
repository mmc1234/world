package com.github.mmc1234.world.toolkit;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URLStreamHandlerFactory;

import org.junit.jupiter.api.Test;
import org.lwjgl.glfw.GLFW;

import com.github.mmc1234.world.leg2.toolkit.gui.DemoBatch;
import com.github.mmc1234.world.leg2.toolkit.gui.View;
import com.github.mmc1234.world.leg2.toolkit.window.ActionType;
import com.github.mmc1234.world.leg2.toolkit.window.ButtonType;
import com.github.mmc1234.world.leg2.toolkit.window.ILocalContext;
import com.github.mmc1234.world.leg2.toolkit.window.LocalContext;
import com.github.mmc1234.world.leg2.toolkit.window.Window;
import com.google.common.collect.ImmutableList;

class TestView {

  @Test
  void test() {
    GLFW.glfwInit();
    Window window = new Window();
    ILocalContext ctx = new LocalContext(new DemoBatch());
    window.start();
    window.setRootView(new View() {

      @Override
      public void onPreRender(ILocalContext ctx) {
        // TODO Auto-generated method stub
        
      }

      @Override
      public void onReshape(double x, double y, double width, double height) {
        // TODO Auto-generated method stub
        
      }

      @Override
      public void onClick(ILocalContext ctx, double x, double y, ButtonType buttonType) {
        // TODO Auto-generated method stub
        
      }

      @Override
      public void onLongClick(ILocalContext ctx, double x, double y, ButtonType buttonType) {
        // TODO Auto-generated method stub
        
      }

      @Override
      public void onCancelClick(ILocalContext ctx, double x, double y, ButtonType buttonType) {
        // TODO Auto-generated method stub
        
      }

      @Override
      public void onButton(ILocalContext ctx, double x, double y, ActionType type, ButtonType buttonType, int mods) {
        // TODO Auto-generated method stub
        
      }

      @Override
      public void onKey(ILocalContext ctx, ActionType type, double x, double y, int key, long time, int scancode,
          int modes) {
        System.out.println(time/(GLFW.glfwGetTimerFrequency()/1000));
      }

      @Override
      public void onInput(ILocalContext ctx, char ch, double x, double y) {
        // TODO Auto-generated method stub
        
      }

      @Override
      public void onFocusEnter(ILocalContext ctx) {
        // TODO Auto-generated method stub
        
      }

      @Override
      public void onFocusExit(ILocalContext ctx) {
        // TODO Auto-generated method stub
        
      }

      @Override
      public void onDropFile(ILocalContext ctx, ImmutableList<String> paths) {
        // TODO Auto-generated method stub
        
      }});
    window.setFocusView(window.getRootView());
    ctx.make(window);
    
    while(!window.isShouldClose()) {
      ctx.waitEvents(0.001);
      
      window.swapBuffers();
    }
    
    window.stop();
    GLFW.glfwTerminate();
  }

}
