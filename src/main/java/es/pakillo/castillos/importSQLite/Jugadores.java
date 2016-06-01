package es.pakillo.castillos.importSQLite;

import java.sql.ResultSet;
import java.sql.SQLException;

import es.pakillo.castillos.model.Jugador;

public enum Jugadores {
	ID_JUGADOR, NOMBRE, ACTIVO;

	public String nombre() {
		return this.name();
	}

	public static String allFields() {
		return IterableEnum.all(Jugadores.class, Tablas.JUGADORES);
	}

	public static Jugador mapper(final ResultSet rs) throws SQLException {
		Jugador jugador = new Jugador();
//		jugador.setId(rs.getLong(Jugadores.ID_JUGADOR.nombre()));
		jugador.setNombre(rs.getString(Jugadores.NOMBRE.nombre()));
		jugador.setIdAlianza(1L);
		return jugador;
	}
}
