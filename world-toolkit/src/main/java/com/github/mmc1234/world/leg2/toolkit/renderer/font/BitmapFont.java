package com.github.mmc1234.world.leg2.toolkit.renderer.font;

import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.charset.Charset;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.lwjgl.stb.STBTTFontinfo;
import org.lwjgl.stb.STBTruetype;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class BitmapFont implements AutoCloseable {
  private final @NonNull String path;
  private STBTTFontinfo info;
  private ByteBuffer data = null;

  @SneakyThrows
  public void create() {
      info = STBTTFontinfo.malloc();
      byte[] font = IOUtils.toByteArray(new URL(path));
      data = (ByteBuffer) MemoryUtil.memAlloc(font.length).put(font).flip();
      STBTruetype.stbtt_InitFont(info, data);
  }
  @Override
  public void close() throws Exception {
    if(info!=null) {
      info = null;
      MemoryUtil.memFree(data);
    }
  }
  @SneakyThrows
  public void getBitmap(int ch) {
    try (MemoryStack stack = MemoryStack.stackPush()) {
      IntBuffer x = stack.mallocInt(1);
      IntBuffer y = stack.mallocInt(1);
      IntBuffer w = stack.mallocInt(1);
      IntBuffer h = stack.mallocInt(1);

      //IntBuffer buffer = STBTruetype.stbtt_GetGlyphSDF(info, scale, index, 5, (byte)180, 180/5.0f, w, h, x, y).asIntBuffer();
      IntBuffer buffer = STBTruetype.stbtt_GetCodepointBitmap(info, 1/4f, 1/4f, ch, w, h, x, y).asIntBuffer();
      BufferedImage img = new BufferedImage(w.get(0), h.get(0), BufferedImage.TYPE_INT_ARGB);
      
      int size = buffer.capacity();
      for(int i = 0; i<size; i++) {
        //System.out.println(buffer.get(i));
        int color = buffer.get(i) & 0xff000000;
        img.setRGB(i%w.get(0), i/w.get(0), color);
      }
      
      System.out.println(buffer.capacity());
      System.out.println(x.get(0)+":"+y.get(0)+":"+w.get(0)+":"+h.get(0));
      ImageIO.write(img, "png", new FileOutputStream("E:/bitmap.png"));
    }
  }
}
