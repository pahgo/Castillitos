package es.pakillo.castillos.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import es.pakillo.castillos.service.ingreso.IngresoService;
import es.pakillo.castillos.service.jugador.JugadorService;

public class JugadorController extends AbstractController {

	@Autowired
	AbstractApplicationContext context;

	@Autowired
	IngresoService ingresoService;
	
	@Autowired
	JugadorService jugadorService;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final ModelAndView modelAndView = new ModelAndView("jugador");
		Long idJugador = request.getParameter("id") != null ? Long.valueOf(request.getParameter("id")) : 0;
		modelAndView.addObject("ingresos", ingresoService.findByIdJugador(idJugador, false));
		modelAndView.addObject("jugador", jugadorService.findById(idJugador));
		return modelAndView;
	}

}
