package es.pakillo.castillos.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import es.pakillo.castillos.Constants;
import es.pakillo.castillos.model.Jugador;

public class AlianzaController extends BasicController {

	@Override
	protected String doValidate(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		if (!validateNumericString(request.getParameter(Constants.ID))) {
			sb.append(Constants.URL_ERROR);
		}
		return sb.toString();
	}

	@Override
	protected ModelAndView doHandleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		final ModelAndView modelAndView = new ModelAndView("alianza");
		Long idAlianza = request.getParameter(Constants.ID) != null ? Long.valueOf(request.getParameter(Constants.ID)) : 1;
		List<Jugador> jugadores = jugadorService.findByIdAlianza(idAlianza);
		jugadores.forEach(p -> p.setProporcion(Math.floor((p.getFragmentos()*10000.0)/p.getPuntos())/100));
		modelAndView.addObject("alianza", alianzaService.findById(idAlianza));
		modelAndView.addObject("jugadores", jugadores);
		return modelAndView;
	}

}
