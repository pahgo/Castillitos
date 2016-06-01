package es.pakillo.castillos.importSQLite;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.LocalDate;

import es.pakillo.castillos.model.Fragmentos;



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
	
	public static Fragmentos mapper(final ResultSet rs) throws SQLException {
		Fragmentos fragmentos = new  Fragmentos();
		fragmentos.setFragmentos(rs.getInt(FragmentosJugador.FRAGMENTOS.nombre()));
		fragmentos.setFecha(new LocalDate(rs.getLong(FragmentosJugador.FECHA.nombre())));
		fragmentos.setIdJugador(rs.getInt(FragmentosJugador.ID_JUGADOR.nombre()));
		return fragmentos;
	}
}
