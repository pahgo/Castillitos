package es.pakillo.castillos.importSQLite;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import es.pakillo.castillos.configuration.AppConfig;
import es.pakillo.castillos.dao.ingreso.IngresoDao;
import es.pakillo.castillos.dao.jugador.JugadorDao;
import es.pakillo.castillos.model.Ingreso;
import es.pakillo.castillos.model.Jugador;

public class UpdateImportAPP {

	public static void main(String[] args) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		List<Jugador> jugadores = context.getBean(JugadorDao.class).findAllJugadores();

		for (Jugador jugador : jugadores) {
			List<Ingreso> ingresosJugador = context.getBean(IngresoDao.class).findByIdJugador(jugador.getId());
			Collections.sort(ingresosJugador);
			Ingreso ultimoIngreso = ingresosJugador.get(ingresosJugador.size() - 1);
			jugador.setPuntos(ultimoIngreso.getPuntos());
			jugador.setPuntosPrevios(ultimoIngreso.getPuntosPrevios());
			jugador.setFragmentos(ultimoIngreso.getFragmentos());
			jugador.setFragmentosPrevios(ultimoIngreso.getFragmentosPrevios());
			context.getBean(JugadorDao.class).updateJugador(jugador);
		}
		context.close();
	}

}
