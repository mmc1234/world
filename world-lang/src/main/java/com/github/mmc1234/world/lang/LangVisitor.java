// Generated from com\github\mmc1234\world\lang\Lang.g4 by ANTLR 4.8
package com.github.mmc1234.world.lang;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LangParser#lang}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLang(LangParser.LangContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#pkg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPkg(LangParser.PkgContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#kv}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKv(LangParser.KvContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(LangParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(LangParser.VarContext ctx);
}