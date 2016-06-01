package es.pakillo.castillos.importSQLite;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.LocalDate;

import es.pakillo.castillos.model.Puntos;


public enum PuntosJugador {
	ID_PUNTOS,
	ID_JUGADOR,
	PUNTOS,
	FECHA;
	
	public String nombre() {
		return this.name();
	}

	public static String allFields() {
		return IterableEnum.all(PuntosJugador.class, Tablas.PUNTOS_JUGADOR);
	}
	
	public static Puntos mapper(final ResultSet rs) throws SQLException {
		Puntos puntos = new Puntos();
		puntos.setFecha(new LocalDate(rs.getLong(PuntosJugador.FECHA.nombre())));
		puntos.setIdJugador(rs.getLong(PuntosJugador.ID_JUGADOR.nombre()));
		puntos.setPuntos(rs.getLong(PuntosJugador.PUNTOS.nombre()));
		return puntos;
	}
}
