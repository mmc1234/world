// Generated from com\github\mmc1234\world\lang\shader\Lang.g4 by ANTLR 4.8
package com.github.mmc1234.world.lang.shader;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LangParser}.
 */
public interface LangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LangParser#lang}.
	 * @param ctx the parse tree
	 */
	void enterLang(LangParser.LangContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#lang}.
	 * @param ctx the parse tree
	 */
	void exitLang(LangParser.LangContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#pkg}.
	 * @param ctx the parse tree
	 */
	void enterPkg(LangParser.PkgContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#pkg}.
	 * @param ctx the parse tree
	 */
	void exitPkg(LangParser.PkgContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#kv}.
	 * @param ctx the parse tree
	 */
	void enterKv(LangParser.KvContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#kv}.
	 * @param ctx the parse tree
	 */
	void exitKv(LangParser.KvContext ctx);
}