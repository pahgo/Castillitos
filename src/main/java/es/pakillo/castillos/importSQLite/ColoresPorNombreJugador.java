package es.pakillo.castillos.importSQLite;

import java.awt.Color;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import es.pakillo.castillos.configuration.AppConfig;
import es.pakillo.castillos.model.Jugador;
import es.pakillo.castillos.service.jugador.JugadorService;

public class ColoresPorNombreJugador {
	static AbstractApplicationContext context;

	public static void main(String[] args) {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		Long idAlianza = Long.valueOf(1l);
		List<Jugador> jugadores = context.getBean(JugadorService.class).findByIdAlianza(idAlianza);
		StringBuilder nombres = new StringBuilder("['");
		StringBuilder donados = new StringBuilder("['");
		StringBuilder colores = new StringBuilder("['");
		int i = 0;
		for (Jugador jugador : jugadores) {
			nombres.append(jugador.getNombre());
			donados.append(jugador.getFragmentos());
			colores.append(getRGBa(jugador.getNombre()));
			if (++i != jugadores.size()) {
				nombres.append("', '");
				donados.append("', '");
				colores.append("', '");
			}
		}
		nombres.append("']");
		donados.append("']");
		colores.append("']");
		System.out.println(nombres.toString());
		System.out.println(donados.toString());
		System.out.println(colores.toString());

	}

	private static String getRGBa(String nombre) {
		Color c = new Color(nombre.hashCode());
		StringBuilder colores = new StringBuilder("rbga(");
		colores.append(c.getRed()).append(", ");
		colores.append(c.getGreen()).append(", ");
		colores.append(c.getBlue()).append(", 0,2)");
		String result = colores.toString();
		return result;
	}

}
