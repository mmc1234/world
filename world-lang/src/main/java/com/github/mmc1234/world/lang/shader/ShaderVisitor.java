// Generated from com\github\mmc1234\world\lang\shader\Shader.g4 by ANTLR 4.8
package com.github.mmc1234.world.lang.shader;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ShaderParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ShaderVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ShaderParser#shader}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShader(ShaderParser.ShaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link ShaderParser#line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLine(ShaderParser.LineContext ctx);
}