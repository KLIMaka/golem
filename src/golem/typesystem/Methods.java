package golem.typesystem;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.Type;

import java.util.List;

public class Methods {

	private ClassType m_clazz;
	private String m_name;

	public Methods(ClassType clazz, String name) {
		m_clazz = clazz;
		m_name = name;
	}

	public Method match(Type[] args) {
		List<Method> m = TypeUtils.searchMethod(m_clazz, m_name, args);
		if (m == null) {
			return null;
		}
		return m.get(0);
	}

	public List<Method> get() {
		return TypeUtils.searchMethod(m_clazz, m_name, null);
	}

	public String getName() {
		return m_name;
	}

	public ClassType getClazz() {
		return m_clazz;
	}

}
