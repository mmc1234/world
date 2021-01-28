package com.github.mmc1234.world.toolkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import com.github.mmc1234.world.toolkit.context.ILocalContext;
import com.github.mmc1234.world.toolkit.enumerate.ActionType;
import com.github.mmc1234.world.toolkit.enumerate.ButtonType;
import com.github.mmc1234.world.toolkit.event.CancelClickEvent;
import com.github.mmc1234.world.toolkit.event.LongClickEvent;
import com.github.mmc1234.world.toolkit.gui.Button;
import com.github.mmc1234.world.toolkit.gui.View;
import com.github.mmc1234.world.toolkit.renderer.DebugRenderer;
import com.github.mmc1234.world.toolkit.renderer.IRenderer;
import com.github.mmc1234.world.toolkit.renderer.UIMesh;
import com.github.mmc1234.world.toolkit.renderer.VertexArray;
import com.github.mmc1234.world.toolkit.renderer.VertexArray.Type;
import com.google.common.collect.ImmutableList;

class TestMeshRender {

  @Test
  void test() {
    new BaseGraphTestApp() {
      DebugRenderer renderer = new DebugRenderer();
      @Override
      public void init() {
        renderer.create(ctx);
        ctx.getCurrentWindow().setRootView(new Button());
      }

      @Override
      public void render() {
        GL30.glEnable(GL30.GL_BLEND);
        GL30.glClearColor(0.3f, 0.3f, 0.3f, 1);
        GL30.glClear(GL30.GL_COLOR_BUFFER_BIT);
        renderer.render(ctx);
      }
      
    }.run();
  }

}
