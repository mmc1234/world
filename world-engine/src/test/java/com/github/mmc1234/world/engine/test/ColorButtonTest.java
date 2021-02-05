package com.github.mmc1234.world.engine.test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.apache.log4j.BasicConfigurator;
import org.junit.jupiter.api.Test;
import org.lwjgl.glfw.GLFW;

import com.github.mmc1234.world.engine.view.ColorButton;
import com.github.mmc1234.world.engine.view.LinearLayout;
import com.github.mmc1234.world.lwjgl.GL2Batch;
import com.github.mmc1234.world.lwjgl.LwjglWindowExtension;
import com.github.mmc1234.world.window.Batch;
import com.github.mmc1234.world.window.Dimension2D;
import com.github.mmc1234.world.window.LayoutMode;
import com.github.mmc1234.world.window.MeasureSpec;
import com.github.mmc1234.world.window.View;
import com.github.mmc1234.world.window.ViewRenderEvent;
import com.github.mmc1234.world.window.ViewRenderListener;
import com.github.mmc1234.world.window.ViewTree;
import com.github.mmc1234.world.window.Viewport;
import com.github.mmc1234.world.window.Window;

class ColorButtonTest {

  @Test
  void test() {GLFW.glfwInit();
  BasicConfigurator.configure();
  Window window = new Window(new LwjglWindowExtension());
  window.create();
  window.make();
  
  LinearLayout ll = new LinearLayout();

  ll.addView(new ColorButton(Color.pink));
  View btn = ll.getView(0);
  btn.setLayoutWidth(LayoutMode.Pixel);
  btn.setLayoutHeight(LayoutMode.Pixel);
  btn.setSize(30, 20);
  btn.setPadLeft(0);
  window.setLayout(ll);
  
  GL2Batch batch = new GL2Batch();
  batch.setWindow(window);
  batch.viewport(new Viewport() {
    @Override
    public void getViewport(Dimension2D pos, Dimension2D size) {
      pos.x = 0;
      pos.y = 0;
      size.x = window.getWidth();
      size.y = window.getHeight();
    }
  });
  while(!window.isShouldClose()) {
    GLFW.glfwWaitEventsTimeout(0.5);
    if(window.getHandle() <= 0) {
      break;
    }
    window.refreshLayout();
    batch.beginFrame(0, 0, 0);
    batch.enablePixelMode();
    new ViewRenderListener().onRender(new ViewRenderEvent(ll, batch));
    batch.endFrame();
  }
  
  window.unmake();
  window.close();
  }

}
