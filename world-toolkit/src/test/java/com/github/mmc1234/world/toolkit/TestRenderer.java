package com.github.mmc1234.world.toolkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.github.mmc1234.world.toolkit.gui.Button;
import com.github.mmc1234.world.toolkit.local.ILocalContext;
import com.github.mmc1234.world.toolkit.local.LocalContext;

class TestRenderer {

  @Test
  void test() {
    new BaseGraphTestApp() {
      @Override
      public void render() {
        ctx.getRenderer().render(ctx);
      }
      
      @Override
      public void init() {
        ctx.getCurrentWindow().setRootView(new Button());
      }
    }.run();
  }

}
