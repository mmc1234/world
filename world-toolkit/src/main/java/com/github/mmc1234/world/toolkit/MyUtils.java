package com.github.mmc1234.world.toolkit;

import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

import lombok.SneakyThrows;

public class MyUtils {
  @SneakyThrows
  public static String readTestFile(String path) {
    return IOUtils.toString(
        MyUtils.class.getClassLoader().getResourceAsStream(path),
        Charset.defaultCharset());
    /*return IOUtils.toString(
        Class.forName("com.github.mmc1234.world.toolkit.TestRenderer").getClassLoader().getResourceAsStream(path),
        Charset.defaultCharset());*/
  }
}
