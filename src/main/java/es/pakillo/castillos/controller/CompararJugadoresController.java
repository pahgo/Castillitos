package es.pakillo.castillos.controller;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import es.pakillo.castillos.Constants;
import es.pakillo.castillos.model.Ingreso;
import es.pakillo.castillos.model.Jugador;

public class CompararJugadoresController extends BasicController {

	private static final int NUMBER_OF_ELEMENTS = 30;

	@Override
	protected ModelAndView doHandleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		String players [] = request.getParameter("jugadores").split(",");
		List<Jugador> jugadores = new ArrayList<Jugador>();
		for (String p : players) {
			jugadores.add(jugadorService.findFullJugadorById(Long.valueOf(p), true));
		}
		adjustToMinElements(jugadores);
		return getData(request, jugadores);
	}

	/**
	 * @param request
	 * @param modelAndView
	 * @param jugadorA
	 * @param jugadorB
	 */
	private ModelAndView getData(HttpServletRequest request, List<Jugador> jugadores) {
		final ModelAndView modelAndView = new ModelAndView("comparaJugadores");
		modelAndView.addObject("fechas", getHistorialFechas(jugadores.get(0)));
		StringJoiner puntos = new StringJoiner(",", "[", "]");
		StringJoiner nombres = new StringJoiner(",", "[", "]");
		StringJoiner colores = new StringJoiner(",", "[", "]");
		int i = 0;
		if (Constants.PUNTOS.equals(request.getParameter(Constants.MODE))) {
			Collections.sort(jugadores, Jugador.byPuntosDesc);
		} else if (Constants.FRAGMENTOS.equals(request.getParameter(Constants.MODE))) {
			Collections.sort(jugadores, Jugador.byFragmentosDesc);
		} 
		for (Jugador j : jugadores) {
			if (Constants.PUNTOS.equals(request.getParameter(Constants.MODE))) {
				puntos.add(getHistorialPuntosJugador(j));
			} else if (Constants.FRAGMENTOS.equals(request.getParameter(Constants.MODE))) {
				puntos.add(getHistorialFragmentosJugador(j));
			}
			nombres.add("'" + j.getNombre() + "'");
			colores.add("'" + Constants.COLORS_ARRAY[i++%50] +"'");
		}
		modelAndView.addObject("puntos", puntos.toString());
		modelAndView.addObject("colores", colores);
		modelAndView.addObject(Constants.MODE, request.getParameter(Constants.MODE));
		modelAndView.addObject("nombres", nombres);

		return modelAndView;
	}

	/**
	 * @param jugadorA
	 * @param jugadorB
	 */
	private void adjustToMinElements(List<Jugador> jugadores) {
		int numIngresos = getNumberOfElements(jugadores);
		for (Jugador j : jugadores) {
			j.setIngresos(j.getIngresos().stream().collect(lastN(numIngresos)));
		}
	}

	/**
	 * @param jugadorA
	 * @param jugadorB
	 * @return
	 */
	private int getNumberOfElements(List<Jugador> jugadores) {
		int numIngresos = NUMBER_OF_ELEMENTS;
		for (Jugador j : jugadores) {
			numIngresos = Math.min(numIngresos, j.getIngresos().size());
		}
		return numIngresos;
	}

	/**
	 * @param jugador
	 */
	private String getHistorialFechas(Jugador jugador) {
		final StringJoiner fechas = new StringJoiner(",", "[", "]");
		for (final Ingreso i : jugador.getIngresos()) {
			fechas.add("'" + i.getFecha().toString() + "'");
		}
		return fechas.toString();
	}

	/**
	 * @param jugador
	 * @return
	 */
	private String getHistorialPuntosJugador(final Jugador jugador) {
		final StringJoiner puntos = new StringJoiner(",", "[", "]");
		for (final Ingreso i : jugador.getIngresos()) {
			puntos.add(i.getPuntos().toString());
		}
		return puntos.toString();
	}

	private String getHistorialFragmentosJugador(final Jugador jugador) {
		final StringJoiner fragmentos = new StringJoiner(",", "[", "]");
		for (final Ingreso i : jugador.getIngresos()) {
			fragmentos.add(i.getFragmentos().toString());
		}
		return fragmentos.toString();
	}

	public static <T> Collector<T, ?, List<T>> lastN(int n) {
		return Collector.<T, Deque<T>, List<T>> of(ArrayDeque::new, (acc, t) -> {
			if (acc.size() == n)
				acc.pollFirst();
			acc.add(t);
		} , (acc1, acc2) -> {
			while (acc2.size() < n && !acc1.isEmpty()) {
				acc2.addFirst(acc1.pollLast());
			}
			return acc2;
		} , ArrayList<T>::new);
	}

	@Override
	protected String doValidate(HttpServletRequest request) {
		StringBuilder errors = new StringBuilder();
		if (!validateStringNotEmpty(request.getParameter(Constants.MODE), Constants.PUNTOS, Constants.FRAGMENTOS))
			errors.append(Constants.URL_ERROR);
		if (!validateNumericString(request.getParameter("cont"))) 
			errors.append(Constants.URL_ERROR);
		if (!validateStringListOfNumbers(request.getParameter("jugadores"))) 
			errors.append(Constants.URL_ERROR);
		return errors.toString();
	}
}
