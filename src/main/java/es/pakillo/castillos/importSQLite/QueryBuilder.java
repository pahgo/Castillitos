package es.pakillo.castillos.importSQLite;

/**
 * Objeto que permite la construcción dinámica de consultas.
 */
public final class QueryBuilder {
	
	/** Objeto que contiene la consulta construida. */
	private final StringBuilder sql = new StringBuilder(100);
	
	/** Parte de sentencia: AND */
	private static final String AND = " AND "; //$NON-NLS-1$
	
	/** Parte de sentencia: OR */
	private static final String OR = " OR "; //$NON-NLS-1$
	
	/** Error por argumento no válido. */
	private static final String WRONG_ARGUMENT = "Argumento para query no válido";
	
	/** Parte de sentencia: = */
	private static final String EQUAL = " = "; //$NON-NLS-1$
	
	/** Parte de sentencia: <> */
	private static final String NOT_EQUAL = " <> "; //$NON-NLS-1$
	
	/** Parte de sentencia: IS NULL */
	private static final String IS_NULL = " IS NULL "; //$NON-NLS-1$
	
	/** Parte de sentencia: WHERE */
	private static final String WHERE = " WHERE "; //$NON-NLS-1$
	
	/** Parte de sentencia: SELECT */
	private static final String SELECT = " SELECT "; //$NON-NLS-1$
	
	/** Parte de sentencia: FROM */
	private static final String FROM = " FROM "; //$NON-NLS-1$
	
	/** Parte de sentencia: ( */
	private static final String LBRACKET = " ( "; //$NON-NLS-1$
	
	/** Parte de sentencia: ) */
	private static final String RBRACKET = " ) "; //$NON-NLS-1$
	
	/** Parte de sentencia: * */
	private static final String ALL = " * "; //$NON-NLS-1$
	
	/** Parte de sentencia: 1 = 1 */
	private static final String PRE_QUERY = " 1 = 1 "; //$NON-NLS-1$
	
	/** Exists para la sentencia. */
	private static final String EXISTS = " EXISTS ";
	
	/** Para la función TO_DATE. */
	private static final String TO_DATE = " TO_DATE(";
	
	/** Parte de la sentencia: BETWEEN. */
	private static final String BETWEEN = " BETWEEN ";
	
	/** Parte de la sentencia: ORDER BY. */
	private static final String ORDER = " ORDER BY ";
	
	/** Parte de la sentencia: ASC. */
	private static final String ASC = " ASC ";
	
	/** Parte de la sentencia: DESC. */
	private static final String DESC = " DESC ";
	
	/**
	 * Devuelve el contenido del objeto en un String.
	 * @return La sentencia.
	 */
	public String build() {
		return sql.toString();
	}
	
	/**
	 * Concatena un " AND ".
	 * @return El objeto.
	 */
	public QueryBuilder and() {
		return add(AND);
	}
	
	/**
	 * Concatena un " OR ".
	 * @return El objeto.
	 */
	public QueryBuilder or() {
		return add(OR);
	}
	
	/**
	 * Concatena un " = ".
	 * @return El objeto.
	 */
	public QueryBuilder equal() {
		return add(EQUAL);
	}
	
	/**
	 * Concatena un " <> ".
	 * @return El objeto.
	 */
	public QueryBuilder notEqual() {
		return add(NOT_EQUAL);
	}
	
	/**
	 * Concatena un " IS NULL ".
	 * @return El objeto.
	 */
	public QueryBuilder isNull() {
		return add(IS_NULL);
	}
	
	/**
	 * Concatena un " WHERE ".
	 * @return El objeto.
	 */
	public QueryBuilder where() {
		return add(WHERE);
	}
	
	/**
	 * Concatena un " SELECT ".
	 * @return El objeto.
	 */
	public QueryBuilder select() {
		return add(SELECT);
	}
	
	/**
	 * Concatena un " FROM ".
	 * @return El objeto.
	 */
	public QueryBuilder from() {
		return add(FROM);
	}
	
	/**
	 * Concatena los valores para un select, p.e.: campo1, campo2, campo3
	 * @param fields Lista de campos a concatenar.
	 * @return El objeto.
	 */
	public QueryBuilder valuesSelect(final String fields) {
		return add(fields);
	}
	
	/**
	 * Concatena varias tablas, p.e.: tabla1, tabla2, tabla3
	 * @param tables Lista de tablas a concatenar.
	 * @return El objeto.
	 */
	public QueryBuilder valuesFrom(final String... tables) {
		boolean first = true;
		for(final String table:tables) {
			if(first) {
				first = false;
				add(table);
			}
			else {
				add(", ").add(table);
			}
		}
		return this;
	}
	
	/**
	 * Añade un campo a la sentencia.
	 * @param field La columna a añadir.
	 * @return El objeto.
	 */
	public QueryBuilder field(final String field) {
		return add(field.toUpperCase());
	}
	
	/**
	 * Concatena un " ( ".
	 * @return El objeto.
	 */
	public QueryBuilder openBracket() {
		return add(LBRACKET);
	}
	
	/**
	 * Concatena un " ) ".
	 * @return El objeto.
	 */
	public QueryBuilder closeBracket() {
		return add(RBRACKET);
	}
	
	/**
	 * Concatena un " * ".
	 * @return El objeto.
	 */
	public QueryBuilder all() {
		return add(ALL);
	}
	
	/**
	 * Concatena un " 1 = 1 ".
	 * @return El objeto.
	 */
	public QueryBuilder preQuery() {
		return add(PRE_QUERY);
	}
		
	/**
	 * Concatena el valor EXISTS.
	 * @return El objeto.
	 */
	public QueryBuilder exists() {
		return add(EXISTS);
	}
	
	/**
	 * Concatena un TO_DATE a la sentencia. Requiere la fecha y la máscara de formato.
	 * @param date Cadena conteniendo la cadena.
	 * @param mask Formato para la fecha.
	 * @return El objeto.
	 */
	public QueryBuilder toDate(final String date, final String mask) {
		return add(TO_DATE).add(entrecomillar(date)).add(", ").add(entrecomillar(mask)).closeBracket();
	}
	
	/**
	 * Concatena el valor del objeto pasado como argumento. El argumento debe ser Long, String o Integer. El valor se entrecomilla
	 * antes de concatenarse.
	 * @param o El objeto a agregar.
	 * @return El objeto.
	 * @throws IllegalArgumentException Tipo de argumento no soportado.
	 */
	public QueryBuilder value(final Object o) {
		final String value;
		
		if(o instanceof Long) {
			value = ((Long) o).toString();
		} 
		else if(o instanceof String) {
			value = (String) o;
		}
		else if(o instanceof Integer) {
			value = ((Integer) o).toString();
		}
		else {
			throw new IllegalArgumentException(WRONG_ARGUMENT);
		}
		
		return add(entrecomillar(value));
	}
	
	/**
	 * Añade un literal a la sentencia.
	 * @param s Literal a añadir.
	 * @return El objeto.
	 */
	private QueryBuilder add(final String s) {
		sql.append(s);
		return this;
	}

	@Override
	public String toString() {
		return build();
	}

	/**
	 * Concatena un " between ".
	 * @return El objeto.
	 */
	public QueryBuilder between() {
		return add(BETWEEN);
	}
	
	/**
	 * Concatena un " ORDER BY ".
	 * @return El objeto.
	 */
	public QueryBuilder order() {
		return add(ORDER);
	}
	
	/**
	 * Concatena un " ASC ".
	 * @return El objeto.
	 */
	public QueryBuilder asc() {
		return add(ASC);
	}
	
	/**
	 * Concatena un " DESC ".
	 * @return El objeto.
	 */
	public QueryBuilder desc() {
		return add(DESC);
	}
	
	/**
	 * Método que pone entre comillas simples un texto.
	 * @param cadena La cadena a entrecomillar.
	 * @return '' Si la cadena es nula o vacía, la cadena entrecomillada en otro caso.
	 */
	public static String entrecomillar(final String cadena) {
		String result = "'";
		
		if(cadena != null) {
			result = result.concat(cadena);
		}
		
		return result.concat("'");
	}

}
