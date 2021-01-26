package com.github.mmc1234.world.toolkit;

import org.junit.jupiter.api.Test;
import org.lwjgl.glfw.GLFW;

import com.github.mmc1234.world.toolkit.context.ILocalContext;
import com.github.mmc1234.world.toolkit.context.LocalContext;
import com.github.mmc1234.world.toolkit.enumerate.EnumPass;
import com.github.mmc1234.world.toolkit.gui.core.Background;
import com.github.mmc1234.world.toolkit.renderer.UIPass;
import com.github.mmc1234.world.toolkit.renderer.UIRenderer;
import com.github.mmc1234.world.toolkit.window.Window;

import lombok.SneakyThrows;

class TestRenderer {
  boolean isExit;
  @Test
  @SneakyThrows
  void test() {
    GLFW.glfwInit();
    ILocalContext ctx = new LocalContext();
    Window window = new Window();
    window.start();
    ctx.make(window);
    ctx.loadGL();

    ctx.getRenderer().setPass(EnumPass.UI, new UIPass());
    ctx.getCurrentWindow().setRootView(new Background());
    
    while(!window.isShouldClose()) {
      ctx.waitEvents(1.0/4);
      ctx.getRenderer().render(ctx);
      window.swapBuffers();
    }
  }

}
