package es.pakillo.castillos.controller;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import es.pakillo.castillos.model.Ingreso;
import es.pakillo.castillos.model.Jugador;
import es.pakillo.castillos.service.ingreso.IngresoService;
import es.pakillo.castillos.service.jugador.JugadorService;

public class CompararJugadoresController extends AbstractController {

	@Autowired
	AbstractApplicationContext context;

	@Autowired
	IngresoService ingresoService;

	@Autowired
	JugadorService jugadorService;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Jugador jugadorA = jugadorService.findFullJugadorById(Long.valueOf(request.getParameter("jugadorA")), true);
		Jugador jugadorB = jugadorService.findFullJugadorById(Long.valueOf(request.getParameter("jugadorB")), true);
		adjustToMinElements(jugadorA, jugadorB);
		getData(request, jugadorA, jugadorB);
		return getData(request, jugadorA, jugadorB);
	}

	/**
	 * @param request
	 * @param modelAndView
	 * @param jugadorA
	 * @param jugadorB
	 */
	private ModelAndView getData(HttpServletRequest request, Jugador jugadorA,
			Jugador jugadorB) {
		final ModelAndView modelAndView = new ModelAndView("comparaJugadores");
		modelAndView.addObject("mode", request.getParameter("mode"));
		modelAndView.addObject("jugadorA", jugadorA);
		modelAndView.addObject("jugadorB", jugadorB);
		modelAndView.addObject("fechas", getHistorialFechas(jugadorA));
		if ("Puntos".equals(request.getParameter("mode"))) {
			modelAndView.addObject("puntosA", getHistorialPuntosJugador(jugadorA));
			modelAndView.addObject("puntosB", getHistorialPuntosJugador(jugadorB));
		} else if ("Fragmentos".equals(request.getParameter("mode"))) {
			modelAndView.addObject("puntosA", getHistorialFragmentosJugador(jugadorA));
			modelAndView.addObject("puntosB", getHistorialFragmentosJugador(jugadorB));
		}
		return modelAndView;
	}

	/**
	 * @param jugadorA
	 * @param jugadorB
	 */
	private void adjustToMinElements(Jugador jugadorA, Jugador jugadorB) {
		if (needToCutElements(jugadorA, jugadorB)) {
			int numIngresos = getNumberOfElements(jugadorA, jugadorB);
			jugadorA.setIngresos(jugadorA.getIngresos().stream().collect(lastN(numIngresos)));
			jugadorB.setIngresos(jugadorB.getIngresos().stream().collect(lastN(numIngresos)));
		}
	}

	/**
	 * @param jugadorA
	 * @param jugadorB
	 * @return
	 */
	private int getNumberOfElements(Jugador jugadorA, Jugador jugadorB) {
		int numIngresos = 10;
		if (jugadorA.getIngresos().size() > jugadorB.getIngresos().size()) {
			numIngresos = jugadorB.getIngresos().size();
		} else if (jugadorB.getIngresos().size() > jugadorA.getIngresos().size()) {
			numIngresos = jugadorA.getIngresos().size();
		}
		if (10 < numIngresos) {
			numIngresos = 10;
		}
		return numIngresos;
	}

	/**
	 * @param jugadorA
	 * @param jugadorB
	 * @return
	 */
	private boolean needToCutElements(Jugador jugadorA, Jugador jugadorB) {
		return jugadorA.getIngresos().size() != jugadorB.getIngresos().size() || jugadorA.getIngresos().size() > 10;
	}

	/**
	 * @param jugador
	 */
	private StringJoiner getHistorialFechas(Jugador jugador) {
		final StringJoiner fechas = new StringJoiner(",", "", "");
		for (final Ingreso i : jugador.getIngresos()) {
			fechas.add("'" + i.getFecha().toString() + "'");
		}
		return fechas;
	}

	/**
	 * @param jugador
	 * @return
	 */
	private StringJoiner getHistorialPuntosJugador(final Jugador jugador) {
		final StringJoiner puntos = new StringJoiner(",", "", "");
		for (final Ingreso i : jugador.getIngresos()) {
			puntos.add(i.getPuntos().toString());
		}
		return puntos;
	}

	private StringJoiner getHistorialFragmentosJugador(final Jugador jugador) {
		final StringJoiner fragmentos = new StringJoiner(",", "", "");
		for (final Ingreso i : jugador.getIngresos()) {
			fragmentos.add(i.getFragmentos().toString());
		}
		return fragmentos;
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

}
