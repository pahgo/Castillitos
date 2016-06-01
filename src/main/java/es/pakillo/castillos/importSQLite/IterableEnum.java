package es.pakillo.castillos.importSQLite;

import java.util.EnumSet;

public class IterableEnum{
	
	@SuppressWarnings("rawtypes")
	public static <E extends Enum<E>> String all(final Class<E> clazz, final Enum prefix) {
		final StringBuilder txt = new StringBuilder(100);
		boolean first = true;
		for(final E a:EnumSet.allOf(clazz)) {
			if (first) {
				first = false;
			}
			else {
				txt.append(", ");
			}
			txt.append(prefix.name()).append(".").append(a.name());
		}
		return txt.toString();
	}

}
