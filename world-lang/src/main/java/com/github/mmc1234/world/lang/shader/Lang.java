package com.github.mmc1234.world.lang.shader;

import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import com.github.mmc1234.world.lang.shader.LangParser.KvContext;
import com.github.mmc1234.world.lang.shader.LangParser.LangContext;
import com.github.mmc1234.world.lang.shader.LangParser.PkgContext;
import com.github.mmc1234.world.lang.shader.ShaderParser.ShaderContext;

import lombok.SneakyThrows;

public class Lang extends LangBaseVisitor<Object> {
  @Override
  public Object visitPkg(PkgContext ctx) {
    System.out.println("Package:"+ctx.ID());
    return super.visitPkg(ctx);
  }
  
  @Override
  public Object visitKv(KvContext ctx) {
    System.out.println("Key:"+ctx.ID()+", Value:"+ctx.CHAR());
    return super.visitKv(ctx);
  }
  
  @SuppressWarnings("deprecation")
  @SneakyThrows
  public static void parse(InputStream in) {
    new Lang().visitLang(new LangParser(new CommonTokenStream(new LangLexer(new ANTLRInputStream(in)))).lang());
  }
}
