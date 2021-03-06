package golem.symbol.nuds;

import gnu.bytecode.Type;
import golem.generator.Gen;
import golem.generator.GenException;
import golem.parser.Parser;
import golem.symbol.IRvalue;
import golem.symbol.Inud;
import golem.symbol.ParseException;
import golem.symbol.Symbol;
import golem.typesystem.IFunctionTypeResolver;
import golem.typesystem.ITypeResolver;
import golem.typesystem.PlainOldTypeResolver;

public class Typeof implements IRvalue, Inud {

	public static Typeof instance = new Typeof();

	@Override
	public Symbol invoke(Symbol self, Parser p) throws ParseException {

		p.advance("(");
		self.first = p.expression(0);
		p.advance(")");
		self.type = new PlainOldTypeResolver(Type.javalangStringType);
		self.rval = instance;
		return self;
	}

	@Override
	public void invoke(Symbol self, Gen g, boolean genResult) throws GenException {

		if (genResult) {
			ITypeResolver type = self.first().type;
			if (type instanceof IFunctionTypeResolver) {
				g.string(((IFunctionTypeResolver) type).getName());
			} else {
				g.string(self.first().type.get().getName());
			}
		}
	}

}
