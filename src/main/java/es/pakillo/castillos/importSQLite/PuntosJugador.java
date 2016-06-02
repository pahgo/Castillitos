package es.pakillo.castillos.importSQLite;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.LocalDate;

import es.pakillo.castillos.model.Ingreso;


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
	
	public static Ingreso mapper(final ResultSet rs) throws SQLException {
		Ingreso ingresos = new Ingreso();
		ingresos.setFecha(new LocalDate(rs.getLong(PuntosJugador.FECHA.nombre())));
		ingresos.setIdJugador(rs.getLong(PuntosJugador.ID_JUGADOR.nombre()));
		ingresos.setPuntos(rs.getInt(PuntosJugador.PUNTOS.nombre()));
		return ingresos;
	}
}
