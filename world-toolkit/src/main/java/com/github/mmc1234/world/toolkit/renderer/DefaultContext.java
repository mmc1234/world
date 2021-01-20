package com.github.mmc1234.world.toolkit.renderer;

import java.util.Map;

import com.github.mmc1234.world.toolkit.renderer.IRenderer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants;

@RequiredArgsConstructor()
public class DefaultContext implements IContext {

  private @Getter @NonNull Window window;
  @Override
  public IRenderer getUIRenderer() {
    return null;
  }
}
