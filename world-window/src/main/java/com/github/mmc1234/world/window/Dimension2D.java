package com.github.mmc1234.world.window;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Dimension2D {
  public @Getter @Setter double x, y;

  public void set(double x, double y) {
    this.x = x;
    this.y = y;
  }
}
