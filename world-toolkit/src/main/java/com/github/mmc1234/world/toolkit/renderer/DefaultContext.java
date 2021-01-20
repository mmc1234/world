package com.github.mmc1234.world.toolkit.renderer;

import java.util.Map;

import com.github.mmc1234.world.toolkit.gui.IBatch;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants;

@RequiredArgsConstructor()
public class DefaultContext implements IContext {

  private @Getter @NonNull Window window;
  private @Getter @NonNull IBatch batch;
}
