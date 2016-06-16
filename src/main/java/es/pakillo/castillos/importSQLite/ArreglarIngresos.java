package es.pakillo.castillos.importSQLite;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import es.pakillo.castillos.configuration.AppConfig;
import es.pakillo.castillos.dao.ingreso.IngresoDao;
import es.pakillo.castillos.model.Ingreso;

public class ArreglarIngresos {
	static AbstractApplicationContext context;
	public static void main(String[] args) {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		IngresoDao dao = context.getBean(IngresoDao.class);
		StringBuilder sb = new StringBuilder();
		List<Ingreso> ingresos = dao.findByFecha(LocalDate.parse("2016-06-08"));
		for (Ingreso ingreso : ingresos) {
			ingreso.setPuntosPrevios(ingreso.getPuntos() - ingreso.getPuntosPrevios());
			ingreso.setFragmentosPrevios(ingreso.getFragmentos() - ingreso.getFragmentosPrevios());
			dao.update(ingreso);
		}
		
		System.out.println(sb.toString());
		context.close();
		
	}
	/**
	 * @param dao
	 * @param sb
	 */
	private static void arreglarTodos(IngresoDao dao, StringBuilder sb) {
		for (int i = 1; i < 15; i++) {
			Integer fragmentosPrevios = 0;
			Integer puntosPrevios = 0;
			List<Ingreso> ingresos = dao.findByIdJugador(Long.valueOf(i+ ""), true);
			for (Ingreso ingreso : ingresos) {
				ingreso.setFragmentosPrevios(ingreso.getFragmentos() - fragmentosPrevios);
				if (fragmentosPrevios==0)
					ingreso.setFragmentosPrevios(0);
				fragmentosPrevios = ingreso.getFragmentos();
				ingreso.setPuntosPrevios(ingreso.getPuntos() - puntosPrevios);
				puntosPrevios = ingreso.getPuntos();
				if (puntosPrevios==0)
					ingreso.setPuntosPrevios(0);

//				context.getBean(IngresoDao.class).update(ingreso);
				sb.append("UPDATE INGRESOS SET FRAGMENTOS_PREV = ").append(ingreso.getFragmentosPrevios()).append(", ");
				sb.append("puntos_prev = ").append(ingreso.getPuntosPrevios());
				sb.append(" WHERE ID = ").append(ingreso.getId()).append(";\n");
			}
		}
	}
}
