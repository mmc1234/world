package com.github.mmc1234.world.lang;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.UnbufferedCharStream;
import org.junit.jupiter.api.Test;

import com.github.mmc1234.world.lang.shader.Shader;
import com.github.mmc1234.world.lang.shader.ShaderLexer;
import com.github.mmc1234.world.lang.shader.ShaderParser;

import lombok.SneakyThrows;

class TestShader {

  @Test
  @SneakyThrows
  void test() {
    File file = new File("target/classes/base.ss");
    System.out.println(file.getAbsolutePath());
    System.out.println(Shader.parse(new FileInputStream(file)));;
  }

}
