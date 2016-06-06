package es.pakillo.castillos.importSQLite;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import es.pakillo.castillos.model.Ingreso;
import es.pakillo.castillos.model.Jugador;

public class PruebaUpdate {

	private static final String FRAGMENTOS_PREVIOS = "fragmentosPrevios=";
	private static final String FRAGMENTOS = "fragmentos=";
	private static final String PUNTOS_PREVIOS = "puntosPrevios=";
	private static final String PUNTOS = "puntos=";
	private static final String ID = "id=";
	static String aux = "[jugador[id=5,puntos=1,puntosPrevios=158564,fragmentos=15,fragmentosPrevios=0]jugador[id=3,puntos=2,puntosPrevios=120554,fragmentos=16,fragmentosPrevios=71748]jugador[id=12,puntos=3,puntosPrevios=82517,fragmentos=17,fragmentosPrevios=49744]jugador[id=2,puntos=4,puntosPrevios=69910,fragmentos=18,fragmentosPrevios=45840]jugador[id=13,puntos=5,puntosPrevios=69880,fragmentos=19,fragmentosPrevios=11000]jugador[id=7,puntos=6,puntosPrevios=63019,fragmentos=20,fragmentosPrevios=9613]jugador[id=9,puntos=7,puntosPrevios=62532,fragmentos=21,fragmentosPrevios=8514]jugador[id=4,puntos=8,puntosPrevios=58779,fragmentos=22,fragmentosPrevios=33889]jugador[id=8,puntos=9,puntosPrevios=46331,fragmentos=23,fragmentosPrevios=4789]jugador[id=1,puntos=10,puntosPrevios=41140,fragmentos=24,fragmentosPrevios=6301]jugador[id=6,puntos=11,puntosPrevios=21345,fragmentos=25,fragmentosPrevios=1914]jugador[id=11,puntos=12,puntosPrevios=16148,fragmentos=26,fragmentosPrevios=159]jugador[id=10,puntos=13,puntosPrevios=15943,fragmentos=27,fragmentosPrevios=8637]jugador[id=14,puntos=14,puntosPrevios=12063,fragmentos=28,fragmentosPrevios=1095]]";

	public static void main(String[] args) {
		String full = aux;
		String[] lines = full.substring(1).split("jugador\\[");
		List<Jugador> jugadores = new ArrayList<Jugador>();
		List<Ingreso> ingresos = new ArrayList<Ingreso>();
		for (String line : lines) {
			if (line.contains(ID)){
				jugadores.add(getJugador(line));
				ingresos.add(getIngreso(line));
			}
		}
	}

	/**
	 * @param line
	 * @return
	 */
	private static Ingreso getIngreso(String line) {
		Ingreso ingreso = new Ingreso();
		ingreso.setIdJugador(getIdJugador(line));
		ingreso.setFragmentos(getFragmentos(line));
		ingreso.setFragmentosPrevios(getFragmentosPrevios(line));
		ingreso.setFecha(new LocalDate());
		ingreso.setPuntos(getPuntos(line));
		ingreso.setPuntosPrevios(getPuntosPrevios(line));
		return ingreso;
	}

	/**
	 * @param line
	 * @return
	 */
	private static Jugador getJugador(String line) {
		Jugador jugador = new Jugador();
		//FIXME; Cambiar por findById(idJugador) 
		jugador.setId(getIdJugador(line));//fin fixme;
		jugador.setPuntos(getPuntos(line));
		jugador.setPuntosPrevios(getPuntosPrevios(line));
		jugador.setFragmentos(getFragmentos(line));
		jugador.setFragmentosPrevios(getFragmentosPrevios(line));
		return jugador;
	}

	/**
	 * @param line
	 * @return
	 */
	private static Long getIdJugador(String line) {
		return Long.valueOf(getCampo(line, ID));
	}

	/**
	 * @param line
	 * @return
	 */
	private static Integer getPuntos(String line) {
		return Integer.valueOf(getCampo(line, PUNTOS));
	}

	/**
	 * @param line
	 * @return
	 */
	private static Integer getPuntosPrevios(String line) {
		return Integer.valueOf(getCampo(line, PUNTOS_PREVIOS));
	}

	/**
	 * @param line
	 * @return
	 */
	private static Integer getFragmentos(String line) {
		return Integer.valueOf(getCampo(line, FRAGMENTOS));
	}

	/**
	 * @param line
	 * @return
	 */
	private static Integer getFragmentosPrevios(String line) {
		return Integer.valueOf(getCampo(line, FRAGMENTOS_PREVIOS));
	}

	/**
	 * @param line
	 * @param campo
	 * @return
	 */
	private static String getCampo(String line, String campo) {
		int fromIndex = line.indexOf(campo) + campo.length();
		int endIndex = line.indexOf(",", fromIndex) > 0 ? line.indexOf(",", fromIndex) : line.indexOf("]", fromIndex);
		return line.substring(fromIndex, endIndex);
	}	
}
