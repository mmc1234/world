package com.github.mmc1234.world.toolkit.legacy;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL30;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

public class Texture {
  public int width;
  public int height;
  public int id;
  public Texture(ByteBuffer img) {
    ByteBuffer buf;
    try (MemoryStack stack = MemoryStack.stackPush()) {
        IntBuffer w = stack.mallocInt(1);
        IntBuffer h = stack.mallocInt(1);
        IntBuffer channels = stack.mallocInt(1);
        buf = STBImage.stbi_load_from_memory(img, w, h, channels, 4);
        if (buf == null) {
          throw new RuntimeException("纹理加载出错了");
        }
        width = w.get();
        height = h.get();
    }

    id = GL30.glGenTextures();
    GL30.glBindTexture(GL30.GL_TEXTURE_2D, id);

    GL30.glPixelStorei(GL30.GL_UNPACK_ALIGNMENT, 1);

    GL30.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_MIN_FILTER, GL30.GL_LINEAR);
    GL30.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_MAG_FILTER, GL30.GL_LINEAR);

    // Upload the texture data
    GL30.glTexImage2D(GL30.GL_TEXTURE_2D, 0, GL30.GL_RGBA, width, height, 0,
        GL30.GL_RGBA, GL30.GL_UNSIGNED_BYTE, buf);
    STBImage.stbi_image_free(buf);
  }
}
