package es.pakillo.castillos.controller;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import es.pakillo.castillos.Constants;
import es.pakillo.castillos.model.Alianza;
import es.pakillo.castillos.model.Jugador;

public class GraficaDonacionesController extends BasicController {

	@Override
	protected ModelAndView doHandleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		Long idAlianza = Long.valueOf(request.getParameter("idAlianza"));
		Alianza alianza = alianzaService.findById(idAlianza);
		List<Jugador> jugadores = jugadorService.findByIdAlianza(idAlianza);
		Collections.sort(jugadores, Jugador.byFragmentosDesc);
		StringBuilder nombres = new StringBuilder("['");
		StringBuilder donados = new StringBuilder("['");
		StringBuilder colores = new StringBuilder("['");
		int i = 0;
		for (Jugador jugador : jugadores) {
			nombres.append(jugador.getNombre());
			donados.append(jugador.getFragmentos());
			colores.append(Constants.COLORS_ARRAY[i%50]);
			if (++i != jugadores.size()) {
				nombres.append("', '");
				donados.append("', '");
				colores.append("', '");
			}
		}
		nombres.append("']");
		donados.append("']");
		colores.append("']");
		modelAndView.addObject("nombres", nombres.toString());
		modelAndView.addObject("donados", donados.toString());
		modelAndView.addObject("colores", colores.toString());
		modelAndView.addObject("alianza", alianza.getNombre());
		return modelAndView;
	}

	/**
	 * @param nombre
	 * @return
	 */
	@Deprecated
	@SuppressWarnings("unused")
	private static String getRGBa(String nombre) {
		Color c = new Color(nombre.hashCode());
		StringBuilder colores = new StringBuilder("rgba(");
		colores.append(c.getRed()).append(", ");
		colores.append(c.getGreen()).append(", ");
		colores.append(c.getBlue()).append(", 0.2)");
		String result = colores.toString();
		return result;
	}


	@Override
	protected String doValidate(HttpServletRequest request) {
		final StringBuilder errors = new StringBuilder();
		if (!validateNumericString(request.getParameter("idAlianza"))) {
			errors.append(Constants.URL_ERROR);
		}
		return errors.toString();
	}


}
