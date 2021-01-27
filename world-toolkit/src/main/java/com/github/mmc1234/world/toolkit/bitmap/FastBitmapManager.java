package com.github.mmc1234.world.toolkit.bitmap;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.naming.spi.DirStateFactory.Result;

import org.lwjgl.opengl.GL30;

import com.github.mmc1234.world.toolkit.Rect;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FastBitmapManager implements IBitmapManager {

  private int maxWidth, maxHeight;
  private Set<Bitmap> freeSet;
  private Map<String, IBitmap> bitmaps;
  private int textureCount;
  private int[] textureArray = new int[14];

  public FastBitmapManager(int maxWidth, int maxHeight) {
    this.maxWidth = maxWidth;
    this.maxHeight = maxHeight;
    freeSet = new HashSet<>();
    bitmaps = new HashMap<>();
  }

  @Override
  public boolean add(String name, int width, int height) {
    if (width > maxWidth | height > maxHeight) {
      throw new IllegalArgumentException("Size out of bounds, width:" + width + ", height:" + height + ".");
    }
    if (bitmaps.containsKey(name)) {
      return false;
    }

    merge();

    Bitmap max = find(width, height);
    cut(max, width, height, name);
    return true;
  }

  protected void merge() {
    // TODO
  }

  protected void cut(Bitmap max, int width, int height, String name) {
    boolean isFullX = max.width == width;
    boolean isFullY = max.height == height;
    if (isFullX) {
      if (!isFullY) {
        // 计算并提交free
        Bitmap freeRect = max.clone();
        freeRect.y += height;
        freeRect.height -= height;
        freeSet.add(freeRect);
      }
    } else {
      if (isFullY) {
        // 计算并提交free
        Bitmap freeRect = max.clone();
        freeRect.x += width;
        freeRect.width -= width;
        freeSet.add(freeRect);
      }
      // 计算并提交free
      Bitmap freeRect = max.clone();
      freeRect.x += width;
      freeRect.width -= width;
      freeSet.add(freeRect);
    }
    max.width = width;
    max.height = height;
    max.name = name;
    freeSet.remove(max);
    bitmaps.put(name, max);
  }

  protected Bitmap createRect() {
    Bitmap result = null;
    if (textureCount < 14) {
      textureArray[textureCount] = GL30.glGenTextures();
      freeSet.add(result = new Bitmap(0, 0, maxHeight, maxHeight, textureArray[textureCount], "Free Bitmap"));
      textureCount++;
    } else {
      throw new RuntimeException("纹理创建的太多了");
    }
    return result;
  }

  public Bitmap find(int width, int height) {
    Bitmap max = null;
    for (IBitmap bitmap : freeSet) {
      if (bitmap.getWidth() >= width && bitmap.getHeight() >= height) {
        if (max == null || (bitmap.getY() <= max.getY() && bitmap.getX() <= max.getX())) {
          max = (Bitmap) bitmap;
        }
      }
    }
    if(max == null) {
      max = createRect();
    }
    //System.out.println("Max:" + max);
    return max;
  }

  @Override
  public boolean remove(String name) {
    Bitmap result = (Bitmap) bitmaps.remove(name);
    if (result != null && freeSet.add(result) == false) {
      throw new RuntimeException("返回位图出错了");
    }
    return result != null;
  }

  @Override
  public IBitmap get(String name) {
    return bitmaps.get(name);
  }

  @Override
  public Collection<IBitmap> getAll() {
    return bitmaps.values();
  }

  @Override
  public void close() {
    for (int i = 0; i < textureArray.length; i++) {
      GL30.glDeleteTextures(textureArray[i]);
    }
  }
}
