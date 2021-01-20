package com.github.mmc1234.world.toolkit;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.Test;

class TestURLBitmapHandler {

  @Test
  void test() throws IOException {
    URL c = new URL("bitmap://abc");
    c.openConnection();
  }

}
