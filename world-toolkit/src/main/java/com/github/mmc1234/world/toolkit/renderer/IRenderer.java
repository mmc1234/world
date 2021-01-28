package com.github.mmc1234.world.toolkit.renderer;

import java.util.Collection;

import com.github.mmc1234.world.toolkit.context.ILocalContext;
import com.github.mmc1234.world.toolkit.gui.View;

public interface IRenderer {
  /**
   * 得到Collection ViewTrack并排序 </br>
   * 提交UniformBuffer和ArrayBuffer </br>
   * 渲染
   * */
  public void render(ILocalContext context);
  public void create(ILocalContext context);
  public void close(ILocalContext context);
  public void enter(View view, Object mesh);
  public void exit(View view);
  public IGlyph getGlyph();
}
