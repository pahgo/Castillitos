package es.pakillo.castillos.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import es.pakillo.castillos.model.Ingreso;
import es.pakillo.castillos.model.Jugador;
import es.pakillo.castillos.service.alianza.AlianzaService;
import es.pakillo.castillos.service.ingreso.IngresoService;
import es.pakillo.castillos.service.jugador.JugadorService;

public class ActualizaPuntuacionesController extends AbstractController {

	@Autowired
	AbstractApplicationContext context;
	@Autowired
	JugadorService jugadorService;
	@Autowired
	AlianzaService alianzaService;
	@Autowired
	IngresoService ingresoService;
	
	private static final String FRAGMENTOS_PREVIOS = "fragmentosPrevios=";
	private static final String FRAGMENTOS = "fragmentos=";
	private static final String PUNTOS_PREVIOS = "puntosPrevios=";
	private static final String PUNTOS = "puntos=";
	private static final String ID = "id=";

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView modelAndView = new ModelAndView("puntuaciones");
		if (request.getMethod().equalsIgnoreCase(METHOD_GET)) {
			modelAndView = modelForGet(request);
		}
		else if (request.getMethod().equalsIgnoreCase(METHOD_POST)) {			
			persistPuntuaciones(request);
			modelAndView = modelForPost(request);
		}

		return modelAndView;
	}

	/**
	 * @param request
	 */
	private void persistPuntuaciones(HttpServletRequest request) {
		List<Jugador> jugadores = new ArrayList<Jugador>();
		List<Ingreso> ingresos = new ArrayList<Ingreso>();
		
		getData(request.getParameter("content"), jugadores, ingresos);
		for (Jugador jugador : jugadores) {
				jugadorService.updateJugador(jugador);
		}
		for (Ingreso ingreso : ingresos) {
			ingresoService.saveIngreso(ingreso);
		}
	}

	/**
	 * @param full
	 * @param jugadores
	 * @param ingresos
	 */
	private void getData(String full, List<Jugador> jugadores, List<Ingreso> ingresos) {
		String[] lines = full.substring(1).split("jugador\\[");
		for (String line : lines) {
			if (line.contains(ID)){
				jugadores.add(getJugador(line));
				ingresos.add(getIngreso(line));
			}
		}
	}

	/**
	 * @param request
	 * @return
	 */
	private ModelAndView modelForPost(HttpServletRequest request) {
		ModelAndView modelAndView;
		modelAndView = new ModelAndView("alianza");
		Long idAlianza = request.getParameter("id") != null ? Long.valueOf(request.getParameter("id")) : 1;
		List<Jugador> jugadores = jugadorService.findByIdAlianza(idAlianza);
		jugadores.forEach(p -> p.setProporcion(Math.floor((p.getFragmentos()*10000.0)/p.getPuntos())/100));
		modelAndView.addObject("alianza", alianzaService.findById(idAlianza));
		modelAndView.addObject("jugadores", jugadores);
		return modelAndView;
	}

	/**
	 * @param request
	 * @param modelAndView
	 */
	private ModelAndView modelForGet(HttpServletRequest request) {
		final ModelAndView modelAndView = new ModelAndView("puntuaciones");
		modelAndView.addObject("alianza", alianzaService.findById(Long.valueOf(request.getParameter("id"))));
		modelAndView.addObject("jugadores", jugadorService.findByIdAlianza(Long.valueOf(request.getParameter("id"))));
		return modelAndView;
	}

	/**
	 * @param line
	 * @return
	 */
	private Ingreso getIngreso(String line) {
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
	private Jugador getJugador(String line) {
		Jugador jugador = jugadorService.findById(getIdJugador(line));
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
