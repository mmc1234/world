package com.github.mmc1234.world.toolkit;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.lwjgl.opengl.GL30;
import org.lwjgl.stb.STBImage;
import org.lwjgl.stb.STBTTBakedChar;
import org.lwjgl.stb.STBTTBitmap;
import org.lwjgl.stb.STBTTFontinfo;
import org.lwjgl.stb.STBTTVertex;
import org.lwjgl.stb.STBTruetype;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import com.github.mmc1234.world.leg2.toolkit.renderer.Texture;
import com.github.mmc1234.world.leg2.toolkit.renderer.font.BitmapFont;

import lombok.SneakyThrows;

class TestBitmapFont {
  
  @Test
  void test() {
    new BaseGraphTestApp() {
      BitmapFont font = new BitmapFont(TestBitmapFont.class.getClassLoader().getResource("test.ttf").toString());
      @Override
      public void init() {
        font.create();
        font.getBitmap('b');
      }
      @Override
      public void render() {
      }
    }.run();
  }

}
