package es.pakillo.castillos.dao.ingreso;

import java.util.List;

import es.pakillo.castillos.model.Ingreso;

public interface IngresoDao {

	void saveIngreso(Ingreso ingreso);
	
	List<Ingreso> findAllIngresos();
	
	List<Ingreso> findByIdJugador(Long idJugador, boolean orderAsc);
	
	void update(Ingreso ingreso);
}
