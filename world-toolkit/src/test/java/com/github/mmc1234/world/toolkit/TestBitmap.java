package com.github.mmc1234.world.toolkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.github.mmc1234.world.toolkit.bitmap.FastBitmapManager;
import com.github.mmc1234.world.toolkit.bitmap.IBitmapManager;

class TestBitmap {

  @Test
  void test() {
    new BaseGraphTestApp() {

      @Override
      public void init() {
        IBitmapManager bitmapManager = new FastBitmapManager(1024, 1024);
        bitmapManager.add("HelloKitty", 16, 16);
        System.out.println(bitmapManager.get("HelloKitty"));
        bitmapManager.add("Tom", 1024-16, 1024);
        System.out.println(bitmapManager.get("Tom"));
      }

      @Override
      public void render() {
      }
      
    }.run();
  }

}
