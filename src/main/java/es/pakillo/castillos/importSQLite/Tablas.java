package es.pakillo.castillos.importSQLite;

/**
 * Las tablas de la aplicaci�n.
 */
public enum Tablas {
	FRAGMENTOS_JUGADOR,
	PUNTOS_JUGADOR,
	JUGADORES;
	
	public String nombre() {
		return this.name();
	}
}
