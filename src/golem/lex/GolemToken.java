package golem.lex;

import golem.generator.GenException;
import golem.symbol.ParseException;

public class GolemToken implements Cloneable, Token {

	public int line;
	public int pos;
	public int type;
	public String val;

	@Override
	public GolemToken clone() {
		GolemToken tok = new GolemToken();
		tok.line = line;
		tok.pos = pos;
		tok.type = type;
		tok.val = val;

		return tok;
	}

	@Override
	public void error(String msg) throws ParseException {
		System.err.println("Error(" + line + ":" + pos + ") : " + msg);
		throw new ParseException(msg);
	}

	@Override
	public void error(String msg, Throwable e) throws ParseException {
		System.err.println("Error(" + line + ":" + pos + ") : " + msg);
		throw new ParseException(msg, e);
	}

	@Override
	public void warning(String msg) {
		System.out.println("Warning(" + line + ":" + pos + ") : " + msg);
	}

	@Override
	public void genError(String msg, Throwable e) throws GenException {
		System.err.println("Codegen error(" + line + ":" + pos + ") : " + msg);
		throw new GenException(msg, e);
	}

	@Override
	public void genError(String msg) throws GenException {
		System.err.println("Codegen error(" + line + ":" + pos + ") : " + msg);
		throw new GenException(msg);
	}

	@Override
	public void genWarning(String msg) {
		System.out.println("Codegen warning(" + line + ":" + pos + ") : " + msg);
	}

	@Override
	public int type() {
		return type;
	}

	@Override
	public String value() {
		return val;
	}

}
