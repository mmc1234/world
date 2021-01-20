package com.github.mmc1234.world.lang;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;

import org.junit.jupiter.api.Test;

import com.github.mmc1234.world.lang.shader.Lang;
import com.github.mmc1234.world.lang.shader.Shader;

import lombok.SneakyThrows;

class TestLang {

  @Test
  @SneakyThrows
  void test() {
    File file = new File("target/classes/zh_CN.lang");
    System.out.println(file.getAbsolutePath());
    Lang.parse(new FileInputStream(file));
  }

}
