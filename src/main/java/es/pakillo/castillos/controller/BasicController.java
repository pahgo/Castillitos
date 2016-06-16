package es.pakillo.castillos.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import es.pakillo.castillos.Constants;
import es.pakillo.castillos.service.alianza.AlianzaService;
import es.pakillo.castillos.service.ingreso.IngresoService;
import es.pakillo.castillos.service.jugador.JugadorService;

public abstract class BasicController extends AbstractController {

	@Autowired
	AbstractApplicationContext context;

	@Autowired
	IngresoService ingresoService;

	@Autowired
	JugadorService jugadorService;
	
	@Autowired
	AlianzaService alianzaService;

	protected abstract String doValidate(HttpServletRequest request);

	protected abstract ModelAndView doHandleRequestInternal(HttpServletRequest request, HttpServletResponse response);
	
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String errors = doValidate(request);
			if (errors.isEmpty()) 
					return doHandleRequestInternal(request, response);
			else
				return goToErrorPage(errors);
		} catch (Exception e) {
			e.printStackTrace();
			return goToErrorPage(Constants.REALLY_BAD + new Date());
		}
	}

	private ModelAndView goToErrorPage(String errors) {
		final ModelAndView modelAndView = new ModelAndView("errorPage");
		modelAndView.addObject("errors", errors);
		return modelAndView;
	}
	
	protected boolean validateStringNotEmpty(String validate, String...acceptedValues) {
		boolean valid = false;
		if (isNotEmpty(validate)) {
			for (String acceptedValue : acceptedValues) {
				if (acceptedValue.equals(validate)) {
					valid = true;
				}
			}
		}
		return valid;
	}

	/**
	 * @param validate
	 * @return
	 */
	private boolean isNotEmpty(String validate) {
		return validate != null && !validate.isEmpty();
	}
	
	protected boolean validateNumericString (String validate) {
		boolean valid = false;
		if (isNotEmpty(validate)) {
			try {
				Long.valueOf(validate);
				valid = true;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return valid;
	}

	protected boolean validateStringListOfNumbers(String validate) {
		boolean result = true;
		String players [] = validate.split(",");
		try{
			for (String p : players) {
				Long.valueOf(p);
			}			
		}catch(NumberFormatException e) {
			result = false;
		}
		return result;
	}
}
