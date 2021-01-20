// Generated from com\github\mmc1234\world\lang\shader\Shader.g4 by ANTLR 4.8
package com.github.mmc1234.world.lang.shader;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ShaderParser}.
 */
public interface ShaderListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ShaderParser#shader}.
	 * @param ctx the parse tree
	 */
	void enterShader(ShaderParser.ShaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShaderParser#shader}.
	 * @param ctx the parse tree
	 */
	void exitShader(ShaderParser.ShaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShaderParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(ShaderParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShaderParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(ShaderParser.LineContext ctx);
}