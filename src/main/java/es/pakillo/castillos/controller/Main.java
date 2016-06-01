package es.pakillo.castillos.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import es.pakillo.castillos.service.alianza.AlianzaService;

public class Main extends AbstractController {
	
	@Autowired
	AbstractApplicationContext context;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("index");
		AlianzaService service = (AlianzaService) context.getBean("alianzaService");
		modelAndView.addObject("alianzas", service.findAllAlianzas());
		return modelAndView;
	}

}
