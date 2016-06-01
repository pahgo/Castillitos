package es.pakillo.castillos.importSQLite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.pakillo.castillos.model.Fragmentos;
import es.pakillo.castillos.model.Jugador;
import es.pakillo.castillos.model.Puntos;

public class ImportApp {

	public static void main(String[] args) throws Exception {
		SQLite db = SQLite.DB;
//		printJugadores(db);
//		printPuntos(db);
		printFragmentos(db);
	}

	/**
	 * @param db
	 * @throws SQLException
	 */
	private static void printFragmentos(SQLite db) throws SQLException {
		ResultSet rsFragmentos = db.execute("select * from Fragmentos_JUGADOR");
		List<Fragmentos> fragmentos = new ArrayList<Fragmentos>();
		while(rsFragmentos.next()) {
			fragmentos.add(FragmentosJugador.mapper(rsFragmentos));
		}
		StringBuilder sb = new StringBuilder();
		Long id = 1L;
		for (Fragmentos f : fragmentos) {
			sb.append("Insert into Fragmentos (id, ID_JUGADOR, Fragmentos, FECHA) values (");
			sb.append(id++ + "," + f.getIdJugador() + "," + f.getFragmentos() + ", '"+ f.getFecha() +"');\n");			
		}
		System.out.println(sb.toString());
	}

	/**
	 * @param db
	 * @throws SQLException
	 */
	private static void printPuntos(SQLite db) throws SQLException {
		ResultSet rsPuntos = db.execute("select * from PUNTOS_JUGADOR");
		List<Puntos> puntos = new ArrayList<Puntos>();
		while(rsPuntos.next()) {
			puntos.add(PuntosJugador.mapper(rsPuntos));
		}
		StringBuilder sb = new StringBuilder();
		Long id = 1L;
		for (Puntos p : puntos) {
			sb.append("Insert into PUNTOS (id, ID_JUGADOR, PUNTOS, FECHA) values (");
			sb.append(id++ + "," + p.getIdJugador() + "," + p.getPuntos() + ", '"+ p.getFecha() +"');\n");			
		}
		System.out.println(sb.toString());
	}

	/**
	 * @param db
	 * @throws SQLException
	 */
	private static void printJugadores(SQLite db) throws SQLException {
		ResultSet rsJugadores = db.execute("select * from jugadores");
		List<Jugador> jugadores = new ArrayList<Jugador>();
		Jugador jugador = new Jugador();
		Long id = 2L;
		while(rsJugadores.next()) {
			jugador = Jugadores.mapper(rsJugadores);
			jugador.setId(id++);
			jugadores.add(jugador);
		}

		StringBuilder sb = new StringBuilder();
		for (Jugador j : jugadores) {
			sb.append("Insert into jugador (id, nombre, aka, fecha_registro, id_alianza) values (");
			sb.append(j.getId() + ",'" + j.getNombre() + "',null, null, 1);\n");			
		}
		System.out.println(sb.toString());
	}

}
