package com.github.mmc1234.world.toolkit;

import java.io.IOException;
import java.io.OutputStream;
import java.util.stream.Stream;

public class ArrayBufferOutputStream extends OutputStream {
  private int offset;
  private byte[] buffer;
  @Override
  public void write(int b) throws IOException {
    buffer[offset++] = (byte) (b >> 0 & 0xff);
    buffer[offset++] = (byte) (b >> 8 & 0xff);
    buffer[offset++] = (byte) (b >> 16 & 0xff);
    buffer[offset++] = (byte) (b >> 32 & 0xff);
  }
  
  @Override
  public void flush() throws IOException {
  }
  
  public void reset() {
    offset = 0;
  }

}