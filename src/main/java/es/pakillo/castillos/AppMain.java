package es.pakillo.castillos;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import es.pakillo.castillos.configuration.AppConfig;
import es.pakillo.castillos.model.Alianza;
import es.pakillo.castillos.service.alianza.AlianzaService;

public class AppMain {

	public static void main(String args[]) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		AlianzaService service = (AlianzaService) context.getBean("alianzaService");

		/*
		 * Create Alianza1
		 */
		Alianza alianza1 = new Alianza();
		alianza1.setNombre("Han Yenn");
		alianza1.setFechaInsercion(new LocalDate(2010, 10, 10));

		/*
		 * Create Alianza2
		 */
		Alianza alianza2 = new Alianza();
		alianza2.setNombre("Dan Thomas");
		alianza2.setFechaInsercion(new LocalDate(2012, 11, 11));

		/*
		 * Persist both Alianzas
		 */
		service.saveAlianza(alianza1);
		service.saveAlianza(alianza2);

		/*
		 * Get all alianzas list from database
		 */
		List<Alianza> alianzas = service.findAllAlianzas();
		for (Alianza emp : alianzas) {
			System.out.println(emp);
		}

		/*
		 * delete an alianza
		 */
		service.deleteAlianzaByNombre("ssn00000002");

		/*
		 * update an alianza
		 */

		Alianza alianza = service.findByNombre("Dan Thomas");
		service.updateAlianza(alianza);

		/*
		 * Get all alianzas list from database
		 */
		List<Alianza> alianzaList = service.findAllAlianzas();
		for (Alianza emp : alianzaList) {
			System.out.println(emp);
		}

		context.close();
	}
}
