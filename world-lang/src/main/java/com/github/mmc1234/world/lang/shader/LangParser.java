// Generated from com\github\mmc1234\world\lang\shader\Lang.g4 by ANTLR 4.8
package com.github.mmc1234.world.lang.shader;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, ID=6, CHAR=7, WS=8;
	public static final int
		RULE_lang = 0, RULE_pkg = 1, RULE_kv = 2;
	private static String[] makeRuleNames() {
		return new String[] {
			"lang", "pkg", "kv"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'.'", "'='", "'\"'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "ID", "CHAR", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Lang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class LangContext extends ParserRuleContext {
		public List<PkgContext> pkg() {
			return getRuleContexts(PkgContext.class);
		}
		public PkgContext pkg(int i) {
			return getRuleContext(PkgContext.class,i);
		}
		public List<KvContext> kv() {
			return getRuleContexts(KvContext.class);
		}
		public KvContext kv(int i) {
			return getRuleContext(KvContext.class,i);
		}
		public LangContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lang; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterLang(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitLang(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitLang(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LangContext lang() throws RecognitionException {
		LangContext _localctx = new LangContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_lang);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0 || _la==ID) {
				{
				setState(8);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__0:
					{
					setState(6);
					pkg();
					}
					break;
				case ID:
					{
					setState(7);
					kv();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(12);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PkgContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public PkgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pkg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterPkg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitPkg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitPkg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PkgContext pkg() throws RecognitionException {
		PkgContext _localctx = new PkgContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_pkg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(13);
			match(T__0);
			setState(14);
			match(ID);
			setState(15);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KvContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(LangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(LangParser.ID, i);
		}
		public List<TerminalNode> CHAR() { return getTokens(LangParser.CHAR); }
		public TerminalNode CHAR(int i) {
			return getToken(LangParser.CHAR, i);
		}
		public KvContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_kv; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterKv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitKv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitKv(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KvContext kv() throws RecognitionException {
		KvContext _localctx = new KvContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_kv);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17);
			match(ID);
			setState(22);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(18);
				match(T__2);
				setState(19);
				match(ID);
				}
				}
				setState(24);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(25);
			match(T__3);
			setState(26);
			match(T__4);
			setState(28); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(27);
				match(CHAR);
				}
				}
				setState(30); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CHAR );
			setState(32);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\n%\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\3\2\3\2\7\2\13\n\2\f\2\16\2\16\13\2\3\3\3\3\3\3\3\3\3\4\3\4"+
		"\3\4\7\4\27\n\4\f\4\16\4\32\13\4\3\4\3\4\3\4\6\4\37\n\4\r\4\16\4 \3\4"+
		"\3\4\3\4\2\2\5\2\4\6\2\2\2%\2\f\3\2\2\2\4\17\3\2\2\2\6\23\3\2\2\2\b\13"+
		"\5\4\3\2\t\13\5\6\4\2\n\b\3\2\2\2\n\t\3\2\2\2\13\16\3\2\2\2\f\n\3\2\2"+
		"\2\f\r\3\2\2\2\r\3\3\2\2\2\16\f\3\2\2\2\17\20\7\3\2\2\20\21\7\b\2\2\21"+
		"\22\7\4\2\2\22\5\3\2\2\2\23\30\7\b\2\2\24\25\7\5\2\2\25\27\7\b\2\2\26"+
		"\24\3\2\2\2\27\32\3\2\2\2\30\26\3\2\2\2\30\31\3\2\2\2\31\33\3\2\2\2\32"+
		"\30\3\2\2\2\33\34\7\6\2\2\34\36\7\7\2\2\35\37\7\t\2\2\36\35\3\2\2\2\37"+
		" \3\2\2\2 \36\3\2\2\2 !\3\2\2\2!\"\3\2\2\2\"#\7\7\2\2#\7\3\2\2\2\6\n\f"+
		"\30 ";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}