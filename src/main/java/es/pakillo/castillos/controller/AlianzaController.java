package es.pakillo.castillos.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import es.pakillo.castillos.model.Jugador;
import es.pakillo.castillos.service.jugador.JugadorService;

public class AlianzaController extends AbstractController {

	@Autowired
	AbstractApplicationContext context;

	@Autowired
	JugadorService service;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final ModelAndView modelAndView = new ModelAndView("alianza");
		Long idAlianza = request.getParameter("id") != null ? Long.valueOf(request.getParameter("id")) : 0;
		List<Jugador> jugadores = service.findByIdAlianza(idAlianza);
		jugadores.forEach(p -> p.setProporcion((p.getFragmentos()*100.0)/p.getPuntos()));
		modelAndView.addObject("jugadores", jugadores);
		return modelAndView;
	}

}
