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
import com.github.mmc1234.world.toolkit.gui.View;
import com.github.mmc1234.world.toolkit.renderer.Renderer;
import com.github.mmc1234.world.toolkit.renderer.UIMesh;
import com.github.mmc1234.world.toolkit.renderer.VertexArray;
import com.github.mmc1234.world.toolkit.renderer.VertexArray.Type;
import com.google.common.collect.ImmutableList;

class TestMeshRender {

  @Test
  void test() {
    new BaseGraphTestApp() {
      Renderer renderer = new Renderer((currentBaseSize, lastVertexCount, vertexCount) -> vertexCount);
      @Override
      public void init() {
        renderer.create(ctx);
        ctx.getCurrentWindow().setFocusView(new View() {
          
          @Override
          public void onReshape(double x, double y, double width, double height) { 
            System.out.println("Reshape");
          }
          
          @Override
          public void onRender(ILocalContext ctx) {
          }
          
          @Override
          public void onLongClick(LongClickEvent event) {
          }
          
          @Override
          public void onKey(ILocalContext ctx, ActionType type, double x, double y, int key, long time, int scancode,
              int modes) {
            
          }
          
          @Override
          public void onInput(ILocalContext ctx, char ch, double x, double y) {
          }
          
          @Override
          public void onFocusExit(ILocalContext ctx) {
          }
          
          @Override
          public void onFocusEnter(ILocalContext ctx) {
            
          }
          
          @Override
          public void onDropFile(ILocalContext ctx, ImmutableList<String> paths) {

          }
          
          @Override
          public void onCancelClick(CancelClickEvent event) {
            // TODO Auto-generated method stub
            
          }
          
          @Override
          public void onButton(ILocalContext ctx, ActionType type, ButtonType buttonType, double x, double y, int mods) {
            // TODO Auto-generated method stub
            
          }
          UIMesh mesh = UIMesh.builder().vertex(-1, 1, 0, 0)
              .vertex(-1, -1, 0, 1)
              .vertex(1, 1, 1, 1)
              .bitmap("hellokitty")
              .color(0x00ff00ff)
              .build();
          @Override
          public UIMesh getMesh() {
            return mesh;
          }
        });
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
