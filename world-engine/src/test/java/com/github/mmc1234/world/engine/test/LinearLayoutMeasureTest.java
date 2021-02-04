package com.github.mmc1234.world.engine.test;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.ByteBuffer;

import org.junit.jupiter.api.Test;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import com.github.mmc1234.world.engine.view.LinearLayout;
import com.github.mmc1234.world.lwjgl.GL2Batch;
import com.github.mmc1234.world.lwjgl.LwjglWindowExtension;
import com.github.mmc1234.world.message.CancelableEventBus;
import com.github.mmc1234.world.window.LayoutMode;
import com.github.mmc1234.world.window.MeasureSpec;
import com.github.mmc1234.world.window.View;
import com.github.mmc1234.world.window.Window;

class LinearLayoutMeasureTest {

  @Test
  void test() {
    LinearLayout ll = new LinearLayout();
    ll.addView(new View());
    View view = ll.getView(0);
    view.setLayoutWidth(LayoutMode.Pixel);
    view.setLayoutHeight(LayoutMode.Pixel);
    view.setX(1);
    view.setY(1);
    view.setWidth(12);
    view.setHeight(12);
    ll.measure(MeasureSpec.AtMost, 800, 600);
    System.out.println(ll);
    System.out.println(view);
  }

}
