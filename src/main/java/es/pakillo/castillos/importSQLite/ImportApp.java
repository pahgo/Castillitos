package es.pakillo.castillos.importSQLite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.joda.time.LocalDate;

import es.pakillo.castillos.model.Ingreso;
import es.pakillo.castillos.model.Jugador;

public class ImportApp {

	static List<Ingreso> ingresos = new ArrayList<Ingreso>();

	public static void main(String[] args) throws Exception {
		SQLite db = SQLite.DB;
		printJugadores(db);
		getPuntos(db);
		getFragmentos(db);
		getIngresos();
		extras();
	}

	private static void extras() {
		System.out.println("UPDATE JUGADORES SET ID_OLD = ID;");
		
	}

	/**
	 * 
	 */
	private static void getIngresos() {
		mergeIngresos();
		printIngresos();
	}

	/**
	 * 
	 */
	private static void printIngresos() {
		StringBuffer sb = new StringBuffer();
		LocalDate prev = LocalDate.now();
		for (int i = 0; i < ingresos.size(); i++) {
			Ingreso ingreso = ingresos.get(i);
			if (ingreso.getFecha() != null && !ingreso.getFecha().equals(prev)) {//Me cargo los impares
				sb.append("INSERT INTO INGRESOS (ID, ID_JUGADOR, PUNTOS, PUNTOS_PREV, FRAGMENTOS, FRAGMENTOS_PREV, FECHA) VALUES (");
				sb.append(1 + i/2).append(", ");
				sb.append(ingreso.getIdJugador()).append(", ");
				sb.append(ingreso.getPuntos()).append(", ");
				sb.append(ingreso.getPuntosPrevios()).append(", ");
			}
			else {
				sb.append(ingreso.getFragmentos()).append(", ");
				sb.append(ingreso.getFragmentosPrevios()).append(", ");
				sb.append("'").append(ingreso.getFecha()).append("');\n");				
			}
			if(ingreso.getIdJugador().equals(43L)) {
				
				sb.append("INSERT INTO INGRESOS (ID, ID_JUGADOR, PUNTOS, PUNTOS_PREV, FRAGMENTOS, FRAGMENTOS_PREV, FECHA) VALUES (");
				sb.append(1 + i/2).append(", ");
				sb.append(ingreso.getIdJugador()).append(", ");
				sb.append(ingreso.getPuntos()).append(", ");
				sb.append(ingreso.getPuntosPrevios()).append(", ");
				sb.append(ingreso.getFragmentos()).append(", ");
				sb.append(ingreso.getFragmentosPrevios()).append(", ");
				sb.append("'").append(ingreso.getFecha()).append("');\n");				
			}
			prev = ingreso.getFecha();
		}
		System.out.println(sb.toString());
	}

	/**
	 * 
	 */
	private static void mergeIngresos() {
		Collections.sort(ingresos);
		Ingreso aux = ingresos.get(0);
		for (Ingreso i : ingresos) {
			if (i.getIdJugador().equals(aux.getIdJugador())) {
				if (i.getFragmentos() == null) {
					i.setFragmentos(aux.getFragmentos());
				}
				if (i.getFragmentosPrevios() == null && aux.getFragmentos() != null) {
					i.setFragmentosPrevios(aux.getFragmentos());
				}
				if (i.getPuntos() == null && aux.getPuntos() != null) {
					i.setPuntos(aux.getPuntos());
				}
				if (i.getPuntosPrevios() == null) {
					i.setPuntosPrevios(aux.getPuntos());
				}
			}
			aux = i;
		}
	}

	/**
	 * @param db
	 * @throws SQLException
	 */
	private static void getFragmentos(SQLite db) throws SQLException {
		ResultSet rsFragmentos = db.execute("SELECT * FROM FRAGMENTOS_JUGADOR");
		while (rsFragmentos.next()) {
			ingresos.add(FragmentosJugador.mapper(rsFragmentos));
		}
	}

	/**
	 * @param db
	 * @throws SQLException
	 */
	private static void getPuntos(SQLite db) throws SQLException {
		ResultSet rsPuntos = db.execute("SELECT * FROM PUNTOS_JUGADOR");
		while (rsPuntos.next()) {
			ingresos.add(PuntosJugador.mapper(rsPuntos));
		}
	}

	/**
	 * @param db
	 * @throws SQLException
	 */
	private static void printJugadores(SQLite db) throws SQLException {
		ResultSet rsJugadores = db.execute("SELECT * FROM JUGADORES");
		List<Jugador> jugadores = new ArrayList<Jugador>();
		while (rsJugadores.next()) {
			jugadores.add(Jugadores.mapper(rsJugadores));
		}

		StringBuilder sb = new StringBuilder();
		Long id = 1l;
		for (Jugador j : jugadores) {
			sb.append("INSERT INTO JUGADORES (ID, ID_OLD, NOMBRE, AKA, FECHA_REGISTRO, ID_ALIANZA) VALUES (");
			sb.append(id++ + ", " + j.getId() + ",'" + j.getNombre() + "',null, null, 1);\n");
		}
		System.out.println(sb.toString());
	}

}
