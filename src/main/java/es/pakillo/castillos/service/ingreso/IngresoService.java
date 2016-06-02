package es.pakillo.castillos.service.ingreso;

import java.util.List;

import es.pakillo.castillos.model.Ingreso;

public interface IngresoService {

	void saveIngreso(Ingreso ingreso);
	
	List<Ingreso> findAllIngresos();
	
	List<Ingreso> findByIdJugador(Long idJugador);
}
