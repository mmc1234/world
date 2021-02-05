package com.github.mmc1234.world.engine.test;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.ByteBuffer;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.lf5.viewer.configure.ConfigurationManager;
import org.junit.jupiter.api.Test;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallbackI;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import com.github.mmc1234.world.engine.view.LinearLayout;
import com.github.mmc1234.world.lwjgl.GL2Batch;
import com.github.mmc1234.world.lwjgl.LwjglWindowExtension;
import com.github.mmc1234.world.message.CancelableEventBus;
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

/**
 * 目前有个bug，那就是View的大小，很奇怪
 * */
class LinearLayoutMeasureTest {

  @Test
  void test() {
    
    GLFW.glfwInit();
    BasicConfigurator.configure();
    Window window = new Window(new LwjglWindowExtension());
    window.create();
    window.make();
    LinearLayout ll = new LinearLayout();
    ll.addView(new View());
    ll.addView(new View());
    View view0 = ll.getView(0);
    view0.setLayoutWidth(LayoutMode.Pixel);
    view0.setLayoutHeight(LayoutMode.Pixel);
    view0.setSize(10, 100);
    view0.setPadLeft(123);
    View view = ll.getView(1);
    view.setLayoutWidth(LayoutMode.Pixel);
    view.setLayoutHeight(LayoutMode.Pixel);
    view.setPosition(100, 100);
    view.setSize(10, 100);
    view.setPadLeft(12);
    ll.measure(MeasureSpec.AtMost, 800, 600);
    
    System.out.println(view0);

    System.out.println(view);
    
    ll.layout(0, ll.getMeasuredWidth(), 0, ll.getMeasuredHeight());
    
    System.out.println(view0);

    System.out.println(view);
    
    view0.addListener(ViewTree.OnDrawListener.class, new ViewTree.OnDrawListener() {
      @Override
      public void onDraw(View view, Batch batch) {
        batch.begin();
        batch.color(0, 1, 0, 1);
        
        batch.draw(view.getLeft(), view.getTop(), 0, 0);
        batch.draw(view.getLeft(), view.getBottom(), 0, 0);
        batch.draw(view.getRight(), view.getTop(), 0, 0);
        batch.draw(view.getRight(), view.getTop(), 0, 0);
        batch.draw(view.getLeft(), view.getBottom(), 0, 0);
        batch.draw(view.getRight(), view.getBottom(), 0, 0);
        batch.end();
      }
    });
    view.addListener(ViewTree.OnDrawListener.class, new ViewTree.OnDrawListener() {
      @Override
      public void onDraw(View view, Batch batch) {
        batch.begin();
        batch.color(0, 1, 0, 1);
        
        batch.draw(view.getLeft(), view.getTop(), 0, 0);
        batch.draw(view.getLeft(), view.getBottom(), 0, 0);
        batch.draw(view.getRight(), view.getTop(), 0, 0);
        batch.draw(view.getRight(), view.getTop(), 0, 0);
        batch.draw(view.getLeft(), view.getBottom(), 0, 0);
        batch.draw(view.getRight(), view.getBottom(), 0, 0);
        batch.end();
      }
    });
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
      batch.beginFrame(0, 0, 0);
      batch.enablePixelMode();
      new ViewRenderListener().onRender(new ViewRenderEvent(ll, batch));
      batch.endFrame();
    }
    
    window.unmake();
    window.close();
  }

}
