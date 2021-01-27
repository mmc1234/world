package com.github.mmc1234.world.toolkit;

import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

import lombok.SneakyThrows;

public class MyUtils {
  @SneakyThrows
  public static String readTestFile(String path) {
    return IOUtils.toString(
        Class.forName("com.github.mmc1234.world.toolkit.TestMD5").getClassLoader().getResourceAsStream(path),
        Charset.defaultCharset());
  }
}
