package com.github.mmc1234.world.lang.shader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.github.mmc1234.world.lang.shader.ShaderParser.LineContext;
import com.github.mmc1234.world.lang.shader.ShaderParser.ShaderContext;

public class Shader extends ShaderBaseVisitor<Object> {
  private ArrayList<String[]> code;

  public static String[][] parse(InputStream in) throws IOException {
    @SuppressWarnings("deprecation")
    ShaderLexer lexer = new ShaderLexer(new ANTLRInputStream(in));
    ShaderParser parse = new ShaderParser(new CommonTokenStream(lexer));
    ShaderContext ctx = parse.shader();
    Shader shader = new Shader();
    shader.visitShader(ctx);
    return shader.code.toArray(new String[0][]);
  }
  
  @Override
  public Object visitLine(LineContext ctx) {
    List<TerminalNode> callList = ctx.ID();  
    String[] args = new String[callList.size()];
    for(int i = 0; i<args.length; i++) {
      args[i] = callList.get(i).toString();
    }
    code.add(args);
    return super.visitLine(ctx);
  }
  
}
