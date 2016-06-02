package es.pakillo.castillos.importSQLite;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.LocalDate;

import es.pakillo.castillos.model.Ingreso;



public enum FragmentosJugador {
	ID_FRAGMENTOS,
	ID_JUGADOR,
	FRAGMENTOS,
	FECHA;
	
	public String nombre() {
		return this.name();
	}
	
	public static String allFields() {
		return IterableEnum.all(FragmentosJugador.class, Tablas.FRAGMENTOS_JUGADOR);
	}
	
	public static Ingreso mapper(final ResultSet rs) throws SQLException {
		Ingreso ingresos = new Ingreso();
		ingresos.setFragmentos(rs.getInt(FragmentosJugador.FRAGMENTOS.nombre()));
		ingresos.setFecha(new LocalDate(rs.getLong(FragmentosJugador.FECHA.nombre())));
		ingresos.setIdJugador(rs.getLong(FragmentosJugador.ID_JUGADOR.nombre()));
		return ingresos;
	}
}
