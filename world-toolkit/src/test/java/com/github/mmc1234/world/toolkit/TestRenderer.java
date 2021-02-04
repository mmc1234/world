package com.github.mmc1234.world.toolkit;

import java.nio.ByteBuffer;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.jupiter.api.Test;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import com.github.mmc1234.world.toolkit.gl.EnableTarget;
import com.github.mmc1234.world.toolkit.gl.GLManager;
import com.github.mmc1234.world.toolkit.gui.Button;
import com.github.mmc1234.world.toolkit.legacy.Texture;

import lombok.SneakyThrows;

class TestRenderer {

  @Test
  void test() {
    new BaseGraphTestApp() {
      @Override
      public void render() {
        ctx.getRenderer().render(ctx.getCurrentWindow());
      }
      
      @Override
      @SneakyThrows
      public void init() {
        
        GLManager manager = ctx.getCurrentWindow().getGlManager();
        System.out.println(manager.isEnable(EnableTarget.Blend));
        GL30.glEnable(GL30.GL_BLEND);
        System.out.println(manager.isEnable(EnableTarget.Blend));
        
        byte[] img = IOUtils.toByteArray(MyUtils.class.getClassLoader().getResource("./hello.png"));

        Texture texture = new Texture((ByteBuffer) MemoryUtil.memAlloc(img.length).put(img).flip());
        
        GL30.glActiveTexture(GL30.GL_TEXTURE0);
        ctx.getCurrentWindow().setRootView(new Button());
      }
    }.run();
  }

}
