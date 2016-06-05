package es.pakillo.castillos.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import es.pakillo.castillos.model.Jugador;
import es.pakillo.castillos.service.alianza.AlianzaService;
import es.pakillo.castillos.service.jugador.JugadorService;

public class AlianzaController extends AbstractController {

	@Autowired
	AbstractApplicationContext context;

	@Autowired
	JugadorService jugadorService;
	
	@Autowired
	AlianzaService alianzaService;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final ModelAndView modelAndView = new ModelAndView("alianza");
		Long idAlianza = request.getParameter("id") != null ? Long.valueOf(request.getParameter("id")) : 1;
		List<Jugador> jugadores = jugadorService.findByIdAlianza(idAlianza);
		jugadores.forEach(p -> p.setProporcion(Math.floor((p.getFragmentos()*10000.0)/p.getPuntos())/100));
		modelAndView.addObject("alianza", alianzaService.findById(idAlianza));
		modelAndView.addObject("jugadores", jugadores);
		return modelAndView;
	}

}
