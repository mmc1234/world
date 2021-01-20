// Generated from com\github\mmc1234\world\lang\shader\Shader.g4 by ANTLR 4.8
package com.github.mmc1234.world.lang.shader;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link ShaderVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class ShaderBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements ShaderVisitor<T> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitShader(ShaderParser.ShaderContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitLine(ShaderParser.LineContext ctx) { return visitChildren(ctx); }
}