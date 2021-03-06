package golem.symbol;

import java.util.*;

import golem.generator.*;
import golem.lex.Token;
import golem.parser.Scope;
import golem.symbol.leds.Null_led;
import golem.symbol.nuds.Null_nud;
import golem.typesystem.ITypeResolver;

public class Symbol {

	public Token token = null;
	public int lbp = 0;
	public Inud nud = Null_nud.instance;
	public Iled led = Null_led.instance;
	public IRvalue rval = null;
	public ILvalue lval = null;
	public Symbol proto = null;
	public Scope scope = null;
	public ITypeResolver type = null;

	public Map<String, Object> tags = new HashMap<String, Object>(0);

	public Object first = null;
	public Object second = null;
	public Object third = null;

	public Object tag(String name) {
		return tags.get(name);
	}

	public void putTag(String name, Object o) {
		tags.put(name, o);
	}

	public void invokeRval(Gen g, boolean genResult) throws GenException {
		try {
			rval.invoke(this, g, genResult);
		} catch (Exception e) {
			token.error(e.getMessage(), e);
		}
	}

	public void invokeLval(Gen g, Symbol val) throws GenException {
		try {
			lval.invoke(this, g, val);
		} catch (Exception e) {
			token.error(e.getMessage(), e);
		}
	}

	public ITypeResolver type() {
		return type;
	}

	public Symbol first() {
		return (Symbol) first;
	}

	public Symbol second() {
		return (Symbol) second;
	}

	public Symbol third() {
		return (Symbol) third;
	}

	@Override
	public Symbol clone() {

		Symbol smb = new Symbol();
		smb.token = token;
		smb.lbp = lbp;
		smb.nud = nud;
		smb.led = led;
		smb.rval = rval;
		smb.proto = proto;
		smb.scope = scope;
		smb.tags = new HashMap<String, Object>(tags);

		return smb;
	}

	@Override
	public String toString() {
		if (token != null) {
			return token.value();
		} else {
			return "<null>";
		}
	}
}
