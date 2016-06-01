package es.pakillo.castillos.importSQLite;

/**
 * Las tablas de la aplicación.
 */
public enum Tablas {
	FRAGMENTOS_JUGADOR,
	PUNTOS_JUGADOR,
	JUGADORES;
	
	public String nombre() {
		return this.name();
	}
}
