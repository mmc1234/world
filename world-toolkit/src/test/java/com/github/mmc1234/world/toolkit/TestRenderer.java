package com.github.mmc1234.world.toolkit;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.jupiter.api.Test;
import org.lwjgl.glfw.GLFW;

import com.github.mmc1234.world.toolkit.gui.Button;

class TestRenderer {

  @Test
  void test() {
    new BaseGraphTestApp() {
      @Override
      public void render() {
        ctx.getRenderer().render(ctx.getCurrentWindow());
      }
      
      @Override
      public void init() {
        ctx.getCurrentWindow().setRootView(new Button());
      }
    }.run();
  }

}
