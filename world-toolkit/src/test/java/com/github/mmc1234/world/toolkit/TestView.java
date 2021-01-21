package com.github.mmc1234.world.toolkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.lwjgl.glfw.GLFW;

import com.github.mmc1234.world.leg2.toolkit.gui.DemoBatch;
import com.github.mmc1234.world.leg2.toolkit.gui.View;
import com.github.mmc1234.world.leg2.toolkit.window.ActionType;
import com.github.mmc1234.world.leg2.toolkit.window.ILocalContext;
import com.github.mmc1234.world.leg2.toolkit.window.LocalContext;
import com.github.mmc1234.world.leg2.toolkit.window.Window;

class TestView {

  @Test
  void test() {
    GLFW.glfwInit();
    Window window = new Window();
    ILocalContext ctx = new LocalContext(new DemoBatch());
    window.start();
    window.setRootView(new View() {
      
      @Override
      public void onReshape(double x, double y, double width, double height) {

      }
      
      @Override
      public void onPreRender(ILocalContext ctx) {
      }
      
      @Override
      public void onLongClick(ILocalContext ctx, int x, int y, int mouseType) {

      }
      
      @Override
      public void onKey(ILocalContext ctx, ActionType type, int key, int modes) {

      }
      
      @Override
      public void onInput(ILocalContext ctx, String message) {

      }
      
      @Override
      public void onFocusExit(ILocalContext ctx) {

      }
      
      @Override
      public void onFocusEnter(ILocalContext ctx) {

      }
      
      @Override
      public void onDropFile(ILocalContext ctx) {
      }
      
      @Override
      public void onClick(ILocalContext ctx, int x, int y, int mouseType) {

      }
      
      @Override
      public void onCancelClick(ILocalContext ctx, int x, int y, int mouseType) {
        // TODO Auto-generated method stub
        
      }
      
      @Override
      public void onButton(ILocalContext ctx, int x, int y, ActionType type, int mouseType) {
        System.out.println("Click");
      }
    });
    ctx.make(window);
    
    while(!window.isShouldClose()) {
      ctx.waitEvents(0.001);
      
      window.swapBuffers();
    }
    
    window.stop();
    GLFW.glfwTerminate();
  }

}
